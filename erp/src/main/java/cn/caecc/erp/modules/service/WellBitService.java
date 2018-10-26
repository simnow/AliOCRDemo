package cn.caecc.erp.modules.service;

import java.util.Date;

import cn.caecc.erp.modules.dao.mybatis.entity.WellBit;
import cn.caecc.erp.support.message.Message;

public interface WellBitService {

	int insertWellBit(WellBit wellBit);

	boolean deleteWellBit(int id);

	WellBit getWellBitById(int id);

	Message listWellBit(int wellId);

	boolean updateWellBit(WellBit wellBit);

	WellBit getOneDay(Date getcurrentDate, int wellId);

	Message listWellBitByWellId(int wellId, int pageNo, int pageSize);

}
