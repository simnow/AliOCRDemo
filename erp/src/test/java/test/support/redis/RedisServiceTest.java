/**
 * 
 */
package test.support.redis;

import org.apache.shiro.codec.Base64;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import cn.caecc.erp.support.redis.service.RedisService;
import cn.caecc.erp.support.redis.service.RedissonService;
import test.SpringTestBase;

/**
 * @author weizhen
 *
 */
public class RedisServiceTest extends SpringTestBase {
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	RedisService redisService;
	@Autowired
	RedissonService redissonService;

	//@Test
	public void testGet1() {
		byte[] s = Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA==");
		System.out.print(s);
	}

	 @Test
	public void testGet() {
		// redisUtil.set("test", "1");

		for (int i = 0; i < 30; i++) {
			MutilThreadService mutilThreadService = new MutilThreadService(redissonService);
			taskExecutor.execute(mutilThreadService);
		}
		try {
			Thread.sleep(1000000);
		} catch (Exception ex) {

		}

	}
}
