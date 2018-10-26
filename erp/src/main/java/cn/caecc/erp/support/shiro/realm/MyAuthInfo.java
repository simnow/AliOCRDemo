package cn.caecc.erp.support.shiro.realm;

import java.io.Serializable;

import org.crazycake.shiro.AuthCachePrincipal;

public class MyAuthInfo implements AuthCachePrincipal, Serializable {
	
	private static final long serialVersionUID = 1L;	

	private Integer loginUserId;

	public MyAuthInfo(Integer id)
	{
		loginUserId = id;
	}
	@Override
	public String getAuthCacheKey() {
		// TODO Auto-generated method stub
			String redisKey = loginUserId.toString();
		//	redisKey = null; 
			return redisKey;
	}

	public Integer getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(Integer loginUserId) {
		this.loginUserId = loginUserId;
	}
	

}
