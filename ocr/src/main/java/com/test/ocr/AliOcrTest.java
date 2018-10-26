package com.test.ocr;
import java.util.HashMap;
import java.util.Map;
 
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
 
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
 
 
/** 
 * @author: py
 * @version:2018年3月16日 下午2:50:32 
 * com.ali.ocr.TestAliOcr.java
 * @Desc 
 */
public class AliOcrTest {
 
	public static void main(String[] args) {
	    String host = "https://ocrapi-document.taobao.com";//不变
	    String path = "/ocrservice/document";//不变
	    String method = "POST";//不变
	    //TODO
	    String appcode = "6862bd4c08e44cb6b0b6a3222d6d8b68";//需要修改
	    
	    
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    //根据API的要求，定义相对应的Content-Type
	    headers.put("Content-Type", "application/json; charset=UTF-8");
	    Map<String, String> querys = new HashMap<String, String>();
//	    + "//图像数据：base64编码，要求base64编码后大小不超过4M，最短边至少15px，最长边最大4096px，支持jpg/png/bmp格式，和url参数只能同时存在一个"
//	    + "//图像url地址：图片完整URL，URL长度不超过1024字节，URL对应的图片base64编码后大小不超过4M，最短边至少15px，最长边最大4096px，支持jpg/png/bmp格式，和img参数只能同时存在一个"
//	    + "//是否需要识别结果中每一行的置信度，默认不需要。true：需要false：不需要"
	    String bodys = "{"
	    		+ "\"img\":\"\","
	    	    + "\"url\":\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523367775393&di=4cd8a2c97a08eeba1ed6b60213735be3&imgtype=0&src=http%3A%2F%2Fimg5q.duitang.com%2Fuploads%2Fitem%2F201504%2F08%2F20150408H2909_4vuxK.thumb.700_0.jpeg\","
	    		+ "\"prob\":false}";
 
 
	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	String message = EntityUtils.toString(response.getEntity()) ;
	    	System.out.println(message);
	    	JSONObject parseObject = JSONObject.parseObject(message);
//	    	String sid = parseObject.getString("sid");
//	    	String prism_version = parseObject.getString("prism_version");
	    	JSONArray jan = parseObject.getJSONArray("prism_wordsInfo");
	    	if(jan!=null||jan.size()!=0){ 
	    		for(int i=0;i<jan.size();i++){ 
    		    JSONObject jo = JSONObject.parseObject(jan.get(i).toString());
    		    String word = jo.getString("word"); 
    		    System.out.println(word);        
	    		} 
	    	} 
	    	
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
