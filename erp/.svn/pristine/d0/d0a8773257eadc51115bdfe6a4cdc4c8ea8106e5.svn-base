/**
 * 
 */
package cn.caecc.erp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import cn.caecc.erp.support.task.service.serviceImpl.DailyJobTask;

/**
 * @author weizhen
 *
 */
@Configuration
@PropertySource("classpath:task.properties")
public class TaskConfig {

	/**
	 * 
	 */
	public TaskConfig() {
		// TODO Auto-generated constructor stub
	}

	@Value("${task.corePoolSize}")
	private int corePoolSize;

	@Value("${task.maxPoolSize}")
	private int maxPoolSize;

	@Value("${task.cronExpression}")
	private String cronExpression;

	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return propertySourcesPlaceholderConfigurer;
	}

	@Bean(name = "executor")
	public ThreadPoolTaskExecutor getExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		// executor.setQueueCapacity(500);
		return executor;
	}

	@Bean(name = "dailyJobTask")
	public DailyJobTask getDailyJobTask() {
		DailyJobTask dailyJobTask = new DailyJobTask();
		return dailyJobTask;
	}

	@Bean(name = "jobDetail")
	public MethodInvokingJobDetailFactoryBean getJobDetail() {
		MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
		jobDetail.setTargetObject(getDailyJobTask());
		jobDetail.setTargetMethod("service");
		return jobDetail;
	}

	@Bean(name = "cronTrigger")
	public CronTriggerFactoryBean getCronTrigger() {
		CronTriggerFactoryBean cronTrigger = new CronTriggerFactoryBean();
		cronTrigger.setJobDetail(this.getJobDetail().getObject());
		cronTrigger.setCronExpression(cronExpression);
		return cronTrigger;
	}

	@Bean(name = "schedulerFactory")
	public SchedulerFactoryBean getSchedulerFactory() {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setTriggers(getCronTrigger().getObject());
		schedulerFactory.setTaskExecutor(getExecutor());
		return schedulerFactory;
	}

}
