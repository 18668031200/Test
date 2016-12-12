package com.ygdxd.app.Permission;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class PermissionResource extends Resource<Permission>{

	public PermissionResource(Permission content, Iterable<Link> links) {
		super(content, links);
		// TODO Auto-generated constructor stub
	}
	
	public PermissionResource(Permission content,Link...links){
		super(content,links);
	}

}
