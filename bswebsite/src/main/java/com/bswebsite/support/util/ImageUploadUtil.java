package com.bswebsite.support.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import sun.misc.BASE64Decoder;

public class ImageUploadUtil {
	// 图片类型
    private static List<String> fileTypes = new ArrayList<String>();

    static {
        fileTypes.add(".jpg");
        fileTypes.add(".jpeg");
        fileTypes.add(".bmp");
        fileTypes.add(".gif");
        fileTypes.add(".png");
    }

    /**
     * 图片上传
     * 
     * @Title upload
     * @param request
     * @param DirectoryName
     *            文件上传目录：比如upload(无需带前面的/) upload/news ..
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static String upload(HttpServletRequest request, String DirectoryName) throws IllegalStateException,
            IOException {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 图片名称
        String returnUrl = "";
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
            	//时间格式
            	String date = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        // 获得图片的原始名称
                        String originalFilename = file.getOriginalFilename();
                        // 获得图片后缀名称,如果后缀不为图片格式，则不上传
                        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                        if (!fileTypes.contains(suffix)) {
                            continue;
                        }
                        InputStream inputStream=file.getInputStream();
                        //上传oss
                        returnUrl=OssUtil.putNewsImage(date, inputStream,suffix);
                        return returnUrl;
                    }
                }
            }
        }
		return returnUrl;
    }

	/**
     * ckeditor文件上传功能，回调，传回图片路径，实现预览效果。
     * 
     * @Title ckeditor
     * @param request
     * @param response
     * @param DirectoryName
     *            文件上传目录：比如upload(无需带前面的/) upload/..
     * @throws IOException
     */
    public static void ckeditor(HttpServletRequest request, HttpServletResponse response, String DirectoryName)
            throws IOException {
        String fileName = upload(request, DirectoryName);
        // 结合ckeditor功能
        // imageContextPath为图片在服务器地址，如upload/123.jpg,非绝对路径
        String imageContextPath = fileName;
        response.setContentType("text/html;charset=UTF-8");
        String callback = request.getParameter("CKEditorFuncNum");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageContextPath + "',''" + ")");
        out.println("</script>");
        out.flush();
        out.close();
    }
    
    /**
     * 用于处理粘贴
     * @param req
     * @param resp
     * @return
     */
    public static String uploadImg(String imgUrl,String path){
    	//将字符串转成
    	byte[] b=GenerateImage(imgUrl);
    	InputStream in = new ByteArrayInputStream(b);
    	//时间格式
    	String date = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
    	//TODO
        return OssUtil.putNewsImage(date, in,".jpg");
    }
    
    /*
	 * 图片解码
	 */
	private static byte[] GenerateImage(String imgURL) {
		BASE64Decoder decoder=new BASE64Decoder();
		//Base64解码
        try {
        	//Base64解码
            byte[] b = decoder.decodeBuffer(imgURL);
         // Base64解码  
			for (int i = 0; i < b.length; ++i) {  
				if (b[i] < 0) {// 调整异常数据  
				b[i] += 256;  
			}
			}
            return b;
		} catch (IOException e) {
			e.printStackTrace();
        }
		return null;
	}
}
