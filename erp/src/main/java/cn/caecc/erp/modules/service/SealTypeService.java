package cn.caecc.erp.modules.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.mybatis.entity.SealType;

public interface SealTypeService {
	
	public int addSealType(SealType sealType);
	
	
	public int deleteSealType(int id);
	
	
	public int updateSealType(SealType sealType);
	
	
	public boolean queryStByName(String name);
	
	public List<SealType>   getStAllList();
	
	
	public PageInfo<SealType>  getStPageList(int pageno,int pagesize);

}
