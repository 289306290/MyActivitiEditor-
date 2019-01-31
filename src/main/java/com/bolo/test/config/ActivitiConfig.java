package com.bolo.test.config;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Activiti Work Flow Config Class
 * 参考：https://www.activiti.org/userguide/#springintegration
 */
@Configuration
public class ActivitiConfig {

    /**
     * Acviviti配置类
     *
     * @param context
     * @param dataSource
     * @return
     */
    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(ApplicationContext context,
                                                                             DataSource dataSource,
                                                                             PlatformTransactionManager txManager) {

        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setApplicationContext(context);
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(txManager);
        configuration.setDatabaseSchemaUpdate("true");
        configuration.setAsyncExecutorActivate(false);
        configuration.isCreateDiagramOnDeploy(); // 部署流程时生成流程图
//        configuration.setBeans(); 设置供页面调用的bean

        //流程图字体
        configuration.setActivityFontName("宋体");
        configuration.setAnnotationFontName("宋体");
        configuration.setLabelFontName("宋体");

        return configuration;
    }

//    /**
//     * 流程引擎工厂
//     *
//     * @param processEngineConfiguration
//     * @return
//     */
//    @Bean
//    public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration processEngineConfiguration) {
//        ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
//        factoryBean.setProcessEngineConfiguration(processEngineConfiguration);
//        return factoryBean;
//    }
//
//    /**
//     * 仓库服务
//     *
//     * @param factoryBean
//     * @return
//     */
//    @Bean
//    public RepositoryService repositoryService(ProcessEngineFactoryBean factoryBean) {
//        ProcessEngineConfigurationImpl configuration = factoryBean.getProcessEngineConfiguration();
//        return configuration.getRepositoryService();
//    }
//
//    /**
//     * 运行时服务
//     *
//     * @param factoryBean
//     * @return
//     */
//    @Bean
//    public RuntimeService runtimeService(ProcessEngineFactoryBean factoryBean) {
//        ProcessEngineConfigurationImpl configuration = factoryBean.getProcessEngineConfiguration();
//        return configuration.getRuntimeService();
//    }
//
//    /**
//     * 获取任务服务
//     *
//     * @param factoryBean
//     * @return
//     */
//    @Bean
//    public TaskService taskService(ProcessEngineFactoryBean factoryBean) {
//        ProcessEngineConfigurationImpl configuration = factoryBean.getProcessEngineConfiguration();
//        return configuration.getTaskService();
//    }
//
//    /**
//     * 获取历史服务
//     *
//     * @param factoryBean
//     * @return
//     */
//    @Bean
//    public HistoryService historyService(ProcessEngineFactoryBean factoryBean) {
//        ProcessEngineConfigurationImpl configuration = factoryBean.getProcessEngineConfiguration();
//        return configuration.getHistoryService();
//    }
//
//    /**
//     * 获取管理服务
//     *
//     * @param factoryBean
//     * @return
//     */
//    @Bean
//    public ManagementService managementService(ProcessEngineFactoryBean factoryBean) {
//        ProcessEngineConfigurationImpl configuration = factoryBean.getProcessEngineConfiguration();
//        return configuration.getManagementService();
//    }
}
