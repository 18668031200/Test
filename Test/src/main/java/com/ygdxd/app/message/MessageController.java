package com.ygdxd.app.message;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ygdxd.app.User.User;
import com.ygdxd.app.User.UserController;
import com.ygdxd.app.User.UserErrorBuilder;
import com.ygdxd.app.User.UserService;
import com.ygdxd.resource.booleanResource.BooleanResource;
import com.ygdxd.sms.SMSSender;
import com.ygdxd.utils.String.RandomData;
import com.ygdxd.utils.pattern.RegexValidator;

@RestController
@ExposesResourceFor(Message.class)
@RequestMapping("/message")
public class MessageController {

	@Autowired SMSSender sender;
	
	@Autowired UserService userService;
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@ResponseBody
	@RequestMapping(value="/SMS",method=RequestMethod.PATCH)
	public ResponseEntity<?> SMSUserSender(@RequestParam(required=true) String mobile){
		String code=RandomData.getSMSverification();
		redisTemplate.boundHashOps("SMS").put("VerificationCode", code);
		if(RegexValidator.isMobile(mobile)){
			try {
				System.out.println(sender.getSellerItem(code,mobile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErrorBuilder(MessageErrorBuilder.Error_Message_SendFailure, "验证码发送失败！").build());
			}
			
		}
		return ResponseEntity.ok(new BooleanResource(Boolean.TRUE));
		
	}
	
	@ResponseBody
	@RequestMapping(value="/UserRegister",method=RequestMethod.POST) 
	public ResponseEntity<?> UserRegister(@RequestParam(required=true) String mobile,String VerificationCode,String passwrod){
		
		if(!StringUtils.isNotEmpty(passwrod)&&!StringUtils.isNotBlank(passwrod)){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserErrorBuilder(UserErrorBuilder.Error_User_PasswordNoPoint, "用户的密码为空！").build());
		}	
		
		String code = (String) redisTemplate.boundHashOps("SMS").get("VerificationCode");
		if(VerificationCode==null||VerificationCode.equals("")){
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new MessageErrorBuilder(MessageErrorBuilder.Error_Message_VerificationCodeNullPoint, "输入短信验证码为空！").build());
		}
		
		if(code==null||code.equals("")){
			return ResponseEntity.status(HttpStatus.GONE).body(new MessageErrorBuilder(MessageErrorBuilder.Error_Message_TimeLimit, "短信验证码过期！").build());
		}
		
		if(code.equals(VerificationCode)){
			//return ResponseEntity.ok(new BooleanResource(true));
			User u=new User();
			BCryptPasswordEncoder bcpe=new BCryptPasswordEncoder();
			u.setPassword(bcpe.encode(passwrod));
			u.setTelephone(mobile);
			u.setCreatTime(new Date());
			u.setNikeName("visitor"+RandomData.getSMSverification()+RandomData.getSMSverification());
			u.setModifyTime(new Date());
			u.setState("alive");
			u.setPermissions(new String[]{"ADMIN:ALL"});
			u.setType("Normal");
			userService.saveUser(u);
			return ResponseEntity.created(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(UserController.class).findUser(u.getId())).toUri()).build();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErrorBuilder(MessageErrorBuilder.Error_Message_VerificationCodeWrong, "验证码 注册失败").build());
	}
}
