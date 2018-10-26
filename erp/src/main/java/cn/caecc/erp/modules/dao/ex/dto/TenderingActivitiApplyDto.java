package cn.caecc.erp.modules.dao.ex.dto;

import java.util.Date;

import cn.caecc.erp.modules.dao.mybatis.entity.TenderingActivitiApply;

public class TenderingActivitiApplyDto extends TenderingActivitiApply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pcode;//立项编码
	
	private String pname;//立项名称
	
    private String pprocessinstanceid;//立项流程id
	
	private int pdid;//立项申请人部门id
	
	private int puid;//立项申请人id
	
	private String ptelephone;//立项申请人电话
	
	private String prelative;//立项合作相对人，相对单位
	
	private String pagent;//对方公司经办人
	
	private String ptelephone2;//对方公司经办人电话
	
	private double pmoney;//预计合同总金额
	
	private String psituation;//立项基本情况
	
	private int pcreateuserid;//立项创建人id
	
	private Date pcreatetime;//立项创建时间
	
	private int pissuccess;//立项流转状态
	
	private String uname;//申请人
	
	private String dname;//部门名称
	
	private String cname;//创建人

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPprocessinstanceid() {
		return pprocessinstanceid;
	}

	public void setPprocessinstanceid(String pprocessinstanceid) {
		this.pprocessinstanceid = pprocessinstanceid;
	}

	public int getPdid() {
		return pdid;
	}

	public void setPdid(int pdid) {
		this.pdid = pdid;
	}

	public int getPuid() {
		return puid;
	}

	public void setPuid(int puid) {
		this.puid = puid;
	}

	public String getPtelephone() {
		return ptelephone;
	}

	public void setPtelephone(String ptelephone) {
		this.ptelephone = ptelephone;
	}

	public String getPrelative() {
		return prelative;
	}

	public void setPrelative(String prelative) {
		this.prelative = prelative;
	}

	public String getPagent() {
		return pagent;
	}

	public void setPagent(String pagent) {
		this.pagent = pagent;
	}

	public String getPtelephone2() {
		return ptelephone2;
	}

	public void setPtelephone2(String ptelephone2) {
		this.ptelephone2 = ptelephone2;
	}

	public double getPmoney() {
		return pmoney;
	}

	public void setPmoney(double pmoney) {
		this.pmoney = pmoney;
	}

	public String getPsituation() {
		return psituation;
	}

	public void setPsituation(String psituation) {
		this.psituation = psituation;
	}

	public int getPcreateuserid() {
		return pcreateuserid;
	}

	public void setPcreateuserid(int pcreateuserid) {
		this.pcreateuserid = pcreateuserid;
	}

	public Date getPcreatetime() {
		return pcreatetime;
	}

	public void setPcreatetime(Date pcreatetime) {
		this.pcreatetime = pcreatetime;
	}

	public int getPissuccess() {
		return pissuccess;
	}

	public void setPissuccess(int pissuccess) {
		this.pissuccess = pissuccess;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
