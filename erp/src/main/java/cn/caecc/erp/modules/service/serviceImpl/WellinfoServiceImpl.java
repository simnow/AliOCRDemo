package cn.caecc.erp.modules.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.dto.DepartmentDto;
import cn.caecc.erp.modules.dao.ex.mapper.DepartmentExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.Department;
import cn.caecc.erp.modules.dao.mybatis.entity.WellInfo;
import cn.caecc.erp.modules.dao.mybatis.entity.WellInfoExample;
import cn.caecc.erp.modules.dao.mybatis.entity.WellInfoExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.WellInfoMapper;
import cn.caecc.erp.modules.service.WellinfoService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.DateUtil;

@Service
public class WellinfoServiceImpl implements WellinfoService{
	@Autowired
	private WellInfoMapper wellInfoMapper;
	@Autowired
	private DepartmentExMapper departMentExMapper;
	
	@Override
	public int insertWellInfo(WellInfo wellinfo) {
		if(StringUtils.isNotBlank(DateUtil.DateToString(wellinfo.getWjrq(), DateUtil.YYYY_MM_DD))){
			wellinfo.setState(1);
		}else{
			wellinfo.setState(0);
		}
		wellinfo.setCreatetime(DateUtil.getcurrentDateTime());
		int result=wellInfoMapper.insertSelective(wellinfo);
		if(result>0){
			return wellinfo.getId();
		}else{
			return 0;
		}
	}

	@Override
	public boolean deleteWellInfo(int id) {
		try {
			if(wellInfoMapper.deleteByPrimaryKey(id)>0){
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateWellInfo(WellInfo wellinfo) {
		if(StringUtils.isNotBlank(DateUtil.DateToString(wellinfo.getWjrq(), DateUtil.YYYY_MM_DD))){
			wellinfo.setState(1);
		}else{
			wellinfo.setState(0);
		}
		if(wellInfoMapper.updateByPrimaryKey(wellinfo)>0){
			return true;
		}
		return false;
	}

	@Override
	public WellInfo selectWellInfoById(int id) {
		return wellInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WellInfo> selectWellInfoByState(int state) {
		WellInfoExample example=new WellInfoExample();
		Criteria criteria=example.createCriteria();
		if(state!=3){
			criteria.andStateEqualTo(state);
		}
		example.setOrderByClause("CreateTime desc");
		return wellInfoMapper.selectByExample(example);
	}
	
	@Override
	public Message selectWellInfoListByState(int state,Integer wellId,int pageNo,int pageSize) {
		Message message = new Message();
		PageHelper.startPage(pageNo, pageSize);
		WellInfoExample example=new WellInfoExample();
		Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(state);
		if(null!=wellId){
			criteria.andIdEqualTo(wellId);
		}
		example.setOrderByClause("CreateTime desc");
		List<WellInfo> list= wellInfoMapper.selectByExample(example);
		PageInfo<WellInfo> pageInfo = new PageInfo<>(list);
		message.setObj(pageInfo);
		return message;
	}

	@Override
	public boolean updateWellDepthById(int id, Double depth) {
		WellInfo wellInfo=new WellInfo();
		wellInfo.setId(id);
		if(null!=depth){
			wellInfo.setCurrentdepth(depth);
			if(wellInfoMapper.updateByPrimaryKeySelective(wellInfo)>0){
				return true;
			}
			return false;
		}
		return true;
	}

	@Override
	public List<Department> selectDidList() {
		DepartmentDto dto=new DepartmentDto();
		dto.setDtypename(Contants.DRILLINGCREW);
		//井队
		List<Department> list=departMentExMapper.selectDeptByDto(dto);
		return list;
	}

	@Override
	public Map<String, Object> getWellInfoDe(int did, int state) {
		Map<String, Object> resultMap=new HashMap<>();
		WellInfoExample example=new WellInfoExample();
		Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(state);
		criteria.andDidEqualTo(did);
		List<WellInfo> result=wellInfoMapper.selectByExample(example);
		resultMap.put("clde", null);//材料定额
		resultMap.put("rhyde", null);//润滑油定额
		resultMap.put("wellid", null);//井编号
		if(null!=result&&result.size()>0){
			resultMap.put("clde", result.get(0).getClde());//材料定额
			resultMap.put("rhyde", result.get(0).getRhyde());//润滑油定额
			resultMap.put("wellid", result.get(0).getId());//井号
		}
		return resultMap;
	}
	@Override
	public List<WellInfo> getWellInfoBydid(Integer did) {
		WellInfoExample example=new WellInfoExample();
		Criteria criteria=example.createCriteria();
		if(null!=did){
			criteria.andDidEqualTo(did);
		}
		List<WellInfo> result=wellInfoMapper.selectByExample(example);
		return result;
	}

}
