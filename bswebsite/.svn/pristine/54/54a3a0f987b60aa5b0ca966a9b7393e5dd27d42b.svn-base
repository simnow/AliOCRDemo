package com.bswebsite.support.util;

import java.io.InputStream;
import java.util.UUID;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.bswebsite.support.constant.Contants;

public class OssUtil {

	public static String putNewsImage(String date, InputStream inputStream,String objectNameSuffix) {
		String accessKeyId = Contants.OSSACCESSKEYID;
		String accessKeySecret = Contants.OSSACCESSKEYSECRET;
		OSSClient ossClient = new OSSClient(Contants.OSSENDPOINT, accessKeyId, accessKeySecret);

		String strUrl = putObject(ossClient, Contants.OSSBUCKET, Contants.OSSNEWSFUNCTION, date,
				Contants.OSSIMAGETYPE, inputStream,objectNameSuffix);

		// 关闭client
		ossClient.shutdown();

		return strUrl;

	}

	// 上传对象
	private static String putObject(OSSClient ossClient, String sourceBucketName, String function, String date,
			String typeName, InputStream inputStream,String objectNameSuffix) {
		String strKey = generalObjectOssKeyName(function, date, typeName, objectNameSuffix);
		String strUrl = Contants.OSSURLPREFIX + strKey;
		// 创建OSSClient实例

		// 上传文件
		ossClient.putObject(Contants.OSSBUCKET, strKey, inputStream);
		ossClient.setObjectAcl(Contants.OSSBUCKET, strKey, CannedAccessControlList.PublicRead);
		return strUrl;
	}

	// 生成对象键值
	private static String generalObjectOssKeyName(String function, String date, String typeName,
			String objectNameSuffix) {
		if (objectNameSuffix == null) {
			objectNameSuffix = UUID.randomUUID().toString();
		}
		String strObject = generalOssFunctionTypeFolderName(function, date, typeName) + objectNameSuffix;
		return strObject;
	}

	// 生成功能文件夹名称
	private static String generalOssFunctionFolderName(String function,String typeName, String date) {
		String strFolder = function + "/" +typeName+"/"+date;
		return strFolder;
	}

	// 生成类型文件夹名称
	private static String generalOssFunctionTypeFolderName(String function,  String date, String typeName) {
		String strFolder = generalOssFunctionFolderName(function,typeName, date);
		return strFolder;
	}
}
