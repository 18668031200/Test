package com.ygdxd.app.activity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ygdxd.mybatis.BaseEntity;
import com.ygdxd.mybatis.typeHanler.StringArrayTypeHandler;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.ColumnType;


/**
 * 义行等活动
 * @author admin
 *
 */
@Setter
@Getter
@ToString
@Entity
@Table(name="activity")
public class Activity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1848316008398473624L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id; 
	/**
	 * 标题
	 */
	private String titil;
	/**
	 * 类型
	 */
	private String type;
	
	private @JsonFormat(pattern = "yyyy-MM-dd HH:mm") Date startTime;
	
	private @JsonFormat(pattern = "yyyy-MM-dd HH:mm") Date endTime;
	/**
	 * 人数上限
	 */
	private Integer allPeopleNums;
	/**
	 * 发起人的ID
	 */
	private String sponsorId;
	/**
	 * 发起人的昵称
	 */
	private String sponsorName;
	/**
	 * 需要的钱
	 */
	private double allAmount;
	/**
	 * 现在拥有的人数
	 */
	private Integer nowPeopleNums;
	/**
	 * 现在拥有的资金
	 */
	private double hasAmount;
	
	private String startPlace;
	
	private String endPlace;
	/**
	 * 浏览数
	 */
	private Integer readNums;
	/**
	 * 点赞数
	 */
	private Integer likeNums;
	
	private @JsonFormat(pattern = "yyyy-MM-dd HH:mm") Date creatTime;
	
	private @JsonFormat(pattern = "yyyy-MM-dd HH:mm") @JsonIgnore Date modifyTime;
	
	private @ColumnType(typeHandler=StringArrayTypeHandler.class) String[] commentId;
	
	private @ColumnType(typeHandler=StringArrayTypeHandler.class) String[] donationId;
	

}
