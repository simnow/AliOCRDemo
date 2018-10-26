package cn.caecc.erp.support.workflow.approver.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import cn.caecc.erp.modules.dao.mybatis.entity.User;

public class BaseApproverService {
	public List<User> preProcessUsers(List<User> userList) {
		if (userList == null) {
			return null;
		}
		List<User> newUserList = new ArrayList<User>();
		for (User user : userList) {
			User newUser = new User();
			newUser.setId(user.getId());
			newUser.setName(user.getName());
			newUserList.add(newUser);
		}
		return newUserList;
	}

}
