gigaspaces-spring-cacheable
===========================

Implementation of Spring Cache Abstraction for GigaSpaces XAP. This provides support for @Cacheable annotations over methods in a Spring application. To use GigaSpaces as a caching provider, you should define a GigaSpacesCacheManager bean and register it as a Spring cache manager:

```xml
     <cache:annotation-driven />

 	   <bean id="cacheManager" class="org.openspaces.cacheable.GigaSpacesCacheManager">
  		 <property name="space" value="jini://*/*/space" />
	   </bean>
```

For caching declaration, the abstraction provides two Java annotations: @Cacheable and @CacheEvict which allow methods to trigger cache population or cache eviction:

```java
@Cacheable("books")
public Book findBook(ISBN isbn) {...}

@CacheEvict(value = "books", allEntries=true)
public void loadBooks(InputStream batch)

```


Since most cache get queries in Spring are ID-based lookups, the performance can be greatly improved by even utilizing the Local Cache feature in GigaSpaces XAP:

```xml
     <cache:annotation-driven />
  

 	  <bean id="cacheManager" class="org.openspaces.cacheable.GigaSpacesCacheManager">
  		 <property name="space" value="jini://*/*/space" />
     	 <property name="localCache" value="true" />

	   </bean>
```
