package com.ygdxd.app.Permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	PermissionService permissionService;
	
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN:ALL')")
	public ResponseEntity<?> findPermission(@PathVariable String id,Authentication authentication){
		Permission permission=permissionService.findPermission(id);
		PermissionResourceAssembler permissionResourceAssembler=new PermissionResourceAssembler();
		PermissionResource permissionResource=permissionResourceAssembler.toResource(permission);
		return ResponseEntity.ok(permissionResource);
	}
	
	
//	@ResponseBody
//	@RequestMapping
//	public 
}
