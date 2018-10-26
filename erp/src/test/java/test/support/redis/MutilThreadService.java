/**
 * 
 */
package test.support.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.redis.service.RedisService;
import cn.caecc.erp.support.redis.service.RedissonService;

/**
 * @author weizhen
 *
 */
public class MutilThreadService extends Thread{
	public static Integer count = 0;
	
	private RLock lock;
	public MutilThreadService( RedissonService redissonService)
	{
		lock = redissonService.getRLock("s");   
	}
	@Override  
	public void run() {  
		
		for (Integer i = 0; i < 10; i++) {  
			 //执行哪个类的哪个方法  
		//	RLock lock = redisUtil.getRLock("s");      
			
			lock.lock();

			count++;
		    
			lock.unlock();
			
		}     
		System.out.println(this.getId() + " : " + count.toString());  
	}
}
