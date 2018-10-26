/**
 * 
 */
package cn.caecc.erp.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.shiro.filter.MyAccessControlFilter;
import cn.caecc.erp.support.shiro.filter.MyLogoutFilter;
import cn.caecc.erp.support.shiro.realm.MyAuthorizingRealm;
import cn.caecc.erp.support.shiro.session.listener.MySessionListener;

/**
 * @author weizhen
 *
 */
@Configuration
@PropertySource({Contants.REDIS_CONFIG, "classpath:shiro.properties"})
public class ShiroConfig {

	/**
	 * 
	 */
	public ShiroConfig() {
		// TODO Auto-generated constructor stub
	}

	@Value("${redis.hostName}")
	private String host;

	@Value("${redis.port}")
	private int port;

	@Value("${redis.password}")
	private String password;

	@Value("${shiro.globalSessionTimeout}")
	private int globalSessionTimeout;

	@Value("${shiro.rememberMeMaxAge}")
	private int rememberMeMaxAge;

	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return propertySourcesPlaceholderConfigurer;
	}

	@Bean(name = "redisManager")
	public RedisManager getRedisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(host);
		redisManager.setPort(port);
		redisManager.setPassword(password);
		return redisManager;
	}

	@Bean(name = "redisCacheManager")
	public RedisCacheManager getRedisCacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(getRedisManager());
		return redisCacheManager;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
		return lifecycleBeanPostProcessor;
	}

	@Bean(name = "defaultAdvisorAutoProxyCreator")
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean(name = "authorizationAttributeSourceAdvisor")
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(getSecurityManager());
		return authorizationAttributeSourceAdvisor;
	}

	@Bean(name = "mySessionListener")
	public MySessionListener getMySessionListener() {
		MySessionListener mySessionListener = new MySessionListener();
		return mySessionListener;
	}

	@Bean(name = "redisSessionDAO")
	public RedisSessionDAO getRedisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(getRedisManager());
		return redisSessionDAO;
	}

	@Bean(name = "jsessionID")
	public SimpleCookie getJsessionID() {
		SimpleCookie jsessionID = new SimpleCookie("UNIONJSESSIONID");
		return jsessionID;
	}

	@Bean(name = "rememberCookie")
	public SimpleCookie getRememberCookie() {
		SimpleCookie rememberCookie = new SimpleCookie("rememberMe");
		rememberCookie.setHttpOnly(true);
		rememberCookie.setMaxAge(rememberMeMaxAge);
		return rememberCookie;
	}

	@Bean(name = "sessionManager")
	public DefaultWebSessionManager getSessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(getRedisSessionDAO());
		sessionManager.setGlobalSessionTimeout(globalSessionTimeout);
		sessionManager.setSessionValidationScheduler(getSessionValidationScheduler(sessionManager));
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionIdCookie(getJsessionID());
		List<SessionListener> sessionListenerList = new ArrayList<SessionListener>();
		sessionListenerList.add(getMySessionListener());
		sessionManager.setSessionListeners(sessionListenerList);
		return sessionManager;
	}

	@Bean(name = "sessionValidationScheduler")
	public ExecutorServiceSessionValidationScheduler getSessionValidationScheduler(
			DefaultWebSessionManager sessionManager) {
		ExecutorServiceSessionValidationScheduler serviceSessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
		serviceSessionValidationScheduler.setInterval(globalSessionTimeout / 2);
		serviceSessionValidationScheduler.setSessionManager(sessionManager);
		return serviceSessionValidationScheduler;
	}

	@Bean(name = "myAuthorizingRealm")
	public MyAuthorizingRealm getMyAuthorizingRealm() {
		MyAuthorizingRealm myAuthorizingRealm = new MyAuthorizingRealm();
		return myAuthorizingRealm;
	}

	@Bean(name = "myAccessControlFilter")
	public MyAccessControlFilter getMyAccessControlFilter() {
		MyAccessControlFilter myAccessControlFilter = new MyAccessControlFilter();
		return myAccessControlFilter;
	}

	@Bean(name = "myLogoutFilter")
	public MyLogoutFilter getMyLogoutFilter() {
		MyLogoutFilter myLogoutFilter = new MyLogoutFilter();
		return myLogoutFilter;
	}

	@Bean(name = "rememberMeManager")
	public CookieRememberMeManager getRememberMeManager() {
		CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
		rememberMeManager.setCipherKey((Base64.decode("4AvVhmFLUs0KTA3Kprsdag==")));
		rememberMeManager.setCookie(this.getRememberCookie());
		return rememberMeManager;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getSecurityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(getMyAuthorizingRealm());
		securityManager.setCacheManager(getRedisCacheManager());
		securityManager.setSessionManager(getSessionManager());
		securityManager.setRememberMeManager(getRememberMeManager());
		return securityManager;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilter() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(getSecurityManager());
		Map<String, Filter> filters = new LinkedHashMap<>();
		filters.put("authc", getMyAccessControlFilter());
		filters.put("logout", getMyLogoutFilter());
		shiroFilter.setFilters(filters);

		Map<String, String> filterMap = new LinkedHashMap<>();
		filterMap.put("/api/workflow/runtime/tasks", "anon");
		filterMap.put("/api/user/login", "anon");
		filterMap.put("/api/check", "anon");
		filterMap.put("/api/captcha", "anon");
		filterMap.put("/api/user/phone-verification-code*", "anon");
		filterMap.put("/api/user/phone-verification-code-return*", "anon");
		filterMap.put("/api/logout", "logout");
		filterMap.put("/api/**", "authc");
		shiroFilter.setFilterChainDefinitionMap(filterMap);

		return shiroFilter;
	}

}
