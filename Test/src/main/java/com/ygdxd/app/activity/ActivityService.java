package com.ygdxd.app.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
	
	@Autowired
	ActivityMapper activityMapper;
	
	public boolean insertActivity(Activity activity){
		return activityMapper.insertSelective(activity)==1;
	}

	public List<Activity> findALLActivity(String id,String title,String type,String startDate,String endDate,Integer allPeopleNums,Integer nowPeopleNums,String sponsorId,String sponsorName,Double allAmount,String startPlace,String endplace,String creatTime){
		return activityMapper.selectAllActivity(id, title, type, startDate, endDate, allPeopleNums, nowPeopleNums, sponsorId, sponsorName, allAmount, startPlace, endplace, creatTime);
	}
	
	public Activity findActivity(String id){
		return activityMapper.selectByPrimaryKey(id);
	}
	
	
}
