package com.ygdxd.upload;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.ygdxd.upload.FileInfo.FileType;

public class FileUploadService {
	private static final Logger LOG = LoggerFactory.getLogger(FileUploadService.class);
	
	private final String rootPath;
	
	@Autowired
	public FileUploadService(WebApplicationContext webApplicationContext) {
		this.rootPath = webApplicationContext.getServletContext().getRealPath("/");
	}
	
	/**
	 * 上传不同类型的多个文件（上传文件的下标编号与文件类型的下标编号需保持一致）
	 * 
	 * @param multiFiles 上传文件集
	 * @param fileTypeStr 文件类型集
	 * @return
	 */
	public Resource[] getResources(MultipartFile[] multiFiles, String[] fileTypes) {
		Resource[] resources = new Resource[multiFiles.length];
		for (int i=0; i < multiFiles.length; i++) {
			if (i > fileTypes.length || fileTypes[i] == null) {
				throw new IllegalArgumentException(multiFiles[i].getOriginalFilename() + "找不到与之匹配的fileType");
			}
			
			resources[i] = getResource(multiFiles[i], fileTypes[i]);
		}
		
		return resources;
	}
	
	
	public Resource getResource(MultipartFile multiFile, String fileTypeStr) {
		Date date = new Date();
		FileType fileType = FileType.valueOf(fileTypeStr);
		String dateString = DateFormatUtils.format(date, "yyyyMM");
		String uuid = UUID.randomUUID().toString();
		String filename = multiFile.getOriginalFilename();
		StringBuilder destPathBuilder = new StringBuilder(rootPath).append("upload").append(File.separator);
		
		switch (fileType) {
			case excel:
				destPathBuilder.append("excel").append(File.separator);
				break;
			case file:
				destPathBuilder.append("file").append(File.separator);
				break;
			case flash:
				destPathBuilder.append("flash").append(File.separator);
				break;
			case image:
				destPathBuilder.append("image").append(File.separator);
				break;
			case media:
				destPathBuilder.append("media").append(File.separator);
				break;
			case pdf:
				destPathBuilder.append("pdf").append(File.separator);
				break;
			default:
				destPathBuilder.append("unknown").append(File.separator);
				break;
		}
		
		String destPath = destPathBuilder.append(dateString).append(File.separator)
						.append(uuid).append(File.separator)
						.append(filename)
						.toString();
		
		LOG.info("上传文件存储至" + destPath);
		
		// 创建上传文件
		File file = new File(destPath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			
			multiFile.transferTo(file);
		} catch (IllegalStateException e) {
			LOG.error(file.getName() + " has already been moved in the filesystem and is not available anymore for another transfer", e);
			return null;
		} catch (IOException e) {
			LOG.error(file.getName() + " have reading or writing errors", e);
			return null;
		}
		
		return new FileSystemResource(file);
		
	}
	
	/**
	 * 获取上传文件的相对路径
	 * 
	 * @param realPath 绝对路径
	 * @return
	 */
	public String getRelativePath(String realPath) {
		if (StringUtils.isBlank(realPath) || realPath.indexOf("upload") == -1) {
			throw new IllegalArgumentException("上传文件路径不合法" + (StringUtils.isBlank(realPath) ? ", 上传路径为空" : realPath));
		}
		
		return realPath.substring(realPath.indexOf("upload"), realPath.length()).replace("\\", "/");
	}

}
