package com.ygdxd.app.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@ExposesResourceFor(User.class)
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping("/find")
	//@PreAuthorize("hasAuthority('ADMIN:ALL')")
	public ResponseEntity<?> findUser(String id){
		return ResponseEntity.ok(JSON.toJSONString(userService.findUser(id, null, null, null, null, null, null)));
	}
	
}
