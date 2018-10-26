package cn.caecc.erp.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.JacksonUtil;

/**
 * @author weizhen
 *
 */
@Controller
public class BaseController {
	private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

	@ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
	public String authenticationException(HttpServletRequest request, HttpServletResponse response) {
		/*
		try {
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter writer = response.getWriter();
			Message result = new Message();
			result.setMsg("您未登录，请先登录");
			result.setSuccess(false);
			result.setErrorCode(Contants.ErrorCodeEnum.UnAuthentication);

			writer.write(JacksonUtil.objectToJson(result));
			writer.flush();
			writer.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		*/
		return null;
	}

	@ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
	public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter writer = response.getWriter();
			Message result = new Message();
			result.setMsg("您没有相应功能权限访问，请联系管理员到人力管理进行角色调整");
			result.setSuccess(false);
			result.setErrorCode(Contants.ErrorCodeEnum.UnAuthorization);

			writer.write(JacksonUtil.objectToJson(result));

			writer.flush();
			writer.close();
			return null;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		return null;

	}

}
