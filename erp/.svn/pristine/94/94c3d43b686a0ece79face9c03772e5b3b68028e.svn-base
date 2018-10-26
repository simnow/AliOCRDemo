package cn.caecc.erp.modules.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.PositionDto;
import cn.caecc.erp.modules.dao.mybatis.entity.Position;

public interface PositionService {

	public int createPosition(Position position);
	
	public int update(Position position);
	
	public PositionDto findById(int id);
	
	public int delById(int id);
	
	public List<Position> findByDid(int did);
	
	public PageInfo<PositionDto> findPositionList(int pageNo,int pageSize,int did);
	
	public  PageInfo<PositionDto> findPositionListByNameLike(int pageNo,int pageSize, String positionName);

}
