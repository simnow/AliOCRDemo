package cn.caecc.erp.modules.dao.ex.dto;

import java.io.Serializable;

import cn.caecc.erp.modules.dao.mybatis.entity.Position;

public class PositionDto extends Position  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dname;
	
	private String cname;//创建人
	
	private String uname;//更新人

	public String getDname() {
		return dname;
	}

	
	/**
	 * @return the cname
	 */
	public String getCname() {
		return cname;
	}


	/**
	 * @param cname the cname to set
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}


	/**
	 * @return the uname
	 */
	public String getUname() {
		return uname;
	}


	/**
	 * @param uname the uname to set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}


	public void setDname(String dname) {
		this.dname = dname;
	}
	
}
