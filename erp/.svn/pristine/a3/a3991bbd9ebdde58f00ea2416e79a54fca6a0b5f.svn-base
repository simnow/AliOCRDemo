package cn.caecc.erp.modules.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.dto.MessageDto;
import cn.caecc.erp.modules.dao.mybatis.entity.Message;

public interface MessageService {
	
	public enum StatusEnum {
		UnRead(0), // 未读
		HasRead(1); // 已读
		private int value = 0;

		/**
		 * @return the value
		 */
		public int getValue() {
			return value;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(int value) {
			this.value = value;
		}

		private StatusEnum(int value) { 
			this.value = value;
		}
	}
	
	public void createWorkflowMessage(int userId, String title, String content);
	
	public void createContractMessage(int userId, String title, String content);
	
	public void createConferenceMessage(int userId, String title, String content);
	
	public int updateMessage(Message message);
		
	public int updateUnReadStatus(int messageId);
	
	public int updateUnReadStatus(List<Integer> messageIdList);
	
	public int updateHasReadStatus(int messageId);
	
	public int updateHasReadStatus(List<Integer> messageIdList);
	
	public int getUnreadMessageCountById();
	
	public Message getMessageById(int messageId);

	public PageInfo<MessageDto> getMyMessageList(Integer status, Integer messageTypeId, int pageNo, int pageSize);	
	
	public int deleteMessageById(int messageId);
	
	public int deleteMessageByIds(List<Integer> messageIdList);


}
