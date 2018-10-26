package cn.caecc.erp.modules.service.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.modules.dao.ex.dto.DepartmentDto;
import cn.caecc.erp.modules.dao.ex.dto.UserDto;
import cn.caecc.erp.modules.dao.ex.mapper.DepartmentExMapper;
import cn.caecc.erp.modules.dao.ex.mapper.PositionExMapper;
import cn.caecc.erp.modules.dao.ex.mapper.UserExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.Role;
import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.modules.dao.mybatis.entity.UserExample;
import cn.caecc.erp.modules.dao.mybatis.entity.UserExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.entity.UserRoleRelationshipKey;
import cn.caecc.erp.modules.dao.mybatis.mapper.PositionMapper;
import cn.caecc.erp.modules.dao.mybatis.mapper.UserMapper;
import cn.caecc.erp.modules.service.RoleService;
import cn.caecc.erp.modules.service.UserRoleRelationshipService;
import cn.caecc.erp.modules.service.UserService;
import cn.caecc.erp.support.shiro.realm.MyAuthorizingRealm;
import cn.caecc.erp.support.util.DateUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private DepartmentExMapper deptMentExMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserExMapper userExMapper;
	@Autowired
	private UserRoleRelationshipService urService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PositionMapper positionMapper;
	@Autowired
	private PositionExMapper positionExMapper;


	/**
	 *
	 * 新建用户 param user return Message
	 */
	@Override
	public int createUser(User user) {
		int result = 0;
		if (user != null) {

			User oldUser = this.findByLoginName(user.getLoginname());
			if (oldUser != null) {
				throw new CommonException("该用户名已存在");
			} else {
				result = userMapper.insert(user);
				if (result > 0) {
					User newUser = this.findByLoginName(user.getLoginname());
					List<Role> list = roleService.getRoleByName(Contants.USERROLE);
					if (newUser != null && list.size() == 1) {
						UserRoleRelationshipKey userRoleRelationshipKey = new UserRoleRelationshipKey();
						userRoleRelationshipKey.setId(newUser.getId());
						userRoleRelationshipKey.setRid(list.get(0).getId());
						result = urService.add(userRoleRelationshipKey);
						if (result > 0) {
							return result;
						}
					} else {
						this.delByLoginName(user.getLoginname());
						throw new CommonException("系统异常，稍后再试");
					}
				} else {
					throw new CommonException("创建失败");
				}
			}
		}
		return result;
	}

	/**
	 * 修改用户信息 param user return Message
	 */
	@Override
	public int update(User user) {
		//只保存不为空的字段
		int result = userMapper.updateByPrimaryKey(user);
		return result;
	}

	/**
	 * 删除用户 param userId return Message
	 */
	@Override
	public int delById(int userId) {
		return userMapper.deleteByPrimaryKey(userId);
	}

	/**
	 * 查询用户信息 param loginName return User
	 */
	@Override
	public User findByLoginName(String loginName) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andLoginnameEqualTo(loginName);
		List<User> list = userMapper.selectByExample(userExample);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public User findByTelphone(String telphone) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andTelephoneEqualTo(telphone);
		List<User> list = userMapper.selectByExample(userExample);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public User findByUnitTelephone(String telphone) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUnittelephoneEqualTo(telphone);
		List<User> list = userMapper.selectByExample(userExample);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public User findByEmail(String email) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andEmailEqualTo(email);
		List<User> list = userMapper.selectByExample(userExample);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * 用户登录 param loginname password return Message
	 */
	@Override
	public User login(String loginName, String password) {
		MyAuthorizingRealm.login(loginName, password);
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
		UserDto userInfo = urService.findUrByUserId(loginUserId);
		return userInfo;
	}
	
	@Override
	public User findById(int id) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andIdEqualTo(id);
		List<User> list = userMapper.selectByExample(userExample);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	/**
	 * 查询用户邮箱 param userId return String
	 */
	@Override
	public String findEmailById(int userId) {
		User user = findById(userId);
		return user.getEmail();
	}

	/**
	 * 获取用户列表 return List<User>
	 */
	@Override
	public List<User> getList() {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andIdIsNotNull();
		List<User> list = userMapper.selectByExample(userExample);
		return list;
	}

	/**
	 * 模糊查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param userName
	 * @return
	 */
	@Override
	public PageInfo<UserDto> findByNameLike(int pageNo, int pageSize, String userName) {
		PageHelper.startPage(pageNo, pageSize);
		List<UserDto> list = userExMapper.findByNameLike(userName);
		PageInfo<UserDto> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	@Override
	public PageInfo<UserDto> findContactByNameLike(int pageNo,int pageSize,String userName) {
		PageHelper.startPage(pageNo, pageSize);
		List<UserDto> list = userExMapper.findByNameLike(userName);
		for (User user : list) {
			this.generalUserContactInfo(user);
		}
		PageInfo<UserDto> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	/**
	 * 联表查询用户列表（分页） param pageno pagesize return List<UserDto>
	 */
	@Override
	public PageInfo<UserDto> findPageList(int pageNo, int pageSize, int did) {
		PageHelper.orderBy("CreateTime DESC");
		PageHelper.startPage(pageNo, pageSize);
		UserDto userDto = new UserDto();
		userDto.setDid(did);
		List<UserDto> list = userExMapper.findUserList(userDto);
		PageInfo<UserDto> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	@Override
	public PageInfo<UserDto> findContactPageList(int pageNo, int pageSize, int did) {
		PageHelper.orderBy("CreateTime DESC");
		PageHelper.startPage(pageNo, pageSize);
		UserDto userDto = new UserDto();
		userDto.setDid(did);
		List<UserDto> list = userExMapper.findUserList(userDto);
		for (User user : list) {
			this.generalUserContactInfo(user);
		}
		PageInfo<UserDto> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public int insertUserList(List<User> list) throws Exception {
		int result = userExMapper.insertUsers(list);
		return result;
	}
	
	@Override
	public void updateUsersOfDepartment(int deptid, String ids) {

		// 拆分用户数组存在则重新分配
		String idArray[] = ids.split(",");
		List<Integer> idsList = changeArrayToList(idArray);
		// 如果传入被修改人数组存在 重新进行分配

		// TODO Auto-generated method stub
		// 判断该部门下是否leader
		List<User> leaderList = getDeptusers(deptid, Contants.LEADERROLE, null);
		if (leaderList != null && leaderList.size() > 0) {
			// 部门下存在管理员而传入可变更的人不存在可变更的情况下
			List<Integer> needUpdateList = contrastList(leaderList, idsList);
			if (needUpdateList != null && needUpdateList.size() > 0) {
				delUrRelation(deptid, needUpdateList, Contants.LEADERROLE);
			}

		}
		List<User> deputyList = getDeptusers(deptid, Contants.EPUTYROLE, null);
		// 判断该部门下是否有副职
		if (deputyList != null && deputyList.size() > 0) {
			List<Integer> needUpdateList = contrastList(leaderList, idsList);
			if (needUpdateList != null && needUpdateList.size() > 0) {
				delUrRelation(deptid, needUpdateList, Contants.EPUTYROLE);
			}
		}
		// 清空部门已分配
		userExMapper.cleanDept(deptid);
		// 重新分配
		if (idsList != null && idsList.size() > 0) {
			User user = new User();
			user.setDid(deptid);
			UserExample userExample = new UserExample();
			Criteria criteria = userExample.createCriteria();
			criteria.andIdIn(idsList);
			userMapper.updateByExampleSelective(user, userExample);
		}

	}

	// 数组转list
	public List<Integer> changeArrayToList(String[] idArray) {
		if (idArray == null || idArray.length == 0 || idArray[0].equals("")) {
			return null;
		}
		List<Integer> idsList = new ArrayList<>();
		for (int i = 0; i < idArray.length; i++) {
			idsList.add(Integer.parseInt(idArray[i]));
		}
		return idsList;
	}

	// 对比集合
	public List<Integer> contrastList(List<User> userList, List<Integer> idsList) {
		List<Integer> needUpId = new ArrayList<>();
		if (idsList == null || idsList.size() == 0) {
			for (User user : userList) {
				needUpId.add(user.getId());
			}
		} else {
			for (int i = 0; i < userList.size(); i++) {

				if (idsList.contains(userList.get(i).getId())) {

				} else {
					needUpId.add(userList.get(i).getId());
				}

			}
		}
		return needUpId;
	}

	// 清除更改表user,urrelation
	public void delUrRelation(int deptid, List<Integer> needUpdate, String name) {
		for (int id : needUpdate) {
			List<Role> role = roleService.getRoleByName(name);
			urService.deleteUrBynd(id, role.get(0).getId());
			if (name.equals(Contants.LEADERROLE)) {
				deptMentExMapper.updateLeaderId(deptid);
			}
		}
	}

	// 查询部门下所有员工 不包括管理员
	@Override
	public List<Object> findAlterDusers(int id) {
		// TODO Auto-generated method stub
		List<Object> resultList = new ArrayList<Object>();
		// 获取角色集合
		List<String> rolesList = getRoleList(1);
		List<User> deptUserList = getDeptusers(id, Contants.USERROLE, rolesList);
		List<User> userList = getDeptusers(0, Contants.USERROLE, rolesList);
		resultList.add(deptUserList);
		resultList.add(userList);
		return resultList;
	}

	// 获取副职员工
	@Override
	public List<Object> findAlterEusers(int deptid) {
		// TODO Auto-generated method stub
		List<Object> resultList = new ArrayList<Object>();
		// 获取角色集合
		List<String> rolesList = getRoleList(2);
		List<User> deptUserList = getDeptusers(deptid, Contants.USERROLE, rolesList);
		List<User> userList = getDeptusers(deptid, Contants.EPUTYROLE, null);
		resultList.add(deptUserList);
		resultList.add(userList);
		return resultList;
	}


	/*
	 * 生成联系方式
	 */
	private User generalUserContactInfo (User user) {
		user.setCreatetime(null);
		user.setCreateuserid(null);
		user.setEmploytime(null);
		user.setIdno(null);
		user.setLoginname(null);
		user.setPassword(null);
		user.setQuittime(null);
		user.setUpdatetime(null);
		user.setUpdateuserid(null);
		
		return user;
	}
	
	@Override
	public List<User> findDpetUsers(int deptid) {
		// TODO Auto-generated method stub
		// 获取变更部门信息员工列表
		List<String> roles = getRoleList(3);
		List<User> resultList = getDeptusers(deptid, Contants.USERROLE, roles);
		return resultList;
	}

	@Override
	public List<User> findDpetUsersContactInfo(int deptid) {
		// TODO Auto-generated method stub
		List<User> userList = findDpetUsers(deptid);
		for (User user : userList) {
			generalUserContactInfo(user);
		}
		return userList;
	}
	
	public List<User> getDeptusers(int id, String name, List<String> roles) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("did", id);
		params.put("name", name);
		params.put("useroles", roles);
		List<User> userList = userExMapper.getDeptAusers(params);
		return userList;
	}

	@Override
	public int delByLoginName(String loginName) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andLoginnameEqualTo(loginName);
		int result = userMapper.deleteByExample(userExample);
		return result;
	}

	@Override
	public User findByIdNo(String idNo) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andIdnoEqualTo(idNo);
		List<User> list = userMapper.selectByExample(userExample);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public User findByJobNo(String jobNo) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andJobnoEqualTo(jobNo);
		List<User> list = userMapper.selectByExample(userExample);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public void updateEputysOfDepartment(int deptid, String ids) {
		// TODO Auto-generated method stub
		List<User> deputyList = getDeptusers(deptid, Contants.EPUTYROLE, null);
		List<Role> roleList = roleService.getRoleByName(Contants.EPUTYROLE);
		// 删除原有副职人员
		if (deputyList != null && deputyList.size() > 0) {
			for (int i = 0; i < deputyList.size(); i++) {
				urService.deleteUrBynd(deputyList.get(i).getId(), roleList.get(0).getId());
			}

		}
		// 重新分配副职人员
		String[] idArray = ids.split(",");
		List<Integer> idList = changeArrayToList(idArray);
		if (idList != null && idList.size() > 0) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("idlist", idList);
			params.put("roleid", roleList.get(0).getId());
			urService.batchAdd(params);
		}
		// 清空已存在部门副职人员
	}

	public List<String> getRoleList(int type) {
		List<String> roleList = new ArrayList<String>();
		// 部门员工变更
		if (type == 1) {
			roleList.add(Contants.ADMINROLE);
		}
		// 部门副职变更
		if (type == 2) {
			roleList.add(Contants.ADMINROLE);
			roleList.add(Contants.LEADERROLE);
			roleList.add(Contants.EPUTYROLE);

		}
		// 部门员工修改
		if (type == 3) {
			roleList.add(Contants.ADMINROLE);
		}
		// 查询部门下领导人和副职人员
		if (type == 4) {
			roleList.add(Contants.LEADERROLE);
			roleList.add(Contants.EPUTYROLE);
		}
		return roleList;
	}

	@Override
	public List<User> findDepartmentLeader(int uId) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> roles = getRoleList(4);
		params.put("userid", uId);
		params.put("useroles", roles);
		return userExMapper.getLeaderByUser(params);
	}

	@Override
	public List<User> findManagerLeader(int uId) {
		// TODO Auto-generated method stub

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", uId);
		params.put("typename", Contants.MANAGER);
		List<User> userList = userExMapper.findManagerLeader(params);
		return userList;
	}

	@Override
	public List<User> findAssignedManagerLeader(int uId) {
		// TODO Auto-generated method stub

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", uId);
		params.put("typename", Contants.ASSIGNEDMANAGER);
		List<User> userList = userExMapper.findManagerLeader(params);
		return userList;
	}

	@Override
	public UserDto findDtoById(int id) {
		// TODO Auto-generated method stub
		UserDto userDto = userExMapper.findById(id);
		return userDto;
	}

	@Override
	public ByteArrayInputStream exportUsers() throws IOException {
		List<User> list = getList();
		boolean b = false;
		int[] n = { 4, 4, 4, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };

		// 创建工作簿
		HSSFWorkbook workBook = new HSSFWorkbook();
		// 创建工作表 工作表的名字叫物资列表
		HSSFSheet sheet = workBook.createSheet("人员列表");
		// 创建行,第一行
		HSSFRow row = sheet.createRow(0);
		// 创建单元格，操作第一行各列
		HSSFCell cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("编号");
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("*登陆名");
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("*登陆密码");
		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("账户注册人名");
		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("部门");
		cell = row.createCell(5, CellType.STRING);
		cell.setCellValue("单位电话");
		cell = row.createCell(6, CellType.STRING);
		cell.setCellValue("*移动电话");
		cell = row.createCell(7, CellType.STRING);
		cell.setCellValue("固定电话");
		cell = row.createCell(8, CellType.STRING);
		cell.setCellValue("网卡");
		cell = row.createCell(9, CellType.STRING);
		cell.setCellValue("传真");
		cell = row.createCell(10, CellType.STRING);
		cell.setCellValue("电子邮箱");
		cell = row.createCell(11, CellType.STRING);
		cell.setCellValue("性别");
		cell = row.createCell(12, CellType.STRING);
		cell.setCellValue("身份证号");
		cell = row.createCell(13, CellType.STRING);
		cell.setCellValue("工号");
		cell = row.createCell(14, CellType.STRING);
		cell.setCellValue("岗位");
		cell = row.createCell(15, CellType.STRING);
		cell.setCellValue("创建时间");
		cell = row.createCell(16, CellType.STRING);
		cell.setCellValue("创建人");
		cell = row.createCell(17, CellType.STRING);
		cell.setCellValue("修改时间");
		cell = row.createCell(18, CellType.STRING);
		cell.setCellValue("修改人");
		cell = row.createCell(19, CellType.STRING);
		cell.setCellValue("入职时间");
		cell = row.createCell(20, CellType.STRING);
		cell.setCellValue("离职时间");
		cell = row.createCell(21, CellType.STRING);
		cell.setCellValue("政治面貌");
		cell = row.createCell(22, CellType.STRING);
		cell.setCellValue("头像");
		cell = row.createCell(23, CellType.STRING);
		cell.setCellValue("电子印章");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			cell = row.createCell(0, CellType.STRING);
			String id = list.get(i).getId().toString();
			if (id.length() > n[0]) {
				n[0] = id.length();
			}
			cell.setCellValue(id);
			cell = row.createCell(1, CellType.STRING);
			String loginName = list.get(i).getLoginname();
			if (loginName != null && loginName.length() > n[1]) {
				n[1] = loginName.length();
			}
			cell.setCellValue(loginName);
			cell = row.createCell(2, CellType.STRING);
			String password = list.get(i).getPassword();
			if (password != null && password.length() > n[2]) {
				n[2] = password.length();
			}
			cell.setCellValue(password);

			cell = row.createCell(3, CellType.STRING);
			String name = list.get(i).getName();
			if (name != null && name.length() > n[3]) {
				n[3] = name.length();
			}
			cell.setCellValue(name);

			cell = row.createCell(4, CellType.STRING);
			Integer unitid = list.get(i).getDid();
			if (unitid != null && unitid.toString().length() > n[4]) {
				n[4] = unitid.toString().length();
			}
			if (unitid != null) {
				// if (b) {
				DepartmentDto depart = deptMentExMapper.findDeptDetail(unitid);
				cell.setCellValue(depart.getName());
				/*
				 * }else{ cell.setCellValue(unitid); }
				 */
			} else {
				cell.setCellValue("");
			}

			cell = row.createCell(5, CellType.STRING);
			String unitTelephone = list.get(i).getUnittelephone();
			if (unitTelephone != null && unitTelephone.length() > n[5]) {
				n[5] = unitTelephone.length();
			}
			cell.setCellValue(unitTelephone);

			cell = row.createCell(6, CellType.STRING);
			String telephone = list.get(i).getTelephone();
			if (telephone != null && telephone.length() > n[6]) {
				n[6] = telephone.length();
			}
			cell.setCellValue(telephone);

			cell = row.createCell(7, CellType.STRING);
			String landline = list.get(i).getLandline();
			if (landline != null && landline.length() > n[7]) {
				n[7] = landline.length();
			}
			cell.setCellValue(landline);

			cell = row.createCell(8, CellType.STRING);
			String networkCard = list.get(i).getNetworkcard();
			if (networkCard != null && networkCard.length() > n[8]) {
				n[8] = networkCard.length();
			}
			cell.setCellValue(networkCard);

			cell = row.createCell(9, CellType.STRING);
			String fax = list.get(i).getFax();
			if (fax != null && fax.length() > n[9]) {
				n[9] = fax.length();
			}
			cell.setCellValue(fax);

			cell = row.createCell(10, CellType.STRING);
			String email = list.get(i).getEmail();
			if (email != null && email.length() > n[10]) {
				n[10] = email.length();
			}
			cell.setCellValue(email);

			cell = row.createCell(11, CellType.STRING);
			Integer sex = list.get(i).getSex();
			if (sex != null) {
				// if (b) {
				if (sex == 2) {
					cell.setCellValue("女");
				} else {
					cell.setCellValue("男");
				}
				/*
				 * }else{ cell.setCellValue(sex); }
				 */
			} else {
				cell.setCellValue("");
			}

			cell = row.createCell(12, CellType.STRING);
			String idNo = list.get(i).getIdno();
			if (idNo != null && idNo.length() > n[12]) {
				n[12] = idNo.length();
			}
			cell.setCellValue(idNo);

			cell = row.createCell(13, CellType.STRING);
			String jobNo = list.get(i).getJobno();
			if (jobNo != null && jobNo.length() > n[13]) {
				n[13] = jobNo.length();
			}
			cell.setCellValue(jobNo);

			cell = row.createCell(14, CellType.STRING);
			Integer positionId = list.get(i).getPositionid();
			if (positionId != null && positionId.toString().length() > n[14]) {
				n[14] = positionId.toString().length();
			}
			if (positionId != null) {
				// if (b) {
				cell.setCellValue(positionMapper.selectByPrimaryKey(positionId).getName());
				/*
				 * }else{ cell.setCellValue(positionId); }
				 */
			} else {
				cell.setCellValue("");
			}

			cell = row.createCell(15, CellType.STRING);
			Date createTime = list.get(i).getCreatetime();
			if (createTime != null && createTime.toString().length() > n[15]) {
				n[15] = createTime.toString().length();
			}
			if (createTime != null) {
				cell.setCellValue(format.format(createTime));
			} else {
				cell.setCellValue("");
			}

			cell = row.createCell(16, CellType.STRING);
			Integer createUserId = list.get(i).getCreateuserid();
			if (createUserId != null && createUserId.toString().length() > n[16]) {
				n[16] = createUserId.toString().length();
			}
			if (createUserId != null) {
				if (b) {
					cell.setCellValue(userMapper.selectByPrimaryKey(createUserId).getName());
				} else {
					cell.setCellValue(createUserId);
				}
			} else {
				cell.setCellValue("");
			}

			cell = row.createCell(17, CellType.STRING);
			Date updateTime = list.get(i).getUpdatetime();
			if (updateTime != null && updateTime.toString().length() > n[17]) {
				n[17] = updateTime.toString().length();
			}
			if (updateTime != null) {
				cell.setCellValue(format.format(updateTime));
			} else {
				cell.setCellValue("");
			}

			cell = row.createCell(18, CellType.STRING);
			Integer updateUserId = list.get(i).getUpdateuserid();
			if (updateUserId != null && updateUserId.toString().length() > n[18]) {
				n[18] = updateUserId.toString().length();
			}
			if (updateUserId != null) {
				if (b) {
					cell.setCellValue(userMapper.selectByPrimaryKey(updateUserId).getName());
				} else {
					cell.setCellValue(updateUserId);
				}
			} else {
				cell.setCellValue("");
			}

			cell = row.createCell(19, CellType.STRING);
			Date employTime = list.get(i).getEmploytime();
			if (employTime != null && employTime.toString().length() > n[19]) {
				n[19] = employTime.toString().length();
			}
			if (employTime != null) {
				cell.setCellValue(format.format(employTime));
			} else {
				cell.setCellValue("");
			}

			cell = row.createCell(20, CellType.STRING);
			Date quitTime = list.get(i).getQuittime();
			if (quitTime != null && quitTime.toString().length() > n[20]) {
				n[20] = quitTime.toString().length();
			}
			if (quitTime != null) {
				cell.setCellValue(format.format(quitTime));
			} else {
				cell.setCellValue("");
			}

			cell = row.createCell(21, CellType.STRING);
			Integer pOId = list.get(i).getPoid();
			if (pOId != null && pOId.toString().length() > n[21]) {
				n[21] = pOId.toString().length();
			}
			if (pOId != null) {
				// if (b) {
				if (pOId == 1) {
					cell.setCellValue("中国党员");
				} else if (pOId == 2) {
					cell.setCellValue("团员");
				} else if (pOId == 3) {
					cell.setCellValue("群众");
				} else {
					cell.setCellValue("");
				}
				/*
				 * }else{ cell.setCellValue(pOId); }
				 */

			} else {
				cell.setCellValue("");
			}

			cell = row.createCell(22, CellType.STRING);
			String headPortraitPath = list.get(i).getHeadportraitpath();
			if (headPortraitPath != null && headPortraitPath.length() > n[22]) {
				n[22] = headPortraitPath.length();
			}
			cell.setCellValue(headPortraitPath);

			cell = row.createCell(23, CellType.STRING);
			String electronicSealPath = list.get(i).getElectronicsealpath();
			if (electronicSealPath != null && electronicSealPath.length() > n[23]) {
				n[23] = electronicSealPath.length();
			}
			cell.setCellValue(electronicSealPath);

		}
		for (int i = 0; i < n.length; i++) {
			//sheet.setColumnWidth(i, n[i] * 2*256);// 调整每一列宽度
			sheet.autoSizeColumn(i, true);
		}
		//sheet.autoSizeColumn(1, true);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			// 工作簿写入流中
			workBook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		ByteArrayInputStream is = new ByteArrayInputStream(content);
		os.close();
		workBook.close();
		return is;
	}

	/**
	 * excel
	 */
	@Override
	public List<User> getUsersFromExcel(File file, Integer userId) throws Exception {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel(file, userId);
		} else if ("xlsx".equals(extension)) {
			return read2007Excel(file, userId);
		} else {
			throw new IOException("不支持该文件类型");
		}
	}

	/**
	 * 解析 office 2003 excel
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("deprecation")
	private List<User> read2003Excel(File file, Integer userId) throws Exception {
		List<User> list = new LinkedList<User>();
		HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = hwb.getSheetAt(0);
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		int counter = 0;
		List<String> list1 = new ArrayList<String>();
		Set<String> loginSet = new HashSet<String>();
		Set<String> teleSet = new HashSet<String>();
		for (int i = sheet.getFirstRowNum(); counter < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			} else {
				counter++;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = 0; j <= 23; j++) {
				cell = row.getCell(j);
				if (cell == null) {
					linked.add(cell);
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");
				SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YMDHMS);
				DecimalFormat nf = new DecimalFormat("0");
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					value = "";
					break;
				default:
					value = cell.toString();
				}
				// if (value == null || "".equals(value)) {
				// continue;
				// }
				linked.add(value);
			}
			// System.out.println(linked);
			// 获取表头
			if (i == 0) {
				for (int j = 0; j < linked.size(); j++) {
					// System.out.println(linked.size());
					if (linked.get(j) != null) {
						list1.add(linked.get(j).toString().trim());
					}
				}
				// System.out.println(list1);
			}
			boolean pass = false;
			for (int j = 0; j < linked.size(); j++) {
				if (linked.get(j) != null) {
					String str = linked.get(j).toString();
					// System.out.println(j);
					if (!str.trim().equals("")) {
						// System.out.println("1"+str.trim()+"1");
						pass = true;
					}
				}
			}
			if (i > 0 && pass) {
				User user = new User();
				// System.out.println(linked);
				// System.out.println(list1.size());
				for (int j = 0; j < list1.size(); j++) {
					// boolean b=false;
					// System.out.println(list1.get(j));
					switch (list1.get(j)) {
					case "编号":
						break;
					case "*登陆名":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							if (this.findByLoginName(linked.get(j).toString().trim()) != null) {
								hwb.close();
								throw new CommonException(
										"登陆名" + linked.get(j).toString().trim() + "第" + (i + 1) + "行已经注册，不能重复注册");
							} else {
								int length = loginSet.size();
								loginSet.add(linked.get(j).toString());
								if (length == loginSet.size()) {
									hwb.close();
									throw new CommonException(
											"登陆名" + linked.get(j).toString().trim() + ",第" + i + "行，本表中已经有此登陆名,不能重复");
								}
								user.setLoginname(linked.get(j).toString().trim());
							}
						} else {
							hwb.close();
							throw new CommonException("第" + (i + 1) + "行，登陆名不能为空");
						}
						break;

					case "*登陆密码":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							// AesUtil.encrypt(content);//加密，是否添加标记，确定当前解析出来的表格内容是否是已经加密后的密码
							user.setPassword(linked.get(j).toString().trim());
						} else {
							// hwb.close();
							// throw new CommonException("登陆密码不能为空");
							user.setPassword("QfRRxHuWnLpmQlrKjTeM2A==");
						}
						break;

					case "账户注册人名":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setName(linked.get(j).toString().trim());
						}
						break;

					case "部门":
						// 校验数据？
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							/*
							 * //根据部门id查找部门 if
							 * (deptMentMapper.selectByPrimaryKey(Integer.parseInt(linked.get(j).toString().
							 * trim()))==null) { hwb.close(); throw new
							 * CommonException("第"+(i+1)+"行，该部门不存在"); }
							 * user.setDid(Integer.parseInt(linked.get(j).toString().trim()));
							 */
							// 根据部门名字查找部门id
							if (deptMentExMapper.findDeptByName(linked.get(j).toString().trim()) != null) {
								user.setDid(deptMentExMapper.findDeptByName(linked.get(j).toString().trim()).getId());
							} else {
								hwb.close();
								throw new CommonException(
										"第" + (i + 1) + "行，" + linked.get(j).toString().trim() + " 该部门不存在");
							}
						}
						break;

					case "单位电话":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setUnittelephone(linked.get(j).toString().trim());
						}
						break;

					case "*移动电话":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							if (this.findByTelphone(linked.get(j).toString().trim()) != null) {
								hwb.close();
								throw new CommonException(
										"第" + (i + 1) + "行，此" + linked.get(j).toString().trim() + "电话号码已经注册");
							} else {
								int length = teleSet.size();
								teleSet.add(linked.get(j).toString());
								if (length == teleSet.size()) {
									hwb.close();
									throw new CommonException(
											"第" + (i + 1) + "行，此" + linked.get(j).toString().trim() + "电话号码，本表中已经有");
								}
								user.setTelephone(linked.get(j).toString().trim());
							}
						} else {
							hwb.close();
							throw new CommonException("第" + (i + 1) + "行，移动电话号码不能为空");
						}
						break;

					case "固定电话":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setLandline(linked.get(j).toString().trim());
						}
						break;

					case "网卡":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setNetworkcard(linked.get(j).toString().trim());
						}
						break;

					case "传真":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setFax(linked.get(j).toString().trim());
						}
						break;

					case "电子邮箱":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setEmail(linked.get(j).toString().trim());
						}
						break;

					case "性别":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							// System.out.println(linked.get(j));
							String sex = linked.get(j).toString().trim();
							if (sex.equals("男")) {
								user.setSex(1);
							} else {
								user.setSex(2);
							}
							// user.setSex(Integer.parseInt(linked.get(j).toString().trim()));
						} else {
							hwb.close();
							throw new CommonException("第" + (i + 1) + "行，性别不能为空");
						}
						break;

					case "身份证号":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setIdno(linked.get(j).toString().trim());
						}
						break;

					case "工号":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setJobno(linked.get(j).toString().trim());
						}
						break;

					case "岗位":
						// 添加数据库查询功能
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							/*
							 * //根据id查找岗位是否存在 if
							 * (positionMapper.selectByPrimaryKey(Integer.parseInt(linked.get(j).toString().
							 * trim()))==null) { hwb.close(); throw new
							 * CommonException("第"+(i+1)+"行，该岗位不存在"); }
							 * user.setPositionid(Integer.parseInt(linked.get(j).toString().trim()));
							 */
							// 根据岗位名字查询是否存在该岗位
							if (positionExMapper.findPositionByName(linked.get(j).toString().trim()) == null) {
								hwb.close();
								throw new CommonException(
										"第" + (i + 1) + "行，" + linked.get(j).toString().trim() + " 此岗位不存在");
							}
							// int
							// pid=positionExMapper.findPositionByName(linked.get(j).toString().trim()).getId();
							// System.out.println(pid);
							user.setPositionid(
									positionExMapper.findPositionByName(linked.get(j).toString().trim()).getId());
						}
						break;
					case "创建时间":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
									.parse(linked.get(j).toString().trim());
							user.setCreatetime(date);
						} else {
							Date date = new Date();
							user.setCreatetime(date);
						}
						break;

					case "创建人":
						// 是否校验数据
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							if (userMapper
									.selectByPrimaryKey(Integer.parseInt(linked.get(j).toString().trim())) == null) {
								hwb.close();
								throw new CommonException(
										"第" + (i + 1) + "行，" + linked.get(j).toString().trim() + " 该人员不存在");
							}
							user.setCreateuserid(Integer.parseInt(linked.get(j).toString().trim()));
						} else {
							user.setCreateuserid(userId);
						}
						break;

					case "修改时间":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
									.parse(linked.get(j).toString().trim());
							user.setUpdatetime(date);
						}
						break;

					case "修改人":
						// 校验传进来的数据数据库是否存在，存在一个问题，数据库不存在，表里存在，如果是人名相同，如何区分
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							if (userMapper
									.selectByPrimaryKey(Integer.parseInt(linked.get(j).toString().trim())) == null) {
								hwb.close();
								throw new CommonException(
										"第" + (i + 1) + "行，" + linked.get(j).toString().trim() + " 该人员不存在");
							}
							user.setUpdateuserid(Integer.parseInt(linked.get(j).toString().trim()));
						}
						break;

					case "入职时间":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
									.parse(linked.get(j).toString().trim());
							user.setEmploytime(date);
						}
						break;

					case "离职时间":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
									.parse(linked.get(j).toString().trim());
							user.setQuittime(date);
						}
						break;

					case "政治面貌":
						// 校验数据
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							String poid = linked.get(j).toString().trim();
							if (poid.equals("中国党员")) {
								user.setPoid(1);
							} else if (poid.equals("团员")) {
								user.setPoid(2);
							} else if (poid.equals("群众")) {
								user.setPoid(3);
							} else {
								hwb.close();
								throw new CommonException(
										"第" + (i + 1) + "行，" + linked.get(j).toString().trim() + " 该政治面貌有问题");
							}
							// user.setPoid(Integer.parseInt(linked.get(j).toString().trim()));
						}
						break;

					case "头像":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setHeadportraitpath(linked.get(j).toString().trim());
						}
						break;

					case "电子印章":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setElectronicsealpath(linked.get(j).toString().trim());
						}
						break;
					default:
						hwb.close();
						throw new CommonException("Excel表格列表头名出现错误, " + "'" + list1.get(j) + "'" + " 此表头有问题");
					}
				}
				// System.out.println(user.getLoginname());
				if (user.getCreateuserid() == null) {
					user.setCreateuserid(userId);
				}
				if (user.getCreatetime() == null) {
					user.setCreatetime(new Date());
				}
				list.add(user);
			}
		}

		hwb.close();
		if (counter < 2) {
			return null;
		} else {
			return list;
		}

	}

	/**
	 * 解析Office 2007 excel
	 * 
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	private List<User> read2007Excel(File file, Integer userId) throws Exception {
		List<User> list = new LinkedList<User>();
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		int counter = 0;
		List<String> list1 = new ArrayList<String>();
		Set<String> loginSet = new HashSet<String>();
		Set<String> teleSet = new HashSet<String>();
		for (int i = sheet.getFirstRowNum(); counter < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			} else {
				counter++;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = 0; j <= 23; j++) {
				cell = row.getCell(j);
				if (cell == null) {
					linked.add(cell);
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");
				SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YMDHMS);
				DecimalFormat nf = new DecimalFormat("0");
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					value = "";
					break;
				default:
					value = cell.toString();
				}
				// if (value == null || "".equals(value)) {
				// continue;
				// }
				linked.add(value);
				// System.out.println(linked);
			}
			// 获取表头
			if (i == 0) {
				for (int j = 0; j < linked.size(); j++) {
					// System.out.println(linked.size());
					if (linked.get(j) != null) {
						list1.add(linked.get(j).toString());
					}
				}
				// System.out.println(list1);
			}
			boolean pass = false;
			for (int j = 0; j < linked.size(); j++) {
				if (linked.get(j) != null) {
					String str = linked.get(j).toString();
					// System.out.println(j);
					if (!str.trim().equals("")) {
						// System.out.println("1"+str.trim()+"1");
						pass = true;
					}
				}
			}
			if (i > 0 && pass) {
				User user = new User();
				// System.out.println(linked);
				// System.out.println(list1.size());
				for (int j = 0; j < list1.size(); j++) {
					// boolean b=false;
					// System.out.println(list1.get(j));
					switch (list1.get(j)) {
					case "编号":
						break;
					case "*登陆名":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							if (this.findByLoginName(linked.get(j).toString().trim()) != null) {
								xwb.close();
								throw new CommonException("登陆名第" + i + "行已经注册，不能重复注册");
							} else {
								int length = loginSet.size();
								loginSet.add(linked.get(j).toString());
								if (length == loginSet.size()) {
									xwb.close();
									throw new CommonException(
											"登陆名" + linked.get(j).toString().trim() + ",第" + i + "行，本表中已经有此登陆名,不能重复");
								}
								user.setLoginname(linked.get(j).toString().trim());
							}
						} else {
							xwb.close();
							throw new CommonException("第" + (i + 1) + "行，登陆名不能为空");
						}
						break;

					case "*登陆密码":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setPassword(linked.get(j).toString().trim());
						} else {
							// xwb.close();
							// throw new CommonException("登陆密码不能为空");
							user.setPassword("QfRRxHuWnLpmQlrKjTeM2A==");
						}
						break;

					case "账户注册人名":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setName(linked.get(j).toString().trim());
						}
						break;

					case "部门":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setDid(Integer.parseInt(linked.get(j).toString().trim()));
						}
						break;

					case "单位电话":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setUnittelephone(linked.get(j).toString().trim());
						}
						break;

					case "*移动电话":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							if (this.findByTelphone(linked.get(j).toString().trim()) != null) {
								xwb.close();
								throw new CommonException("此" + linked.get(j).toString().trim() + "电话号码已经注册");
							} else {
								int length = teleSet.size();
								teleSet.add(linked.get(j).toString());
								if (length == teleSet.size()) {
									xwb.close();
									throw new CommonException(
											"第" + i + "行，此" + linked.get(j).toString().trim() + "电话号码，本表中已经有");
								}
								user.setTelephone(linked.get(j).toString().trim());
							}
						} else {
							xwb.close();
							throw new CommonException("第" + (i + 1) + "行，电话号码不能为空");
						}
						break;

					case "固定电话":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setLandline(linked.get(j).toString().trim());
						}
						break;

					case "网卡":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setNetworkcard(linked.get(j).toString().trim());
						}
						break;

					case "传真":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setFax(linked.get(j).toString().trim());
						}
						break;

					case "电子邮箱":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setEmail(linked.get(j).toString().trim());
						}
						break;

					case "性别":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							// System.out.println(linked.get(j));
							user.setSex(Integer.parseInt(linked.get(j).toString().trim()));
						} else {
							xwb.close();
							throw new CommonException("第" + (i + 1) + "行，性别不能为空");
						}
						break;

					case "身份证号":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setIdno(linked.get(j).toString().trim());
						}
						break;

					case "工号":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setJobno(linked.get(j).toString().trim());
						}
						break;

					case "岗位":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setPositionid(Integer.parseInt(linked.get(j).toString().trim()));
						}
						break;
					case "创建时间":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
									.parse(linked.get(j).toString().trim());
							user.setCreatetime(date);
						}
						break;

					case "创建人":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setCreateuserid(Integer.parseInt(linked.get(j).toString().trim()));
						}
						break;

					case "修改时间":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
									.parse(linked.get(j).toString().trim());
							user.setUpdatetime(date);
						}
						break;

					case "修改人":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setUpdateuserid(Integer.parseInt(linked.get(j).toString().trim()));
						}
						break;

					case "入职时间":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
									.parse(linked.get(j).toString().trim());
							user.setEmploytime(date);
						}
						break;

					case "离职时间":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
									.parse(linked.get(j).toString().trim());
							user.setQuittime(date);
						}
						break;

					case "政治面貌":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setPoid(Integer.parseInt(linked.get(j).toString().trim()));
						}
						break;

					case "头像":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setHeadportraitpath(linked.get(j).toString().trim());
						}
						break;

					case "电子印章":
						if (linked.get(j) != null && !linked.get(j).toString().trim().equals("")) {
							user.setElectronicsealpath(linked.get(j).toString().trim());
						}
						break;
					default:
						xwb.close();
						throw new CommonException("Excel表格列表头名出现错误, " + "'" + list1.get(j) + "'" + " 此表头有问题");
					}
				}
				// System.out.println(user.getLoginname());
				list.add(user);
			}
		}
		xwb.close();
		if (counter < 3) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public List<User> findByIds(List<Integer> userIdList) {
		// TODO Auto-generated method stub
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andIdIn(userIdList);
		List<User> list = userMapper.selectByExample(userExample);
		return list;
	}
	

	private String generateOssKey(String type, Integer userId, String name) {		
		UUID uuid = UUID.randomUUID();
		String path = "user/" + type + "/" + userId.toString() +  "/" +  uuid + "-" + name;
		return path;
	}
	
	
	@Override
	public String getHeadPortraitOssKey(Integer userId, String name) {
		return generateOssKey("HeadPortrait", userId, name);
	}
	
	@Override
	public String getElectronicSealOssKey(Integer userId, String name) {
		return generateOssKey("ElectronicSeal", userId, name);
	}
	
}
