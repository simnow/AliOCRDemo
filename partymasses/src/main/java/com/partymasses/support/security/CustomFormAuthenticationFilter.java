package com.partymasses.support.security;

/**
 * 
 */
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.partymasses.support.message.Message;
import com.partymasses.support.util.JacksonUtil;

/**
 * 自定义登录
 * 
 * @author
 * 
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
	private static final Logger logger = LoggerFactory.getLogger(CustomFormAuthenticationFilter.class);

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password == null) {
			password = "";
		}
		boolean rememberMe = false;
		String host = getHost(request);
		return new CustomUsernamePasswordToken(username, password.toCharArray(), rememberMe, host);
	}

	/*
	 * 主要是针对登入成功的处理方法。对于请求头是AJAX的之间返回JSON字符串。
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		if (!isAjax(request)) {// 不是ajax请求
			issueSuccessRedirect(request, response);
		} else {
			String url = "";
			try {
				url = WebUtils.getSavedRequest(request).getRequestUrl();
			} catch (Exception ex) {
				logger.error("获取未登录路径异常", ex);
			}
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			Message message=new Message();
			message.setMsg("登录成功");
//			out.println("{\"code\":\"true\",\"message\":\"登录成功\",\"url\":\"" + url + "\"}");
			out.println(JacksonUtil.objectToJson(message));
			out.flush();
			out.close();
		}
		return false;
	}

	/**
	 * 主要是处理登入失败的方法
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		PrintWriter out = null;
		if (!isAjax(request)) {// 不是ajax请求
			setFailureAttribute(request, e);
			return true;
		}
		try {
			response.setContentType("text/plain;charset=utf-8");
			out = response.getWriter();
			String message = "";
			logger.info("登录错误【{}】", e.getMessage());
			if (message.indexOf("Exception") >= 0) {
				message = "系统正在维护中，请稍后再试";
			} else {
				message = e.getMessage();
			}
			Message result=new Message();
			result.setMsg("登录失败:"+message);
			result.setSuccess(false);
//			out.println("{\"code\":\"false\",\"message\":\"" + message + "\"}");
			out.println(JacksonUtil.objectToJson(result));
			out.flush();
			out.close();
		} catch (IOException e1) {
			logger.info("登录失败【{}】", e1.getMessage());
		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
		return false;
	}

	/**
	 * 所有请求都会经过的方法。
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				if (logger.isTraceEnabled()) {
					logger.trace("Login submission detected.  Attempting to execute login.");
				}
				HttpServletRequest rq = (HttpServletRequest) request;
				HttpSession session = rq.getSession(false);
				if (session == null) {
					session = rq.getSession();
				}
				response.setContentType("text/plain;charset=utf-8");
				return executeLogin(request, response);
			} else {
				if (logger.isTraceEnabled()) {
					logger.trace("Login page view.");
				}
				response.setContentType("text/plain;charset=utf-8");
				PrintWriter out = response.getWriter();
				Message result=new Message();
				result.setMsg("请先进行登录:");
				result.setSuccess(false);
				out.println(JacksonUtil.objectToJson(result));
//				out.println("{\"code\":false,\"message\":\"tologin\"}");
				out.flush();
				out.close();
				return false;
			}
		} else {
			if (logger.isTraceEnabled()) {
				logger.trace("Attempting to access a path which requires authentication.  Forwarding to the "
						+ "Authentication url [" + getLoginUrl() + "]");
			}
			if (!isAjax(request)) {// 不是ajax请求
				saveRequestAndRedirectToLogin(request, response);
			} else {
				response.setContentType("text/plain;charset=utf-8");
				PrintWriter out = response.getWriter();
				Message result=new Message();
				result.setMsg("请先进行登录:");
				result.setSuccess(false);
				out.println(JacksonUtil.objectToJson(result));
//				out.println("{\"code\":false,\"message\":\"login\"}");
				out.flush();
				out.close();
			}
			return false;
		}
	}

	private boolean isAjax(ServletRequest request) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		return "XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"));
	}

}
