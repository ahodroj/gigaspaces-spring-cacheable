package org.openspaces.cacheable;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.openspaces.core.space.cache.LocalCacheSpaceConfigurer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public class GigaSpacesCacheManager implements CacheManager, InitializingBean  {
	
    private GigaSpace gigaSpace;
    
    private String space;
    
    private boolean localCache;
    
    Logger log = Logger.getLogger(this.getClass().getName());
    
	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
	
	private final Collection<String> names = Collections.unmodifiableSet(caches.keySet());
    
	@Override
	public Cache getCache(String name) {
		Cache cache = caches.get(name);
		if(cache == null) {
			cache = new GigaSpacesCache(name, this.gigaSpace);
			caches.put(name, cache);
		}
		
		return cache;
	}

	@Override
	public Collection<String> getCacheNames() {
		return names;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("GigaSpaces space set: " + this.space);
		if(this.isLocalCache()) {
			// Initialize remote space configurer:
			UrlSpaceConfigurer urlConfigurer = new UrlSpaceConfigurer(this.space);
			LocalCacheSpaceConfigurer localCacheConfigurer = new LocalCacheSpaceConfigurer(urlConfigurer);
			this.gigaSpace = new GigaSpaceConfigurer(localCacheConfigurer).gigaSpace();
		} else {
			this.gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer(this.space)).gigaSpace();
		}
		
		log.info("GigaSpaces proxy set: " + this.gigaSpace);

	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public boolean isLocalCache() {
		return localCache;
	}

	public void setLocalCache(boolean localCache) {
		this.localCache = localCache;
	}

	

}
