package cn.caecc.erp.support.sms.entity;

import java.io.Serializable;

public class SmsCodeEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Code;
	
	private Long time;
	
	public SmsCodeEntity() {
		
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}
	
	

}
