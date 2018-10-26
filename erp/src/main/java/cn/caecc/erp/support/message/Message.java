package cn.caecc.erp.support.message;

import java.util.Map;

import cn.caecc.erp.support.constant.Contants;
public class Message {
	public Message(){
		
	}
	public Message(boolean bool,String message){
		this.success=bool;
		this.msg=message;
	}
    private boolean success = true;// 是否成功
    
    private Contants.ErrorCodeEnum errorCode = Contants.ErrorCodeEnum.NoError;// 错误码


    private String msg = "操作成功";// 提示信息

    private Object obj = null;// 其他信息

    private Map<String, Object> attributes;// 其他参数

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    
    public Contants.ErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Contants.ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
    }

    
    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
