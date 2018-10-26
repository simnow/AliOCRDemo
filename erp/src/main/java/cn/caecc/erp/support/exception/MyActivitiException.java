package cn.caecc.erp.support.exception;

import cn.caecc.erp.support.constant.Contants;

public class MyActivitiException extends CommonException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Contants.ErrorCodeEnum errorCode;
	
	public MyActivitiException(String message){
		super(message);
	}
	
	public MyActivitiException(String message , Throwable cause){
		super(message , cause);
	}

	public Contants.ErrorCodeEnum getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Contants.ErrorCodeEnum errorCode) {
		this.errorCode = errorCode;
	}
	

}
