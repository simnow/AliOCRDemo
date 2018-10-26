/**
 * 
 */
package test.support.sms;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.support.sms.service.SmsService;
import test.SpringTestBase;

/**
 * @author weizhen
 *
 */
public class SmsServiceImplTest extends SpringTestBase {
	@Autowired 
	SmsService smsService;
	
	//@Test
	public void testSend()
	{
		try
		{
		//	smsService.sendLoginSms("18511708369");
		//	smsService.sendModifyPasswordSms("18511708369");
		//	smsService.sendWorkflowApproveSms("18511708369");
			
			Map<Integer, User> loginUsers = null;
			
			loginUsers = new HashMap<Integer, User>();
			int userId = 1000000;
			if (!loginUsers.containsKey(userId))
			{
				loginUsers.put(userId, null);
				if (loginUsers.containsKey(userId))
				{
					System.out.println("s");
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
