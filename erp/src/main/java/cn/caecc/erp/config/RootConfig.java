/**
 * 
 */
package cn.caecc.erp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author weizhen
 *
 */
@Configuration
@ComponentScan(basePackages = "cn.caecc.erp", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
public class RootConfig {

	/**
	 * 
	 */
	public RootConfig() {
		// TODO Auto-generated constructor stub
	}

}
