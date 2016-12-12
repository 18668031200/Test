package com.ygdxd.app.User;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.StringUtil;
import com.ygdxd.resource.booleanResource.BooleanResource;
import com.ygdxd.resource.booleanResource.BooleanResourceAssembler;
import com.ygdxd.utils.String.RandomData;

@RestController
@ExposesResourceFor(User.class)
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/find",method=RequestMethod.GET)
	//@PreAuthorize("hasAuthority('ADMIN:ALL')")
	public ResponseEntity<?> findUser(String id){
		return ResponseEntity.ok(JSON.toJSONString(userService.findUser(id, null, null, null, null, null, null)));
	}
	
	@ResponseBody
	@RequestMapping(value="/{telephone}/ValidateMobile",method=RequestMethod.GET)
	public ResponseEntity<?> ValidateMobile(@PathVariable String telephone){
		List<User> list=null;
		list = userService.findUser(null, null, null, null, telephone, null, null);
		if(list!=null&&list.size()>0&&!list.isEmpty()){
			return ResponseEntity.ok(new BooleanResourceAssembler().toResource(true));
		}
		return ResponseEntity.ok(new BooleanResourceAssembler().toResource(false));
		
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<?> modifyUserInfo(@PathVariable String id,
			@RequestBody User u
			){     
//		if(!StringUtils.isNotEmpty(u.getUserName())&&!StringUtils.isNotBlank(u.getUserName())){
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserErrorBuilder(UserErrorBuilder.Error_User_UserNameNoPoint, "用户的用户名为空！").build());
//		}
//		if(!StringUtils.isNotEmpty(u.getPassword())&&!StringUtils.isNotBlank(u.getPassword())){
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserErrorBuilder(UserErrorBuilder.Error_User_PasswordNoPoint, "用户的密码为空！").build());
//		}		
		u.setId(id);
		if(userService.updateUser(u)){
			return ResponseEntity.noContent().location(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(UserController.class).findUser(u.getId())).toUri()).build();
		}
		
		return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(new UserErrorBuilder(UserErrorBuilder.Error_User_UserUpdateFailure, "用户更新失败！").build());
		
	}
	
	
	
	
}
