package cn.caecc.erp.modules.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.ex.mapper.SupplierGoodsRelationshipExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.SupplierGoodsRelationship;
import cn.caecc.erp.modules.dao.mybatis.entity.SupplierGoodsRelationshipExample;
import cn.caecc.erp.modules.dao.mybatis.entity.SupplierGoodsRelationshipExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.SupplierGoodsRelationshipMapper;
import cn.caecc.erp.modules.service.SupplierGoodsRelationshipService;
import cn.caecc.erp.support.exception.CommonException;

@Service
public class SupplierGoodsRelationshipServiceImpl implements SupplierGoodsRelationshipService {

	@Autowired
	private SupplierGoodsRelationshipExMapper sgrExMapper;
	@Autowired
	private SupplierGoodsRelationshipMapper sgrMapper;

	@Override
	public List<SupplierGoodsRelationship> batchAdd(List<SupplierGoodsRelationship> list) {
		if (list == null || list.size() == 0) {
			throw new CommonException("参数异常");
		} else {
			int result = sgrExMapper.batchAdd(list);
			if (result <= 0) {
				throw new CommonException("创建失败");
			}
		}
		return list;
	}

	@Override
	public int batchUpdate(int Supplierid,List<SupplierGoodsRelationship> list) {
		int result = 0;
		SupplierGoodsRelationshipExample supplierGoodsRelationshipExample = new SupplierGoodsRelationshipExample();
		Criteria criteria = supplierGoodsRelationshipExample.createCriteria();
		criteria.andSupplieridEqualTo(Supplierid);
		List<SupplierGoodsRelationship> selectByExample = sgrMapper.selectByExample(supplierGoodsRelationshipExample);
		//前端传过来的list为[],数据库也没有数据
		if ((selectByExample == null || selectByExample.size() == 0) && (list == null || list.size() == 0)) {
			return 1;
		}else {
			int ret = -1;
			//如果数据库有数据
			if (selectByExample != null && selectByExample.size() > 0) {
				ret = sgrMapper.deleteByExample(supplierGoodsRelationshipExample);
			} else {//数据库没有数据
				ret = 0;
			}
			//若ret > -1且list不为空，将list的数据存入数据库
			if (ret > -1 && list != null && list.size() > 0) {
				result = sgrExMapper.batchAdd(list);
				if (result <= 0) {
					throw new CommonException("更新失败");
				}
			} 
			return result;
		}
		
	}
}
