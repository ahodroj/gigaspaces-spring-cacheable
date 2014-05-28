package org.openspaces.cacheable;

import java.io.Serializable;

import org.openspaces.core.GigaSpace;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

public class GigaSpacesCache implements Cache {
	
	private GigaSpace gigaSpace;
	
	private String name;
	
	public GigaSpacesCache(String name, GigaSpace gigaSpace) {
		this.gigaSpace = gigaSpace;
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getNativeCache() {
		return gigaSpace;
	}
	
	@Override
	public ValueWrapper get(Object key) {
		SpaceCacheEntry entry = gigaSpace.readById(SpaceCacheEntry.class, key.toString());
		
		return (entry == null ? null : new SimpleValueWrapper(entry.getValueObject()));
	}

	@Override
	public void put(Object key, Object value) {
		SpaceCacheEntry entry = new SpaceCacheEntry();
		entry.setKey(key.toString());
		entry.setName(name);
		entry.setValueObject((Serializable)value);
		
		gigaSpace.write(entry);
	}

	@Override
	public void evict(Object key) {
		SpaceCacheEntry entry = new SpaceCacheEntry();
		entry.setKey(key.toString());
		
		gigaSpace.clear(entry);
	}

	@Override
	public void clear() {
		SpaceCacheEntry entry = new SpaceCacheEntry();
		entry.setName(this.name);
		
		gigaSpace.clear(entry);
		
	}

	public GigaSpace getGigaSpace() {
		return gigaSpace;
	}

	public void setGigaSpace(GigaSpace gigaSpace) {
		this.gigaSpace = gigaSpace;
	}

}
