package cn.caecc.erp.modules.service.serviceImpl;

import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.PositionDto;
import cn.caecc.erp.modules.dao.ex.mapper.PositionExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.Position;
import cn.caecc.erp.modules.dao.mybatis.entity.PositionExample;
import cn.caecc.erp.modules.dao.mybatis.entity.PositionExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.PositionMapper;
import cn.caecc.erp.modules.service.PositionService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.util.DateUtil;

@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	private PositionMapper positionMapper;
	@Autowired
	private PositionExMapper positionExMapper;

	@Override
	public int createPosition(Position position) {
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
		position.setCreateuserid(loginUserId);
		position.setUpdateuserid(loginUserId);
		position.setCreatetime(DateUtil.getcurrentDateTime());
		position.setUpdatetime(DateUtil.getcurrentDateTime());
		int result = positionMapper.insert(position);
		return result;
	}

	@Override
	public int update(Position position) {
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
		position.setUpdatetime(DateUtil.getcurrentDateTime());
		position.setUpdateuserid(loginUserId);
		int result = positionMapper.updateByPrimaryKey(position);
		return result;
	}

	@Override
	public PositionDto findById(int id) {
		return positionExMapper.findById(id);
	}

	@Override
	public int delById(int id) {
		int result = positionMapper.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public List<Position> findByDid(int did) {
		PositionExample positionExample = new PositionExample();
		Criteria criteria = positionExample.createCriteria();
		criteria.andDidEqualTo(did);
		List<Position> list = positionMapper.selectByExample(positionExample);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public PageInfo<PositionDto> findPositionList(int pageNo, int pageSize, int did) {
		PageHelper.orderBy("Did DESC");
		PageHelper.startPage(pageNo, pageSize);
		List<PositionDto> list = null;
		if (did != 0) {
			PositionDto positionDto = new PositionDto();
			positionDto.setDid(did);
			list = positionExMapper.findPositionList(positionDto);
		} else {
			list = positionExMapper.findPositionList(null);
		}
		PageInfo<PositionDto> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public  PageInfo<PositionDto> findPositionListByNameLike(int pageNo,int pageSize, String positionName) {
		PageHelper.startPage(pageNo, pageSize);
		List<PositionDto> positionList = positionExMapper.findPositionListByNameLike(positionName);
		PageInfo<PositionDto> pageInfo = new PageInfo<>(positionList);
		return pageInfo;

	}
}
