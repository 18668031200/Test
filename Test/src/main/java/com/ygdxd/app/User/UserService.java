package com.ygdxd.app.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	public List<User> findUser(String id,String userName,String type,String state,String telephone,String email,String nikeName){
		return userMapper.findUser(id, userName, type, state, telephone, email, nikeName);
	}
	
	public boolean saveUser(User u){
		return userMapper.insertSelective(u)==1;
	}
	
	public boolean updateUser(User u){
		return userMapper.updateByPrimaryKeySelective(u)==1;
	}

}
