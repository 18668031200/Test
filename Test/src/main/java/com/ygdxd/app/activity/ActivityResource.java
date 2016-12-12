package com.ygdxd.app.activity;

import java.util.Arrays;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.mysql.fabric.xmlrpc.base.Array;

public class ActivityResource extends ResourceSupport{

	private Activity entity;

	public ActivityResource(Activity entity,Link... links) {
		// TODO Auto-generated constructor stub
		this(entity,Arrays.asList(links));
	}
	
	public ActivityResource(Activity entity,Iterable<Link> links){
		this.entity.setId(entity.getId());
		this.entity.setTitil(entity.getTitil());
		this.entity.setType(entity.getType());
		this.entity.setStartTime(entity.getStartTime());
		this.entity.setEndTime(entity.getEndTime());
		this.entity.setAllPeopleNums(entity.getAllPeopleNums());
		this.entity.setNowPeopleNums(entity.getNowPeopleNums());
		this.entity.setSponsorId(entity.getSponsorId());
		this.entity.setSponsorName(entity.getSponsorName());
	}
}
