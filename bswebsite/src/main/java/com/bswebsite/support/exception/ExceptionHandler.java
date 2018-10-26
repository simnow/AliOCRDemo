package com.bswebsite.support.exception;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.bswebsite.support.message.Message;
import com.bswebsite.support.util.JacksonUtil;


public class ExceptionHandler extends ExceptionHandlerExceptionResolver {

	private final static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
	private String defaultErrorView;

	public String getDefaultErrorView() {
		return defaultErrorView;
	}

	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}

	protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod, Exception exception) {
		if (handlerMethod == null) {
			return null;
		}
		Method method = handlerMethod.getMethod();
		if (method == null) {
			return null;
		}
		ModelAndView returnValue = new ModelAndView();
		// 判断是否已捕获ajax异常
		if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null
						&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			// 如果不是ajax,返回一个错误页，判断异常类型，同时将错误提示信息显示出来
			if (exception instanceof CommonException) {
				returnValue.addObject("message",exception.getMessage());
			} else {
				exception.printStackTrace();
				logger.info("异常信息[{}]", exception.getMessage());
				returnValue.addObject("message","系统异常！");
			}
			returnValue.setViewName(defaultErrorView);
			return returnValue;
		} else {
			PrintWriter writer =null;
			try {
				response.setContentType("application/json;charset=UTF-8");
				writer = response.getWriter();
				Message message = new Message();
				message.setSuccess(false);
				if (exception instanceof CommonException) {
					message.setMsg(exception.getMessage());
					if("nopermission".equals(exception.getMessage())){
						Map<String,Object> attributes=new HashMap<String, Object>();
						attributes.put("errorcode", 403);
						message.setAttributes(attributes);
					}
				} else {
					exception.printStackTrace();
					logger.info("异常信息[{}]", exception.getMessage());
					message.setMsg("系统异常！");
				}
				writer.write(JacksonUtil.objectToJson(message));
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				writer.close();
			}
		}

		return returnValue;
	}
}
