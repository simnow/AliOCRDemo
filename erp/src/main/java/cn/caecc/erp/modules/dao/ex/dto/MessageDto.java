package cn.caecc.erp.modules.dao.ex.dto;

import cn.caecc.erp.modules.dao.mybatis.entity.Message;

public class MessageDto extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String messageTypeName;

	public String getMessageTypeName() {
		return messageTypeName;
	}

	public void setMessageTypeName(String messageTypeName) {
		this.messageTypeName = messageTypeName;
	}
}
