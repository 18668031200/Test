package com.ygdxd.app.User;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ygdxd.mybatis.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="password")
@Entity
@Table(name="user")
public class User extends BaseEntity{

	/**
	 * 
	 */
	public static final long serialVersionUID = -111983474666349117L;
	
	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	
	
	private String userName;
	
	@Column(name="nike_name")
	private String nikeName;
	
	private String name;
	
	private @JsonIgnore String password;
	
	@Column(name="creat_Time")
	private Date creatTime;
	
	@Column(name="modify_time")
	private Date modifyTime;
	
	private String type;
	
	private String state;
	
	@Column
	private int gender;
	
	private String telephone;
	
	private String email;
	
	
	

}
