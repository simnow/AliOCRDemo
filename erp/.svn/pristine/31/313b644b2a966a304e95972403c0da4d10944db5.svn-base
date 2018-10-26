package cn.caecc.erp.support.shiro.filter;

import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.JacksonUtil;

public class MyLogoutFilter extends LogoutFilter {
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		// 在这里执行退出系统前需要清空的数据
		response.setContentType("text/plain;charset=utf-8");
		Subject subject = getSubject(request, response);
		PrintWriter writer = response.getWriter();
		Message message = new Message();
		try {
			subject.logout();
		} catch (SessionException e) {
			
		} finally {
			writer.write(JacksonUtil.objectToJson(message));
			writer.flush();
			writer.close();
		}
		return false;
	}
}
