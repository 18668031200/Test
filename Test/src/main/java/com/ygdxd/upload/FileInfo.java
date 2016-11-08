package com.ygdxd.upload;

import java.io.File;
import java.util.Date;

/**
 * 文件信息
 * 
 * @type utils
 * @description 文件信息类 提供设置与获得文件名称、文件类型、文件、临时路径、目标路径、是否为目录、文件大小、最后修改时间的操作
 *              提供通过上传的文件获得文件信息的操作
 * @author SHOP++ Team
 * @version 3.0
 */
public class FileInfo {

	/**
	 * 文件类型
	 */
	public enum FileType {

		/** 图片 */
		image,

		/** Flash */
		flash,

		/** 媒体 */
		media,

		/** 文件 */
		file,

		/** excel导入导出文件 */
		excel,
		
		/** pdf*/
		pdf
	}

	/**
	 * 排序类型
	 */
	public enum OrderType {

		/** 名称 */
		name,

		/** 大小 */
		size,

		/** 类型 */
		type
	}

	/** 名称 */
	private String name;

	/** 文件类型 */
	private FileType fileType;

	/** 文件 */
	private File file;

	/** 临时URL */
	private String tempUrl;

	/** 目标URL */
	private String targetUrl;

	/** 是否为目录 */
	private Boolean isDirectory;

	/** 大小 */
	private Long size;

	/** 最后修改日期 */
	private Date lastModified;


	/**
	 * 获取名称
	 * 
	 * @description 获取名称
	 * @version 1.0
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @description 设置名称并获得文件信息
	 * @version 1.0
	 * @param name
	 *            名称
	 */
	public FileInfo setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 获得文件类型
	 * 
	 * @description 返回文件类型
	 * @version 1.0
	 * @return
	 */
	public FileType getFileType() {
		return fileType;
	}

	/**
	 * 设置文件类型
	 * 
	 * @description 设置文件类型并返回文件信息
	 * @version 1.0
	 * @param fileType
	 *            文件类型
	 */
	public FileInfo setFileType(FileType fileType) {
		this.fileType = fileType;
		return this;
	}

	/**
	 * 获得文件
	 * 
	 * @description 返回文件
	 * @version 1.0
	 * @return
	 */
	public File getFile() {
		return file;
	}

	/**
	 * 设置文件
	 * 
	 * @description 设置文件并返回文件信息
	 * @version 1.0
	 * @param file
	 */
	public FileInfo setFile(File file) {
		this.file = file;
		return this;
	}

	/**
	 * 获取临时路径
	 * 
	 * @description 返回临时路径
	 * @version 1.0
	 * @return
	 */
	public String getTempUrl() {
		return tempUrl;
	}

	/**
	 * 设置临时路径
	 * 
	 * @description 设置临时路径并返回文件信息
	 * @version 1.0
	 * @param tempUrl
	 */
	public FileInfo setTempUrl(String tempUrl) {
		this.tempUrl = tempUrl;
		return this;
	}

	/**
	 * 获得目标路径
	 * 
	 * @description 返回目标路径
	 * @version 1.0
	 * @return
	 */
	public String getTargetUrl() {
		return targetUrl;
	}

	/**
	 * 设置目标路径
	 * 
	 * @description 设置目标路径并返回文件信息
	 * @version 1.0
	 * @param targetUrl
	 */
	public FileInfo setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
		return this;
	}

	/**
	 * 获取是否为目录
	 * 
	 * @description 返回是否为目录
	 * @version 1.0
	 * @return 是否为目录
	 */
	public Boolean getIsDirectory() {
		return isDirectory;
	}

	/**
	 * 设置是否为目录
	 * 
	 * @description 设置是否为目录信息并返回文件信息
	 * @version 1.0
	 * @param isDirectory
	 *            是否为目录
	 */
	public FileInfo setIsDirectory(Boolean isDirectory) {
		this.isDirectory = isDirectory;
		return this;
	}

	/**
	 * 获取大小
	 * 
	 * @description 返回文件大小
	 * @version 1.0
	 * @return 大小
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * 设置大小
	 * 
	 * @description 设置文件大小并返回文件信息
	 * @version 1.0
	 * @param size
	 *            大小
	 */
	public FileInfo setSize(Long size) {
		this.size = size;
		return this;
	}

	/**
	 * 获取最后修改日期
	 * 
	 * @description 返回最后修改日期
	 * @version 1.0
	 * @return 最后修改日期
	 */
	public Date getLastModified() {
		return lastModified;
	}

	/**
	 * 设置最后修改日期
	 * 
	 * @description 设置最后修改日期并返回文件信息
	 * @version 1.0
	 * @param lastModified
	 *            最后修改日期
	 */
	public FileInfo setLastModified(Date lastModified) {
		this.lastModified = lastModified;
		return this;
	}

}