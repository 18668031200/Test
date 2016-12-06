package com.ygdxd.app.User;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
	
	@Select("<script>Select u.id,u.userName,u.type,u.gender,u.email,u.telephone,u.state,u.nikeName from user as u "
			+ "Where u.id is not null "
			+ "<if test=\"id!=null and id !='' \" >"
			+ "AND u.id=#{id} "
			+ "</if>"
			+ "<if test=\"userName!=null and userName !='' \" >"
			+ "AND u.userName=#{userName} "
			+ "</if>"
			+ "<if test=\"type!=null and type !='' \" >"
			+ "AND u.type=#{type} "
			+ "</if>"
			+ "<if test=\"state!=null and state !='' \" >"
			+ "AND u.state=#{state} "
			+ "</if>"
			+ "<if test=\"telephone!=null and telephone !='' \" >"
			+ "AND u.telephone=#{telephone} "
			+ "</if>"
			+ "<if test=\"email!=null and email !='' \" >"
			+ "AND u.email=#{email} "
			+ "</if>"
			+ "<if test=\"nikeName!=null and nikeName !='' \" >"
			+ "AND u.nike_name=#{nikeName} "
			+ "</if>"
			+ "</script>")
	public List<User> findUser(@Param("id")String id,@Param("userName")String userName,@Param("type")String type,@Param("state")String state,@Param("telephone")String telephone,@Param("email")String email,@Param("nikeName")String nikeName);

}
