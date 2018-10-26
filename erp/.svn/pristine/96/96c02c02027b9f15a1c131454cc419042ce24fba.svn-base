package cn.caecc.erp.modules.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import cn.caecc.erp.modules.dao.ex.dto.GoodsDto;
import cn.caecc.erp.modules.dao.mybatis.entity.Goods;

public interface GoodsService {

	public int create(Goods goods);
	
	public Goods findByCode(String code);
	
	public List<GoodsDto> findGoodsList();
	
	public List<Goods> selectGoods();
	
	public int delByCode(String code);
	
	public List<Goods> findByPcode(Goods goods);
	
	public List<Goods> findByGrade(Goods goods);
	
	public int updateByCode(Goods goods);
	
	public ByteArrayInputStream exportGoods()throws IOException;
}
