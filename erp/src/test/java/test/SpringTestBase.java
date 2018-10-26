/**
 * 
 */
package test;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import cn.caecc.erp.modules.dao.mybatis.entity.MaterialGoodsRelationshipActivitiApply;
import cn.caecc.erp.modules.service.MaterialGoodsRelationshipActivitiApplyService;


/**
 * @author weizhen
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SpringTestBase extends AbstractJUnit4SpringContextTests {
	    
}