package com.partymasses.support.security;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.partymasses.modules.dao.mybatis.entity.Function;
import com.partymasses.modules.dao.mybatis.entity.User;
import com.partymasses.modules.service.FunctionService;
import com.partymasses.modules.service.UserService;
import com.partymasses.support.constant.Contants;;

public class ManageRealmAuthorizingRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	@Autowired
	private FunctionService functionService;
	
	/**
	 * 自己先进行模拟后台固定参数
	 */
	
//    private static final String USER_NAME = "gai";  
//    private static final String PASSWORD = "123456";  

    /* 
     * 
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) { 
    	SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    	User userInfo=(User) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSER);
    	// 处理用户
    	Set<String> rolesSet = new HashSet<String>(); // 角色
    	Set<String> PermissionsSet = new HashSet<String>(); // 权限
    	if(userInfo==null){
    		rolesSet.add("guest");// 用户
			PermissionsSet.add("guest");
    	}else{
    		//查询菜单列表
    		List<Function> perlist=functionService.getByUnitID(userInfo.getUnitid());
    		for(Function fun:perlist){
    			PermissionsSet.add(""+fun.getId());
    		}
			if(Contants.SUPERADMIN.equals(userInfo.getRoletype())){
				rolesSet.add(Contants.SUPERADMIN);// 客户权限
			}else if(Contants.ADMIN.equals(userInfo.getRoletype())){
				rolesSet.add(Contants.ADMIN);// 管理员
			}else if(Contants.USER.equals(userInfo.getRoletype())){
				rolesSet.add(Contants.USER);// 用户
			}
		}
    	info.setRoles(rolesSet);
    	info.setStringPermissions(PermissionsSet);
        return info;  
    }

    /* 
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User userInfo=null;
        //通过手机号查询数据库
        userInfo= userService.getUserByNumber(token.getUsername());
        if(userInfo==null){
        	throw new UnknownAccountException("您的账号尚未注册，请先注册!");
        }
        //对比密码
        if(!userInfo.getPassword().equals(new String(token.getPassword()))){
        	throw new CredentialsException("您输入的密码有误，请输入正确的登录密码");
        }
        	// 更新session
    	SecurityUtils.getSubject().getSession().setAttribute(Contants.LOGINUSER, userInfo);
    	//注意
        return new SimpleAuthenticationInfo(userInfo.getMobile(),userInfo.getPassword() , getName());  
      
    }
}