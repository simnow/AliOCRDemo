package cn.caecc.erp.modules.dao.ex.dto;

import java.util.Date;
import cn.caecc.erp.modules.dao.mybatis.entity.ContractGoodsRelationship;

public class ContractGoodsRelationshipDto extends ContractGoodsRelationship {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private String gname;//物品名称
	
    private String ccode;
    
    private String cprocessinstanceid;

    private Integer cpid;

    private Integer cuid;

    private Integer cdid;

    private String cname;

    private Date capplydate;

    private String cpartya;

    private String cpartyb;

    private Date cstarttime;

    private Date cendtime;

    private String csummary;

    private Integer ccreateuserid;

    private Date ccreatetime;

    private Integer cissuccess;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}
	
	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getCprocessinstanceid() {
		return cprocessinstanceid;
	}

	public void setCprocessinstanceid(String cprocessinstanceid) {
		this.cprocessinstanceid = cprocessinstanceid;
	}

	public Integer getCpid() {
		return cpid;
	}

	public void setCpid(Integer cpid) {
		this.cpid = cpid;
	}

	public Integer getCuid() {
		return cuid;
	}

	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}

	public Integer getCdid() {
		return cdid;
	}

	public void setCdid(Integer cdid) {
		this.cdid = cdid;
	}

	public Date getCapplydate() {
		return capplydate;
	}

	public void setCapplydate(Date capplydate) {
		this.capplydate = capplydate;
	}

	public String getCpartya() {
		return cpartya;
	}

	public void setCpartya(String cpartya) {
		this.cpartya = cpartya;
	}

	public String getCpartyb() {
		return cpartyb;
	}

	public void setCpartyb(String cpartyb) {
		this.cpartyb = cpartyb;
	}

	public Date getCstarttime() {
		return cstarttime;
	}

	public void setCstarttime(Date cstarttime) {
		this.cstarttime = cstarttime;
	}

	public Date getCendtime() {
		return cendtime;
	}

	public void setCendtime(Date cendtime) {
		this.cendtime = cendtime;
	}

	public String getCsummary() {
		return csummary;
	}

	public void setCsummary(String csummary) {
		this.csummary = csummary;
	}

	public Integer getCcreateuserid() {
		return ccreateuserid;
	}

	public void setCcreateuserid(Integer ccreateuserid) {
		this.ccreateuserid = ccreateuserid;
	}

	public Date getCcreatetime() {
		return ccreatetime;
	}

	public void setCcreatetime(Date ccreatetime) {
		this.ccreatetime = ccreatetime;
	}

	public Integer getCissuccess() {
		return cissuccess;
	}

	public void setCissuccess(Integer cissuccess) {
		this.cissuccess = cissuccess;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
