package com.ygdxd.app.User;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>{
	
	@Select("<script>Select id,userName,type,gender,email,telephone,state,nike_name from user "
			+ "Where id is not null "
			+ "<if test=\"id!=null and id !='' \" >"
			+ "AND id=#{id} "
			+ "</if>"
			+ "<if test=\"userName!=null and userName !='' \" >"
			+ "AND userName=#{userName} "
			+ "</if>"
			+ "<if test=\"type!=null and type !='' \" >"
			+ "AND type=#{type} "
			+ "</if>"
			+ "<if test=\"state!=null and state !='' \" >"
			+ "AND state=#{state} "
			+ "</if>"
			+ "<if test=\"telephone!=null and telephone !='' \" >"
			+ "AND telephone=#{telephone} "
			+ "</if>"
			+ "<if test=\"email!=null and email !='' \" >"
			+ "AND email=#{email} "
			+ "</if>"
			+ "<if test=\"nikeName!=null and nikeName !='' \" >"
			+ "AND nike_name=#{nikeName} "
			+ "</if>"
			+ "</script>")
	public List<User> findUser(@Param("id")String id,@Param("userName")String userName,@Param("type")String type,@Param("state")String state,@Param("telephone")String telephone,@Param("email")String email,@Param("nikeName")String nikeName);

}
