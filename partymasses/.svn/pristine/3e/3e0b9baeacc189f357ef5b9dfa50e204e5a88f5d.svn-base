package com.partymasses.modules.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymasses.modules.dao.mybatis.entity.Limit;
import com.partymasses.modules.dao.mybatis.entity.LimitExample;
import com.partymasses.modules.dao.mybatis.entity.LimitExample.Criteria;
import com.partymasses.modules.dao.mybatis.mapper.LimitMapper;
import com.partymasses.modules.service.LimitService;
import com.partymasses.support.message.Message;
@Service
public class LimitServiceImpl implements LimitService {
	
	@Autowired
	private LimitMapper limitMapper;
	
	private final static Logger logger = LoggerFactory.getLogger(LimitServiceImpl.class);
	
	@Override
	public Limit findByUnitId(int unitId) {
		LimitExample limitExample = new LimitExample();
		Criteria criteria = limitExample.createCriteria();
		criteria.andUnitidEqualTo(unitId);
		List<Limit> list = null;
		try {
			list = limitMapper.selectByExample(limitExample);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (list != null && list.size() == 1) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Message update(Limit limit) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int result = limitMapper.updateByPrimaryKey(limit);
			if (result > 0) {
				message.setSuccess(true);
			}else {
				message.setMsg("更新失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			message.setMsg(e.getCause().getMessage());
		}
		return message;
	}

}
