package com.ygdxd.mybatis.util;


import java.util.Collection;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import tk.mybatis.mapper.common.Mapper;

@NoArgsConstructor
@Component
public class SqlSessionHelper {
	
	private static final Logger log = LoggerFactory.getLogger(SqlSessionHelper.class);
	
	private static SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired(required = true)
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		SqlSessionHelper.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	/**
	 * 批量增加操作
	 * 
	 * @param mapperClass
	 * @param saveDataList
	 * @return
	 */
	public static <T>  boolean batchSave(Class<? extends Mapper<T>> mapperClass,Collection<T> dataList){
		SqlSession sqlSession=sqlSessionTemplate.getSqlSessionFactory().openSession();
		Mapper<T> mapper = sqlSession.getMapper(mapperClass);
		Integer index = 0;
		try {
			if (dataList != null && !dataList.isEmpty()) {
				for (T t : dataList) {
					mapper.insertSelective(t);
					index ++;
				}
				
				sqlSession.commit();
				sqlSession.clearCache();
				
			} else {
				log.info("批量更新数据列表为空");
			}
			
		} catch (Exception e) {
			log.error("第" + (index + 1) + "条数据增加失败", e);
			sqlSession.rollback();
			sqlSession.clearCache();
			
			return false;
		} finally {
			sqlSession.close();
		}
		
		return true;
		
	}
	/**
	 * 批量更新操作
	 * 
	 * @param mapperClass
	 * @param updateDataList
	 * @return
	 */
	public static <T> boolean batchUpdate(Class<? extends Mapper<T>> mapperClass, Collection<T> updateDataList) {
		SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, Boolean.FALSE);
		Mapper<T> mapper = sqlSession.getMapper(mapperClass);
		Integer index = 0;
		try {
			if (updateDataList != null && !updateDataList.isEmpty()) {
				for (T t : updateDataList) {
					mapper.updateByPrimaryKeySelective(t);
					index ++;
				}
				
				sqlSession.commit();
				sqlSession.clearCache();
				
			} else {
				log.info("批量更新数据列表为空");
			}
			
		} catch (Exception e) {
			log.error("第" + (index + 1) + "条数据更新失败", e);
			sqlSession.rollback();
			sqlSession.clearCache();
			
			return false;
		} finally {
			sqlSession.close();
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param mapperClass 
	 * @param dataList 删除对象的主键
	 * @return
	 */
	public static <T> boolean batchDelete(Class<? extends Mapper<T>> mapperClass,Collection<T> dataList){
		SqlSession sqlSession=sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
		Mapper<T> mapper=sqlSession.getMapper(mapperClass);
		Integer index=0;
		try{
		if (dataList != null && !dataList.isEmpty()) {
		for(T t : dataList ){
			mapper.deleteByPrimaryKey(t);
			index++;
		}
		sqlSession.commit();
		sqlSession.clearCache();
		}else{
			log.info("dataList为空!");
		}
		}catch (Exception e) {
			// TODO: handle exception
			sqlSession.rollback();
			sqlSession.clearCache();
			
			return false;
			
		}finally{
			sqlSession.close();
		}
		
		return true;
		
		
	}
	
	
	

}
