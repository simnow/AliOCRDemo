package cn.caecc.erp.modules.dao.ex.dto;

import java.util.List;

public class AccruingAmountsDto {

	private Integer did;
	
	private List<String> gname;

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public List<String> getGname() {
		return gname;
	}

	public void setGname(List<String> gname) {
		this.gname = gname;
	}
	
}
