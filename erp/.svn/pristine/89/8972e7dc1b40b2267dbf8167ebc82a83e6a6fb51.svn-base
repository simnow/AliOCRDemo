/**
 * 
 */
package test.support.email;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.caecc.erp.support.email.service.EmailService;
import test.SpringTestBase;

/**
 * @author weizhen
 *
 */
public class EmailServiceImplTest extends SpringTestBase {
	@Autowired 
	EmailService emailService;
	
	@Test
	public void testSend()
	{
		try
		{
			emailService.sendMail("dddd", "ddd", false, "wei_zhen@163.com");
		//	smsService.sendLoginSms("18511708369");
		//	smsService.sendModifyPasswordSms("18511708369");
		//	smsService.sendWorkflowApproveSms("18511708369");
			/*
			
			List<String> a = new ArrayList<String>();
			a.add("s1");
			a.add("s2");
			a.add("s3");
			a.add("s4");
			a.add("s5");
			a.subList(1, a.size() - 1).clear();
			System.out.println(a);
*/
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
}
