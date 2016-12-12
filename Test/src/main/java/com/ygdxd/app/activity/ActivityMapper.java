package com.ygdxd.app.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface ActivityMapper extends MySqlMapper<Activity>,Mapper<Activity>{

	@Select("<script>"
			+ "Select count(1) from activity as a Where a.id is not null "
			+ "<if test=\" id != null and id !='' \" > AND a.id=#{id} </if> "
			+ "<if test=\" title != null and title !='' \" > AND a.title like '${title}%' </if> "
			+ "<if test=\" type != null and type !='' \" > AND a.type = #{type} </if> "
			+ "<if test=\" start_time != null and start_time !='' \" > AND a.start_time <![CDATA[>=]]> #{start_time} </if> "
			+ "<if test=\" end_time != null and end_time !='' \" > AND a.end_time <![CDATA[<=]]> #{end_time} </if> "
			+ "<if test=\" all_people_nums != null  \" > AND a.all_people_nums = #{all_people_nums} </if> "
			+ "<if test=\" now_people_nums != null  \" > AND a.now_people_nums = #{now_people_nums} </if> "
			+ "<if test=\" sponsor_id != null and sponsor_id !='' \" > AND a.sponsor_id=#{sponsor_id} </if> "
			+ "<if test=\" sponsor_name != null and sponsor_name !='' \" > AND a.sponsor_name like '${sponsor_name}%' </if> "
			+ "<if test=\" all_amount != null  \" > AND a.all_amount = #{all_amount} </if> "
			+ "<if test=\" start_place != null and start_place !='' \" > AND a.start_place=#{start_place} </if> "
			+ "<if test=\" end_place != null and end_place !='' \" > AND a.end_place=#{end_place} </if> "
			+ "<if test=\" creat_time != null and creat_time !='' \" > AND a.creat_time <![CDATA[>=]]> #{creat_time} </if> "
			+ "</script>")
	public List<Activity> selectAllActivity(@Param("id") String id,@Param("title") String title,@Param("type") String type,
			@Param("start_time") String startDate,@Param("end_time")String endDate,@Param("all_people_nums") Integer allPeopleNums,
			@Param("now_people_nums") Integer nowPeopleNums,@Param("sponsor_id") String sponsorId,@Param("sponsor_name")String sponsorName,
			@Param("all_amount") Double allAmount,@Param("start_place") String startPlace,@Param("end_place")String endplace,@Param("creat_time")String creatTime);
	
}
