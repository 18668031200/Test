package com.ygdxd.app.Permission;

import org.springframework.stereotype.Service;

@Service
public class PermissionService {
	
	PermissionMapper permissionMapper;
	
	public boolean savePermission(Permission permission){
		return permissionMapper.insertSelective(permission)==1;
	}
	
	public Permission findPermission(String id){
		return permissionMapper.selectByPrimaryKey(id);
	}

}
