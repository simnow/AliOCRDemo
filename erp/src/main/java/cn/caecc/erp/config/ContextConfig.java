/**
 * 
 */
package cn.caecc.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import cn.caecc.erp.support.context.springcontext.SpringContextUtil;

/**
 * @author weizhen
 *
 */
@Configuration
public class ContextConfig {

	/**
	 * 
	 */
	public ContextConfig() {
		// TODO Auto-generated constructor stub
	}

	@Bean(name = "springContextUtil")
	@Lazy(value = false)
	public SpringContextUtil getSpringContextUtil() {
		SpringContextUtil springContextUtil = new SpringContextUtil();
		return springContextUtil;
	}

}
