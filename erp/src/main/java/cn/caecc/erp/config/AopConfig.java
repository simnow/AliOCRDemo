/**
 * 
 */
package cn.caecc.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cn.caecc.erp.support.aop.monitor.MethodMonitor;

/**
 * @author weizhen
 *
 */
@Configuration
public class AopConfig {

	/**
	 * 
	 */
	public AopConfig() {
		// TODO Auto-generated constructor stub
	}

	@Bean(name = "methodMonitor")
	public MethodMonitor getMethodMonitor() {
		MethodMonitor methodMonitor = new MethodMonitor();
		return methodMonitor;
	}

}
