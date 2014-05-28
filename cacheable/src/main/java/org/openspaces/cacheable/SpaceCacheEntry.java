package org.openspaces.cacheable;

import java.io.Serializable;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

@SpaceClass
public class SpaceCacheEntry {
	
	private String key;
	
	private String name;
	
	private Serializable valueObject;

	@SpaceId(autoGenerate = false)
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public Serializable getValueObject() {
		return valueObject;
	}

	public void setValueObject(Serializable valueObject) {
		this.valueObject = valueObject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
