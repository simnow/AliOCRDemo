package com.test.ocr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;	
public class OcrLocalDemo {

	public static void main(String[] args) {
		 // 将图片转换为base64编码格式
        String imgPath = "C:\\Users\\GaiNing\\Desktop\\1.jpg";//C:\Users\GaiNing\Desktop
        String imgStr = imgToBase64(imgPath);
        ocr(imgStr);
	}

	private static void ocr(String imgBase64) {
		String host = "https://ocrapi-document.taobao.com"; // 阿里接口地址
        String path = "/ocrservice/document"; // 具体地址
        String method = "POST";  // 请求类型POST
        String appcode = "6862bd4c08e44cb6b0b6a3222d6d8b68"; // 产品密钥
        
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE yourAppCode
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        // img 和 url 只能使用一个
        String bodys = "{\"img\":\""+imgBase64+"\",\"prob\":false}";
        try {
            /**
             * HttpUtils下载：https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 相关依赖请参照：https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            // 获取response的body
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

	/**
     * 将图片转换为base64字符串
     * @param imgPath 编码图片的路径
     * @return imgStr 图片的base64编码字符串
     */
    public static String imgToBase64(String imgPath){
        byte[] data = null;
        InputStream in = null;
        try {
            // 将图片读入data中
            in = new FileInputStream(new File(imgPath));
            data = new byte[in.available()];
            in.read(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 对data进行Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        String imgStr = encoder.encode(data);
        return imgStr;
    }
    
    /**
     * 将Base64字符串转换为图片
     * @param imgStr 图片的base64编码字符串；
     * @param imgPath 生成图片的路径
     * @return 是否生成图片
     */
    public static boolean base64ToImg(String imgStr, String imgPath){
        if(imgStr == null){
            return false;
        }
        // 对imgBase64字符串进行解码
        OutputStream out = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i = 0 ; i <b.length ; i++){
                // 调整异常数据
                if(b[i] < 0){
                    b[i] += 256;
                }
            }
            // 生成图片文件
            out = new FileOutputStream(new File(imgPath));
            out.write(b);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
