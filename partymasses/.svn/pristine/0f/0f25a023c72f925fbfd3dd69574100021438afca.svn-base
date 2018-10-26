package com.partymasses.modules.service.impl;

import java.io.InputStream;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.SimplifiedObjectMeta;
import com.partymasses.modules.service.OSSService;
import com.partymasses.support.constant.Contants;


@Service
public class OSSServiceImpl implements OSSService {
	/*
	@Autowired  
	private HttpSession session;  
	/function/unitid_1/functionid_1/
	*/
	private final static Logger logger = LoggerFactory.getLogger(OSSServiceImpl.class);

	//生成功能文件夹名称
	private String generalOssFunctionFolderName(Integer uintId, String function, Integer id)
	{
		String strFolder = function +  "/" + "unitid_" + uintId.toString() + "/" +  function +"id_" + id.toString() ;
		return strFolder;
	}
	
	//生成类型文件夹名称
	private String generalOssFunctionTypeFolderName(Integer uintId, String function, Integer id, String typeName)
	{
		String strFolder = generalOssFunctionFolderName(uintId, function, id) +  "/" + typeName;
		return strFolder;
	}
	
	//生成对象键值
	private String generalObjectOssKeyName(Integer uintId, String function, Integer id, String typeName, String objectNameSuffix)
	{
		if (objectNameSuffix == null)
		{
			objectNameSuffix = UUID.randomUUID().toString();
		}
		String strObject = generalOssFunctionTypeFolderName(uintId, function, id, typeName) +  "/"  + objectNameSuffix;
		return strObject;
	}
	/*
	
	//上传对象
	private String putObject(OSSClient ossClient,
			String sourceBucketName, 
			Integer uintId, 
			String function,
			Integer id, 
			String typeName, InputStream inputStream)
	{
		String strKey = generalObjectOssKeyName(uintId, function, id, typeName, null) ;
		String strUrl = Contants.OSSURLPREFIX + strKey;
		// 创建OSSClient实例

		// 上传文件
		ossClient.putObject(Contants.OSSBUCKET, strKey, inputStream);
		ossClient.setObjectAcl(Contants.OSSBUCKET, strKey, CannedAccessControlList.PublicRead);
		return strUrl;
	}
	
	@Override
	public String putNewsImage(Integer uintId, Integer newId, InputStream inputStream)
	{
		String accessKeyId = Contants.OSSACCESSKEYID;
		String accessKeySecret = Contants.OSSACCESSKEYSECRET;
		OSSClient ossClient = new OSSClient(Contants.OSSENDPOINT, accessKeyId, accessKeySecret);

		String strUrl = putObject(ossClient, Contants.OSSBUCKET, uintId, Contants.OSSNEWSFUNCTION, newId, Contants.OSSIMAGETYPE, inputStream);
		
		// 关闭client
		ossClient.shutdown();	
		
		return strUrl;
				
	}
	*/
	
	//删除模拟文件夹prefixKey为文件夹名
	private void deleteFolder(OSSClient ossClient, String sourceBucketName, String prefixKey)
	{
		ObjectListing  objectListing = null;

		List<String> keys = new ArrayList<String>();
		String nextMarker = null;
		do {
		    objectListing = ossClient.listObjects(new ListObjectsRequest(sourceBucketName).
		            withPrefix(prefixKey).withMarker(nextMarker).withMaxKeys(1000));
		    List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
		    for (OSSObjectSummary s : sums) {
		    	
		    	keys.add(s.getKey());
		    }
		    nextMarker = objectListing.getNextMarker();
		} while (objectListing.isTruncated());
		
		//批量删除文件
		ossClient.deleteObjects(new DeleteObjectsRequest(sourceBucketName).withKeys(keys));
	}
	
	@Override
	public void deleteNewsObjects(Integer uintId, Integer newId)
	{
		String accessKeyId = Contants.OSSACCESSKEYID;
		String accessKeySecret = Contants.OSSACCESSKEYSECRET;
		OSSClient ossClient = new OSSClient(Contants.OSSENDPOINT, accessKeyId, accessKeySecret);
		//模拟文件夹名，一个新闻存在对应的文件夹,prefix为模拟文件夹名
		String prefix = generalOssFunctionFolderName(uintId, Contants.OSSNEWSFUNCTION, newId);

		deleteFolder(ossClient, Contants.OSSBUCKET, prefix);
		
		// 关闭client
		ossClient.shutdown();
	}
	
	@Override
	public void deleteLearningVideosObjects(Integer uintId, Integer videoId) throws Exception
	{
		String accessKeyId = Contants.OSSACCESSKEYID;
		String accessKeySecret = Contants.OSSACCESSKEYSECRET;
		OSSClient ossClient = new OSSClient(Contants.OSSENDPOINT, accessKeyId, accessKeySecret);
		
		String sourceKey = generalOssFunctionFolderName(uintId, Contants.OSSLEARNINGVIDEOFUNCTION, videoId);
		ossClient.deleteObject(Contants.OSSBUCKET, sourceKey);
		ossClient.shutdown();
	}
	
	private long getFolderSize(OSSClient ossClient, String sourceBucketName, String sourceKeyPrefix)
	{
		ObjectListing  objectListing = null;
		long size = 0;
		String nextMarker = null;
		do {
		    objectListing = ossClient.listObjects(new ListObjectsRequest(sourceBucketName).
		            withPrefix(sourceKeyPrefix).withMarker(nextMarker).withMaxKeys(1000));
		    List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
		    for (OSSObjectSummary s : sums) {
		    	SimplifiedObjectMeta objectMeta = ossClient.getSimplifiedObjectMeta(sourceBucketName, s.getKey());
		    	size += objectMeta.getSize();
		    }
		    nextMarker = objectListing.getNextMarker();
		} while (objectListing.isTruncated());
		return size;
	}
	
	@Override
	public long getLearningVideoFolderSize(Integer uintId, Integer videoId)
	{
		String accessKeyId = Contants.OSSACCESSKEYID;
		String accessKeySecret = Contants.OSSACCESSKEYSECRET;
		OSSClient ossClient = new OSSClient(Contants.OSSENDPOINT, accessKeyId, accessKeySecret);
		String sourceKeyPrefix = generalOssFunctionFolderName(uintId, Contants.OSSLEARNINGVIDEOFUNCTION, videoId);
		long size = getFolderSize(ossClient, Contants.OSSBUCKET, sourceKeyPrefix);
		ossClient.shutdown();
		return size;
	}
}
