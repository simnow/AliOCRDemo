/**
 * 
 */
package cn.caecc.erp.support.captcha.service.entity;

import java.io.Serializable;


/**
 * @author weizhen
 *
 */
public class CaptchaEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	
	private String code;
	
	private Long time;
	
	public CaptchaEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the time
	 */
	public Long getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Long time) {
		this.time = time;
	}

	
	
}
