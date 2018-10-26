package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.mybatis.entity.WellWdDaily;

public interface WellWdDailyExMapper {

	int batchAdd(@Param("wdDailylist")List<WellWdDaily> list);

//	int batchUpdate(@Param("wdDailylist") List<WellWdDaily> record);
}
