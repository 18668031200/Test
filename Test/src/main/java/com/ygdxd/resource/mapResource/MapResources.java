package com.ygdxd.resource.mapResource;

import java.util.Map;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class MapResources<K,V> extends Resource<Map<K,V>>{

	public MapResources(Map<K, V> content, Iterable<Link> links) {
		super(content, links);
		// TODO Auto-generated constructor stub
	}
	
	public MapResources(Map<K,V> content,Link... links){
		super(content,links);
	}

}
