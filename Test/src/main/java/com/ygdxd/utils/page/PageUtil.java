package com.ygdxd.utils.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.github.pagehelper.PageHelper;

/**
 * 分页工具
 * 
 * @author xuq
 * @date 2016年8月13日
 * @version 1.0
 */
public class PageUtil {

	private PageUtil() {}
	
	/**
	 * 获取分页内容及相关分页信息
	 * 
	 * @param pageable 分页信息
	 * @param callback 用于获取列表内容
	 * @return 分页内容
	 */
	public static <T> Page<T> pagination(Pageable pageable, Callback<T> callback) {
		return PageUtil.pagination(pageable, null, callback);
	}
	
	/**
	 * 获取分页内容及相关分页信息
	 * 
	 * @param pageable 分页信息
	 * @param sortList 外部传递的分页，与pageable的sort取其一，如果sortList有排序，则以sortList作为唯一排序内容；否则以pageable内部的排序作为唯一排序内容
	 * @param callback 用于获取列表内容
	 * @return 分页内容
	 */
	public static <T> Page<T> pagination(Pageable pageable, List<String> sortList, Callback<T> callback) {
		List<String> sorts = new ArrayList<>();
		if (sortList != null) {
			sorts.addAll(sortList);
		}
		if (!sorts.isEmpty() && pageable.getSort() != null) {
			pageable.getSort().forEach( item -> {
				sorts.add(item.getProperty() + " " + item.getDirection());
			});
		}
		com.github.pagehelper.Page<T> page = PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize(), StringUtils.join(sorts, ","));
		
		List<T> list = callback.getPageContent();
		return new PageImpl<>(list, pageable, page.getTotal());
	}
	
	/**
	 * 分页内容回调接口
	 * 
	 * @author xuq
	 * @date 2016年8月13日
	 * @version 1.0
	 */
	public interface Callback<T> {
		
		public List<T> getPageContent();
		
	}
	
}
