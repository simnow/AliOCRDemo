/**
 * 
 */
package cn.caecc.erp.support.redis.service;

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

/**
 * @author weizhen
 *
 */
public interface RedissonService {
	/// --------redssion-----------------------------
	public RLock getRLock(String objectName);

	public <T> RBucket<T> getRBucket(String objectName);

	/**
	 * 获取Map对象
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public <K, V> RMap<K, V> getRMap(String objectName);

	/**
	 * 获取有序集合
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public <V> RSortedSet<V> getRSortedSet(String objectName);

	/**
	 * 获取集合
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public <V> RSet<V> getRSet(String objectName);

	/**
	 * 获取列表
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public <V> RList<V> getRList(String objectName);

	/**
	 * 获取队列
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public <V> RQueue<V> getRQueue(String objectName);

	/**
	 * 获取双端队列
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public <V> RDeque<V> getRDeque(String objectName);

	/**
	 * 获取原子数
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public RAtomicLong getRAtomicLong(String objectName);

	/**
	 * 获取记数锁
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public RCountDownLatch getRCountDownLatch(String objectName);

	/**
	 * 获取消息的Topic
	 * 
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public <M> RTopic<M> getRTopic(String objectName);

}
