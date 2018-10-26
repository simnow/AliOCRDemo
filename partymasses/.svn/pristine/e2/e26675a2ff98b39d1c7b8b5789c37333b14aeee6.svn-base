package com.partymasses.support.security;

import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import com.partymasses.support.message.Message;
import com.partymasses.support.util.JacksonUtil;


public class KickoutSessionControlFilter extends LogoutFilter{
	@Override  
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {  
        //在这里执行退出系统前需要清空的数据  
        Subject subject=getSubject(request,response);  
        PrintWriter out = response.getWriter();
        Message message=new Message();
        try {  
            subject.logout();  
            response.setContentType("text/plain;charset=utf-8");
			message.setMsg("退出成功");
        }catch (SessionException e){  
            e.printStackTrace(); 
            message.setMsg("退出失败");
            message.setSuccess(false);
        } finally{
        	out.println(JacksonUtil.objectToJson(message));
			out.flush();
			out.close();
        }
        return false;  
    }  
}
