package com.ygdxd.app.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ExposesResourceFor(Activity.class)
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	ActivityService activityService;
	
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> findActivity(@PathVariable String id){
		activityService.findActivity(id);
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/",method=RequestMethod.POST)
	//@PreAuthorize("hasAuthority('ADMIN:ALL')")
	public ResponseEntity<?> insertActivity(@RequestBody Activity activity,Authentication authentication){
		
		if(activity==null){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ActivityErrorBuilder(ActivityErrorBuilder.Error_Activity_Nopointed, "活动为空!").build());
		}
		
		if(activityService.insertActivity(activity)){
			return ResponseEntity.created(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ActivityController.class).findActivity(activity.getId())).toUri()).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ActivityErrorBuilder(ActivityErrorBuilder.Error_Activity_InsertFailure, "活动创建失败！").build());
		
	}
}
