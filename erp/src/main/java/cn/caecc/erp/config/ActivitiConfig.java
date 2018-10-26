/**
 * 
 */
package cn.caecc.erp.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.persistence.StrongUuidGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author weizhen
 *
 */
@Configuration
public class ActivitiConfig {

	/**
	 * 
	 */
	public ActivitiConfig() {
		// TODO Auto-generated constructor stub
	}

	@Bean(name = "uuidGenerator")
	public StrongUuidGenerator getUuidGenerator() {
		StrongUuidGenerator uuidGenerator = new StrongUuidGenerator();
		return uuidGenerator;
	}

	@Bean(name = "processEngineConfiguration")
	public SpringProcessEngineConfiguration getProcessEngineConfiguration(DataSource dataSource,
			DataSourceTransactionManager transactionManager) throws IOException {
		SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
		processEngineConfiguration.setDataSource(dataSource);
		processEngineConfiguration.setTransactionManager(transactionManager);
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		processEngineConfiguration.setHistory(HistoryLevel.FULL.getKey());
		processEngineConfiguration.setIdGenerator(getUuidGenerator());
		/*
		 * PathMatchingResourcePatternResolver resolver = new
		 * PathMatchingResourcePatternResolver();
		 * processEngineConfiguration.setDeploymentResources(resolver.getResources(
		 * "classpath:workflow/*"));
		 */
		return processEngineConfiguration;
	}

	@Bean(name = "processEngineFactory")
	public ProcessEngineFactoryBean getProcessEngineFactory(SpringProcessEngineConfiguration processEngineConfiguration)
			throws IOException {
		ProcessEngineFactoryBean processEngineFactory = new ProcessEngineFactoryBean();
		processEngineFactory.setProcessEngineConfiguration(processEngineConfiguration);
		return processEngineFactory;
	}

	@Bean(name = "repositoryService")
	public RepositoryService getRepositoryService(ProcessEngineFactoryBean processEngineFactory) throws Exception {
		return processEngineFactory.getObject().getRepositoryService();
	}

	@Bean(name = "runtimeService")
	public RuntimeService getRuntimeService(ProcessEngineFactoryBean processEngineFactory) throws Exception {
		return processEngineFactory.getObject().getRuntimeService();
	}

	@Bean(name = "identityService")
	public IdentityService getIdentityService(ProcessEngineFactoryBean processEngineFactory) throws Exception {
		return processEngineFactory.getObject().getIdentityService();
	}

	@Bean(name = "taskService")
	public TaskService getTaskService(ProcessEngineFactoryBean processEngineFactory) throws Exception {
		return processEngineFactory.getObject().getTaskService();
	}

	@Bean(name = "historyService")
	public HistoryService getHistoryService(ProcessEngineFactoryBean processEngineFactory) throws Exception {
		return processEngineFactory.getObject().getHistoryService();
	}
}
