package cn.caecc.erp.modules.service.serviceImpl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.mybatis.entity.SealType;
import cn.caecc.erp.modules.dao.mybatis.entity.SealTypeExample;
import cn.caecc.erp.modules.dao.mybatis.entity.SealTypeExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.SealTypeMapper;
import cn.caecc.erp.modules.service.SealTypeService;
import cn.caecc.erp.support.exception.CommonException;
@Service
public class SealTypeServiceImpl implements SealTypeService{
	@Autowired
	private SealTypeMapper  stMapper;

	@Override
	public int addSealType(SealType sealType) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(sealType.getName())){
			throw new CommonException("印章类型不能为空");

		}
		if(!queryStByName(sealType.getName())){
			throw new CommonException("印章类型重复");
		}
		return 	stMapper.insertSelective(sealType);
	}

	@Override
	public int deleteSealType(int id) {
		// TODO Auto-generated method stub
		return stMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateSealType(SealType sealType) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(sealType.getName())){
			throw new CommonException("印章类型不能为空");

		}
		if(!queryStByName(sealType.getName())){
			throw new CommonException("印章类型重复");
		}
		return stMapper.updateByPrimaryKeySelective(sealType);
	}

	@Override
	public boolean queryStByName(String name) {
		// TODO Auto-generated method stub
		SealTypeExample  sealTypeExample=new SealTypeExample();
		Criteria criteria=sealTypeExample.createCriteria();
		criteria.andNameEqualTo(name);
		List<SealType> reusultList=stMapper.selectByExample(sealTypeExample);
		if(reusultList!=null&&reusultList.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public PageInfo<SealType>  getStPageList(int pageno, int pagesize) {
		// TODO Auto-generated method stub
		List<SealType> pageList=stMapper.selectByExample(null);
		PageHelper.startPage(pageno, pagesize);
		PageInfo<SealType> pageInfo = new PageInfo<>(pageList);
		return pageInfo;
	}

	@Override
	public List<SealType> getStAllList() {
		// TODO Auto-generated method stub
		List<SealType> resultList=stMapper.selectByExample(null);
		return resultList;
	}
	
	
	

}
