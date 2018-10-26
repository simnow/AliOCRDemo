package cn.caecc.erp.modules.dao.ex.dto;

import cn.caecc.erp.modules.dao.mybatis.entity.CardActivitiApply;

public class CardActivitiApplyDto  extends CardActivitiApply{

	
		private static final long serialVersionUID = 1L;

	
		private  String  deptname;
		
		private  String  username;

		public String getDeptname() {
			return deptname;
		}

		public void setDeptname(String deptname) {
			this.deptname = deptname;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}


	
	

}
