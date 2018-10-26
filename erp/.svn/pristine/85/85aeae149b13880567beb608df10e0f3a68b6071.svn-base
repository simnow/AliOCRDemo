/**
 * 
 */
package cn.caecc.erp.support.redis.service.serviceImpl;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RDeque;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RQueue;
import org.redisson.api.RSet;
import org.redisson.api.RSortedSet;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.caecc.erp.support.redis.service.RedissonService;

/**
 * @author weizhen
 *
 */
@Service
public class RedissonServiceImpl implements RedissonService {

	@Autowired
	@Qualifier("redissonClient")
	private RedissonClient redissonClient;

	/**
	 * 
	 */
	public RedissonServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	// ---下面是redisson提供的方法------------------------------------------------------------------------
	/**
	 * 获得redis锁
	 * 
	 * @param objectName
	 * @return
	 */
	@Override
	public RLock getRLock(String objectName) {

		RLock rLock = redissonClient.getLock(objectName);
		return rLock;
	}

	@Override
	public <T> RBucket<T> getRBucket(String objectName) {
		RBucket<T> bucket = redissonClient.getBucket(objectName);
		return bucket;
	}

	/**
	 * 获取Map对象
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	@Override
	public <K, V> RMap<K, V> getRMap(String objectName) {
		RMap<K, V> map = redissonClient.getMap(objectName);
		return map;
	}

	/**
	 * 获取有序集合
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	@Override
	public <V> RSortedSet<V> getRSortedSet(String objectName) {
		RSortedSet<V> sortedSet = redissonClient.getSortedSet(objectName);
		return sortedSet;
	}

	/**
	 * 获取集合
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	@Override
	public <V> RSet<V> getRSet(String objectName) {
		RSet<V> rSet = redissonClient.getSet(objectName);
		return rSet;
	}

	/**
	 * 获取列表
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	@Override
	public <V> RList<V> getRList(String objectName) {
		RList<V> rList = redissonClient.getList(objectName);
		return rList;
	}

	/**
	 * 获取队列
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	@Override
	public <V> RQueue<V> getRQueue(String objectName) {
		RQueue<V> rQueue = redissonClient.getQueue(objectName);
		return rQueue;
	}

	/**
	 * 获取双端队列
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	@Override
	public <V> RDeque<V> getRDeque(String objectName) {
		RDeque<V> rDeque = redissonClient.getDeque(objectName);
		return rDeque;
	}

	/**
	 * 此方法不可用在Redisson 1.2 中 在1.2.2版本中 可用
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	/**
	 * public <V> RBlockingQueue<V> getRBlockingQueue(Redisson redisson,String
	 * objectName){ RBlockingQueue rb=redisson.getBlockingQueue(objectName); return
	 * rb; }
	 */

	/**
	 * 获取原子数
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	@Override
	public RAtomicLong getRAtomicLong(String objectName) {
		RAtomicLong rAtomicLong = redissonClient.getAtomicLong(objectName);
		return rAtomicLong;
	}

	/**
	 * 获取记数锁
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	@Override
	public RCountDownLatch getRCountDownLatch(String objectName) {
		RCountDownLatch rCountDownLatch = redissonClient.getCountDownLatch(objectName);
		return rCountDownLatch;
	}

	/**
	 * 获取消息的Topic
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	@Override
	public <M> RTopic<M> getRTopic(String objectName) {
		RTopic<M> rTopic = redissonClient.getTopic(objectName);
		return rTopic;
	}
}
