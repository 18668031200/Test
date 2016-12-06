package com.ygdxd.app.User;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@ExposesResourceFor(User.class)
@RequestMapping("/user")
public class UserController {

	UserService userService;
	
	@ResponseBody
	@RequestMapping("/find")
	public ResponseEntity<?> findUser(String id){
		return ResponseEntity.ok(JSON.toJSONString(userService.findUser(id, null, null, null, null, null, null)));
	}
	
}
