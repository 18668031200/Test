package com.ygdxd.resource.listResource;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;

public class ListResources<E> extends Resources<E>{

	public ListResources(List<E> content, Iterable<Link> links) {
		super(content, links);
		// TODO Auto-generated constructor stub
	}
	
	public ListResources(List<E> content,Link...links){
		super(content,links);
	}

}
