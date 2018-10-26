package cn.caecc.erp.modules.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.ex.dto.OfficesupplyGoodsRelationshipActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.mapper.OfficesupplyGoodsRelationshipActivitiApplyExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.OfficesupplyGoodsRelationshipActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.entity.OfficesupplyGoodsRelationshipActivitiApplyExample;
import cn.caecc.erp.modules.dao.mybatis.entity.OfficesupplyGoodsRelationshipActivitiApplyExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.OfficesupplyGoodsRelationshipActivitiApplyMapper;
import cn.caecc.erp.modules.service.OfficesupplyGoodsRelationshipActivitiApplyService;
import cn.caecc.erp.support.exception.CommonException;
@Service
public class OfficesupplyGoodsRelationshipActivitiApplyServiceImpl
		implements OfficesupplyGoodsRelationshipActivitiApplyService {
	
	@Autowired
	private OfficesupplyGoodsRelationshipActivitiApplyMapper ogrActivitiApplyMapper;
	@Autowired
	private OfficesupplyGoodsRelationshipActivitiApplyExMapper ogrActivitiApplyExMapper;

	/**
	 * 批量更新
	 */
	@Override
	public List<OfficesupplyGoodsRelationshipActivitiApply> batchUpdate(List<OfficesupplyGoodsRelationshipActivitiApply> list) {
		if (list == null || list.size() == 0) {
			throw new CommonException("参数异常");
		}else {
			List<Integer> idList = new ArrayList<>();
			List<OfficesupplyGoodsRelationshipActivitiApply> batchFind = null;
			for (OfficesupplyGoodsRelationshipActivitiApply ogr : list) {
				idList.add(ogr.getId());
			}
			if (idList != null && idList.size() == list.size()) {
				batchFind = this.batchFind(idList);
			}else {
				throw new CommonException("数据异常");
			}
			if (batchFind != null && batchFind.size() == list.size()) {
				int result = ogrActivitiApplyExMapper.batchUpdate(list);
				if (result != list.size()) {
					throw new CommonException("更新失败");
				}
			}else {
				throw new CommonException("数据异常");
			}
		}
		return list;
	}

	/**
	 * 批量增加
	 */
	@Override
	public List<OfficesupplyGoodsRelationshipActivitiApply> batchAdd(List<OfficesupplyGoodsRelationshipActivitiApply> list) {
		if (list == null || list.size() == 0) {
			throw new CommonException("参数异常");

		}else {
			int result = ogrActivitiApplyExMapper.batchAdd(list);
			if (result != list.size()) {
				throw new CommonException("创建失败");
			}
		}
		return list;
	}

	/**
	 * 删除
	 */
	@Override
	public int deleteByOid(int id) {
		OfficesupplyGoodsRelationshipActivitiApplyExample example = new OfficesupplyGoodsRelationshipActivitiApplyExample();
		Criteria criteria = example.createCriteria();
		criteria.andOidEqualTo(id);
		int result = ogrActivitiApplyMapper.deleteByExample(example);
		if (result <= 0) {
			throw new CommonException("删除失败");
		}
		return result;
	}

	/**
	 * 查询详情
	 */
	@Override
	public OfficesupplyGoodsRelationshipActivitiApplyDto findDetail(int id) {
		return ogrActivitiApplyExMapper.findDetail(id);
	}

	/**
	 * 批量查询
	 * @param idList
	 * @return
	 */
	@Override
	public List<OfficesupplyGoodsRelationshipActivitiApply> batchFind(List<Integer> idList) {
		return ogrActivitiApplyExMapper.batchFind(idList);
	}

}
