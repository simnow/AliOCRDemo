package cn.caecc.erp.support.shiro.filter;

import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.shiro.realm.MyAuthInfo;
import cn.caecc.erp.support.shiro.realm.MyAuthorizingRealm;
import cn.caecc.erp.support.util.JacksonUtil;

public class MyAccessControlFilter extends AccessControlFilter {
//	private static final Logger logger = LoggerFactory.getLogger(AjaxAccessControlFilter.class);

	@Autowired
	private MyAuthorizingRealm myAuthorizingRealm;
	
	//当每次访问时被拦截，判断是否被允许登录，允许返回true
    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object mappedValue) throws Exception {

		Subject subject = SecurityUtils.getSubject();

        if(null != subject) {
        	if (subject.isAuthenticated())
        	{
        		return Boolean.TRUE;
        	} else if (subject.isRemembered()) {
        		HttpServletRequest httpRequest = (HttpServletRequest)request;
        		String userRemember = httpRequest.getHeader("Use-Remember");
        		boolean flag = Boolean.parseBoolean(userRemember);
        		if (flag) {
        			try {
	        			MyAuthInfo myAuthInfo = (MyAuthInfo)subject.getPrincipals().getPrimaryPrincipal();
	        			myAuthorizingRealm.setSessionLoginFlag(myAuthInfo.getLoginUserId());
	            		return Boolean.TRUE;        			
        			}
        			catch (Exception ex) {
        				
        			}
        		}
        	}
        }       
        return Boolean.FALSE ;
        

    }
    //如果上一步中返回false，即拒绝登录，则执行该操作

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws Exception {
    	
    	response.setContentType("text/plain;charset=utf-8");
		PrintWriter writer = response.getWriter();
		Message result=new Message();
		result.setMsg("您未登录，请先登录");
		result.setSuccess(false);
		result.setErrorCode(Contants.ErrorCodeEnum.UnAuthentication);
		writer.write(JacksonUtil.objectToJson(result));
		writer.flush();
		writer.close();
		return false;
	
    }

}
