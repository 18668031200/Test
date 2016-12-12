package com.ygdxd.app.Permission;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.security.core.Authentication;

public class PermissionResourceAssembler implements ResourceAssembler<Permission, PermissionResource>{
	
	private Authentication authentication;

	@Override
	public PermissionResource toResource(Permission entity) {
		// TODO Auto-generated method stub
		PermissionResource permissionResource=new PermissionResource(entity);
		permissionResource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PermissionController.class).findPermission(entity.getId(),authentication)).withSelfRel());
		return permissionResource;
	}

}
