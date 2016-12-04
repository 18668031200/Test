package com.ygdxd.resource.booleanResource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class BooleanResource extends Resource<Boolean> {

	public BooleanResource(Boolean content, Link... links) {
		super(content, links);
	}

	public BooleanResource(Boolean content, Iterable<Link> links) {
		super(content, links);
	}

}
