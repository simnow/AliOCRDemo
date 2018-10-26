/**
 * 
 */
package cn.caecc.erp.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import cn.caecc.erp.support.exception.MyExceptionHandler;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @author weizhen
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cn.caecc.erp.controller", useDefaultFilters = false, includeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
public class WebConfig extends WebMvcConfigurationSupport {

	/**
	 * 
	 */
	public WebConfig() {
		// TODO Auto-generated constructor stub
	}

	@Bean(name = "stringHttpMessageConverter")
	public StringHttpMessageConverter getStringHttpMessageConverter() {
		return new StringHttpMessageConverter();
	}

	@Bean(name = "mappingJackson2HttpMessageConverter")
	public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.parseMediaType("text/html;charset=UTF-8"));
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
		return mappingJackson2HttpMessageConverter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(getStringHttpMessageConverter());
		converters.add(getMappingJackson2HttpMessageConverter());
		super.configureMessageConverters(converters);
	}

	@Bean(name = "defaultAdvisorAutoProxyCreator")
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("utf-8");
		multipartResolver.setMaxUploadSize(10485760); // 最大上傳長度10M
		return multipartResolver;
	}

	@Bean(name = "handlerExceptionResolver")
	public MyExceptionHandler getHandlerExceptionResolver() {
		MyExceptionHandler handlerExceptionResolver = new MyExceptionHandler();
		return handlerExceptionResolver;
	}
}
