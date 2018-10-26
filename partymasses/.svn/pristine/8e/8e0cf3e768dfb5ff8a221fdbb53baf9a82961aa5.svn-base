package com.partymasses.modules.service;

import java.io.InputStream;



public interface OSSService {
	
	//public String putNewsImage(Integer uintId, Integer newId, String strPath) throws Exception;
	
	/**
	 * 上传新闻图片文件
	 * uintId 单位id
	 * newId 新闻id
	 * inputStream 文件流
	 */
	/*
	public String putNewsImage(Integer uintId, Integer newId, InputStream inputStream ) throws Exception;
	*/
	
	/**
	 * 删除新闻中所有图片文件，当删除新闻时调用
	 * uintId 单位id
	 * newId 新闻id
	 */
	public void deleteNewsObjects(Integer uintId, Integer newId) throws Exception;
	/**
	 * 删除学习视频中所有文件，当删除新闻时调用
	 * uintId 单位id
	 * keySuffix 视频对应的oss名称后缀
	 */
	public void deleteLearningVideosObjects(Integer uintId, Integer videoId) throws Exception;

	/**
	 * 获取学习文件夹大小
	 * uintId 单位id
	 */
	public long getLearningVideoFolderSize(Integer uintId, Integer videoId);


}
