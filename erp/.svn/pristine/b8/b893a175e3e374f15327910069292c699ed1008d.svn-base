package cn.caecc.erp.support.statistics.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import cn.caecc.erp.modules.dao.mybatis.entity.LoginLog;
import cn.caecc.erp.modules.service.LoginLogService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.redis.service.RedisService;
import cn.caecc.erp.support.redis.service.RedissonService;
import cn.caecc.erp.support.statistics.entity.OnlineDepartmentUserEntity;
import cn.caecc.erp.support.statistics.service.StatisticsService;
import cn.caecc.erp.support.util.DateUtil;
import cn.caecc.erp.support.websocket.handler.MyWebSocketHandler;

/**
 * @author weizhen
 */
@Service
public class WebsocketStatisticsServiceImpl implements StatisticsService {
	private final static Logger logger = LoggerFactory.getLogger(WebsocketStatisticsServiceImpl.class);
	private Map<WebSocketSession, Integer> loginLogIdMap = new ConcurrentHashMap<WebSocketSession, Integer>();

	@Autowired
	private RedisService redisService;
	@Autowired
	private RedissonService redissonService;
	@Autowired
	private LoginLogService loginLogService;
	@Autowired @Qualifier("redisSessionDAO")
	private SessionDAO sessionDAO;
	
	@Override
	@SuppressWarnings("unchecked")
	public void addLoginUser(Object s) {
		// TODO Auto-generated method stub
		WebSocketSession session = (WebSocketSession) s;
		if (session == null) {
			return;
		}
		Integer loginUserId = (Integer) MyWebSocketHandler.getUserFromWebSocketSession(session);
		if (loginUserId == null) {
			return;
		}
		String strloginId = loginUserId.toString();
		String ipAddr = session.getRemoteAddress().getAddress().getHostAddress();
		LoginLog loginLog = new LoginLog();
		loginLog.setSessionid(session.getId());
		loginLog.setUid(loginUserId);
		loginLog.setLogintime(DateUtil.getcurrentDateTime());
		loginLog.setIpaddress(ipAddr);
		loginLogService.addLoginLog(loginLog);
		Integer loginLogId = loginLog.getId();
		loginLogIdMap.put(session, loginLogId);

		RLock redisLock = redissonService.getRLock(Contants.LOGINUSERID);
		try {
			redisLock.lock();
			/**
			 * 第一个String 为userid 第二个String 为loginlog表的id
			 */
			Map<String, Map<String, LoginLog>> redisLoginUserMap = null;
			if (redisService.hasKey(Contants.REDIS_LOGINUSERS_BY_WEBSOCKET)) {
				redisLoginUserMap = (Map<String, Map<String, LoginLog>>) redisService
						.get(Contants.REDIS_LOGINUSERS_BY_WEBSOCKET);
			} else {
				redisLoginUserMap = new HashMap<String, Map<String, LoginLog>>();
			}
			if (!redisLoginUserMap.containsKey(strloginId)) {
				redisLoginUserMap.put(strloginId, new HashMap<String, LoginLog>());
			}
			Map<String, LoginLog> internalLoginLogMap = redisLoginUserMap.get(strloginId);
			internalLoginLogMap.put(loginLogId.toString(), loginLog);
			redisService.set(Contants.REDIS_LOGINUSERS_BY_WEBSOCKET, redisLoginUserMap);
	//		System.out.println(JacksonUtil.objectToJson(redisLoginUserMap));

		} catch (Exception ex) {
			logger.warn(ex.getMessage());
		} finally {
			redisLock.unlock();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Map<String, LoginLog>> updateAndGetLoginUsers() {
		// TODO Auto-generated method stub
		RLock redisLock = redissonService.getRLock(Contants.LOGINUSERID);
		Map<String, Map<String, LoginLog>> redisLoginUserMap = null;
		try {
			redisLock.lock();
			if (redisService.hasKey(Contants.REDIS_LOGINUSERS_BY_WEBSOCKET)) {
				redisLoginUserMap = (Map<String, Map<String, LoginLog>>) redisService
						.get(Contants.REDIS_LOGINUSERS_BY_WEBSOCKET);
				
				
				
				List<String> toDeleteLoginUserKeyList = new ArrayList<String>();
				for (String loginUserKey : redisLoginUserMap.keySet()) { 
					Map<String, LoginLog> loginUserItem = redisLoginUserMap.get(loginUserKey);
					List<String> toDeleteKeyList = new ArrayList<String>();
					for (String loginLogKey : loginUserItem.keySet()) { 
						LoginLog loginLog = loginUserItem.get(loginLogKey);
						String sessionId = loginLog.getSessionid();
						try {
							sessionDAO.readSession(sessionId);
						} catch (Exception ex) {
							toDeleteKeyList.add(loginLogKey);
						}
					}
					for (String toDeleteKey : toDeleteKeyList) {
						loginUserItem.remove(toDeleteKey);
					}
					if (loginUserItem.size() == 0) {
						toDeleteLoginUserKeyList.add(loginUserKey);
					}
				}
				for (String toDeleteLoginUserKey : toDeleteLoginUserKeyList) {
					redisLoginUserMap.remove(toDeleteLoginUserKey);
				}
				
				redisService.set(Contants.REDIS_LOGINUSERS_BY_SESSION, redisLoginUserMap);
				
			}

		} catch (Exception ex) {
			logger.warn(ex.getMessage());
		} finally {
			redisLock.unlock();
		}
		return redisLoginUserMap;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void removeLoginUser(Object s) {

		// TODO Auto-generated method stub
		WebSocketSession session = (WebSocketSession) s;
		if (session == null) {
			return;
		}
		Integer loginUserId = MyWebSocketHandler.getUserFromWebSocketSession(session);
		if (loginUserId == null) {
			return;
		}
		String strLoginId = loginUserId.toString();
		Integer loginlogId = loginLogIdMap.get(session);
		if (!loginLogIdMap.containsKey(session)) {
			return;
		}
		loginLogIdMap.remove(session);
		loginLogService.updateLogoutLog(loginlogId);

		RLock redisLock = redissonService.getRLock(Contants.LOGINUSERID);
		try {
			redisLock.lock();
			if (redisService.hasKey(Contants.REDIS_LOGINUSERS_BY_WEBSOCKET)) {
				Map<String, Map<String, LoginLog>> redisLoginUserMap = (Map<String, Map<String, LoginLog>>) redisService
						.get(Contants.REDIS_LOGINUSERS_BY_WEBSOCKET);

				if (redisLoginUserMap != null && redisLoginUserMap.containsKey(strLoginId)) {
					Map<String, LoginLog> internalLoginLogMap = redisLoginUserMap.get(strLoginId);
					String strLoginlogId = loginlogId.toString();
					if (internalLoginLogMap != null && internalLoginLogMap.containsKey(loginlogId.toString())) {
						internalLoginLogMap.remove(strLoginlogId);
						if (internalLoginLogMap.size() == 0) {
							redisLoginUserMap.remove(strLoginId);
						}
						redisService.set(Contants.REDIS_LOGINUSERS_BY_WEBSOCKET, redisLoginUserMap);
					}
				}
	//			System.out.println(JacksonUtil.objectToJson(redisLoginUserMap));
			}

		} catch (Exception ex) {
			logger.warn(ex.getMessage());
		} finally {
			redisLock.unlock();

		}

	}

	@Override
	public List<OnlineDepartmentUserEntity> updateAndGetOnlineDepartmentUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSimilarOnlineUserCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
