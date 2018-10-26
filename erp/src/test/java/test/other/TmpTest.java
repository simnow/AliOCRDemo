package test.other;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.caecc.erp.config.AopConfig;
import cn.caecc.erp.config.ContextConfig;
import cn.caecc.erp.config.MybatisConfig;
import cn.caecc.erp.config.RootConfig;
import cn.caecc.erp.config.ShiroConfig;
import cn.caecc.erp.config.TransactionConfig;
import cn.caecc.erp.config.WebConfig;
import cn.caecc.erp.modules.service.WellinfoService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, RootConfig.class, ContextConfig.class, MybatisConfig.class,AopConfig.class,ShiroConfig.class,TransactionConfig.class})
@WebAppConfiguration
public class TmpTest {
	 @Autowired
	 private WellinfoService service;
	 
	  @Test
	  public void testUser(){
	    System.out.println(service.selectDidList().size());
	  }
    
}
