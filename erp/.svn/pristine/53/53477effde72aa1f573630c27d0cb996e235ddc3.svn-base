package cn.caecc.erp.support.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.JacksonUtil;


public class MyExceptionHandler extends ExceptionHandlerExceptionResolver {

	private final static Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

	protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod, Exception exception) {
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8"); 
			PrintWriter writer = response.getWriter();
			Message message = new Message();
			message.setSuccess(false);
			if (exception instanceof CommonException) {
				message.setMsg(exception.getMessage());
			} 
			else if (exception instanceof UnauthorizedException) {
				message.setMsg("您没有相应功能的访问权限，请联系管理员进行角色调整");
				message.setErrorCode(Contants.ErrorCodeEnum.UnAuthorization);
			}
			else {
				logger.info("异常信息[{}]", exception.getMessage());
				message.setMsg("系统异常！");
				message.setErrorCode(Contants.ErrorCodeEnum.Unknow);
			}
			String json = JacksonUtil.objectToJson(message);
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return new ModelAndView();
	}
}
