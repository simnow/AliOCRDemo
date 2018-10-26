package cn.caecc.erp.support.websocket.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PingMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import cn.caecc.erp.support.constant.Contants;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;

/**
 * @author weizhen
 *
 */
public class MyWebSocketHandler implements WebSocketHandler {
	/**
	 * 登录用户的映射 Integer:用户id
	 */
	private final static Map<Integer, Vector<WebSocketSession>> websocketLoginSessionMap = new ConcurrentHashMap<Integer, Vector<WebSocketSession>>();;

	/*
	@Autowired
	@Qualifier("websocketStatisticsServiceImpl")
	private StatisticsService statisticsService;
	*/
	/**
	 * 获取用户
	 * 
	 * @param session
	 * @return
	 */
	public static Integer getUserFromWebSocketSession(WebSocketSession session) {
		Integer loginUserId = null;
		Map<String, Object> attributes = session.getAttributes();
		if (attributes.containsKey(Contants.LOGINUSERID)) // 如果包含登录信息
		{
			loginUserId = (Integer) attributes.get(Contants.LOGINUSERID);
		}
		return loginUserId;
	}

	/**
	 * 发送keepalive报文
	 */
	public static void sendKeeplive() {

		for (Integer userIdKey : websocketLoginSessionMap.keySet()) {
			Vector<WebSocketSession> internalSessionVector = websocketLoginSessionMap.get(userIdKey);
			if (internalSessionVector != null && internalSessionVector.size() > 0) {
				for (WebSocketSession session : internalSessionVector) {
					byte[] bs = new byte[1];
					bs[0] = 0;
					ByteBuffer byteBuffer = ByteBuffer.wrap(bs);
					PingMessage pingMessage = new PingMessage(byteBuffer);
					try {
						session.sendMessage(pingMessage);
					} catch (Exception ex) {
					}
				}
			}
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		Integer loginUserId = getUserFromWebSocketSession(session);
		if (loginUserId != null) // 如果包含登录信息
		{

			if (websocketLoginSessionMap.containsKey(loginUserId)) {
				websocketLoginSessionMap.get(loginUserId).add(session);
			} else {
				Vector<WebSocketSession> vertor = new Vector<WebSocketSession>();
				vertor.add(session);
				websocketLoginSessionMap.put(loginUserId, vertor);
			}

			//statisticsService.addLoginUser(session);
		}
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		Integer loginUserId = getUserFromWebSocketSession(session);
		if (loginUserId != null) {
			Object obj = message.getPayload();
			sendMessage(loginUserId, obj.toString());
		}
	}

	private void onClose(WebSocketSession session) {
		Integer loginUserId = getUserFromWebSocketSession(session);
		if (loginUserId == null) {
			return;
		}
		if (websocketLoginSessionMap.containsKey(loginUserId)) {
			Vector<WebSocketSession> internalSessionVector = websocketLoginSessionMap.get(loginUserId);

			if (internalSessionVector != null && internalSessionVector.size() > 0) {
				internalSessionVector.remove(session);
				if (internalSessionVector.size() == 0) {
					websocketLoginSessionMap.remove(loginUserId);
				}
			}
		}

		//statisticsService.removeLoginUser(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		onClose(session);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
		// TODO Auto-generated method stub
		if (session.isOpen()) {
			session.close();
		}
		onClose(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

	public void sendMessage(Integer userId, String msg) {

		Vector<WebSocketSession> websocketVertor = websocketLoginSessionMap.get(userId);
		if (websocketVertor != null && websocketVertor.size() > 0) {
			for (WebSocketSession websocket : websocketVertor) {
				try {
					TextMessage message = new TextMessage(msg);
					synchronized(websocketLoginSessionMap) {

						if (websocket.isOpen()) {
							websocket.sendMessage(message);
						}
					} 
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}