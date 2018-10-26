package cn.caecc.erp.support.oss.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.oss.service.OssService;
@Service
@PropertySource(Contants.OSS_CONFIG)
public class OssServiceImpl implements OssService {

	@Value("${oss.bucket}")
	private String bucket;

	@Value("${oss.endpoint}")
	private String endpoint;

	@Value("${oss.urlPrefix}")
	private String urlPrefix;

	@Value("${oss.accesskeyId}")
	private String accesskeyId;
	
	@Value("${oss.accesskeySecret}")
	public String accesskeySecret;
	
	
	/**
	 * @return the urlPrefix
	 */
	@Override
	public String getUrlPrefix() {
		return urlPrefix;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return propertySourcesPlaceholderConfigurer;
	}
	private OSSClient open() {
		OSSClient ossClient = new OSSClient(endpoint, accesskeyId, accesskeySecret);
		return ossClient;
	}

	private void shutdown(OSSClient ossClient) {
		if (ossClient != null) {
			ossClient.shutdown();
		}
	}

	@Override
	public boolean doesKeyExist(String key) {
		// TODO Auto-generated method stub
		boolean flag = false;
		OSSClient ossClient = null;
		try {
			ossClient = open();
			flag = ossClient.doesObjectExist(bucket, key);
		}
		catch(Exception ex) {
		}
		finally {
			shutdown(ossClient);
		}
		return flag;
	}
	
	@Override
	public List<String> listDirectory(String directory) {
		// TODO Auto-generated method stub
		List<String> fileList = new ArrayList<String>();
		OSSClient ossClient = null;
		try {
			ossClient = open();
			ObjectListing objectListing = ossClient.listObjects(bucket, directory);
			List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
			for (OSSObjectSummary s : sums) {
				fileList.add(s.getKey());
			}
		}
		catch(Exception ex) {
		}
		finally {
			shutdown(ossClient);
		}
		return fileList;
	}
	
	@Override
	public boolean deleteObject(String key) {
		OSSClient ossClient = null;
		boolean flag = false;
		try {
			ossClient = open();
			ossClient.deleteObject(bucket, key);
			flag = true;
		}
		catch(Exception ex) {
		}
		finally {
			shutdown(ossClient);
		}
		return flag;
	}

	@Override
	public void deleteDirectory(String directory) {
		OSSClient ossClient = null;
		try {
			ossClient = open();
			ObjectListing objectListing = ossClient.listObjects(bucket, directory);
			List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
			List<String> keys = new ArrayList<String>();
			for (OSSObjectSummary s : sums) {
				keys.add(s.getKey());
			}
			ossClient.deleteObjects(new DeleteObjectsRequest(bucket).withKeys(keys));
		}
		catch(Exception ex) {
		}
		finally {
			shutdown(ossClient);
		}
	}

	
	@Override
	public void moveFile(String sourceFileName, String destinationFileName) {
		// TODO Auto-generated method stub
		OSSClient ossClient = null;
		try {
			ossClient = open();
			ossClient.copyObject(bucket, sourceFileName, bucket, destinationFileName);
			ossClient.deleteObject(bucket, sourceFileName);
		}
		catch(Exception ex) {
		}
		finally {
			shutdown(ossClient);
		}
	}
	
	

}
