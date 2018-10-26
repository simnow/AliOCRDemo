package cn.caecc.erp.modules.service.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.mybatis.entity.MessageType;
import cn.caecc.erp.modules.dao.mybatis.mapper.MessageTypeMapper;
import cn.caecc.erp.modules.service.MessageTypeService;

@Service
public class MessageTypeServiceImpl implements MessageTypeService {

	@Autowired
	private MessageTypeMapper messageTypeMapper;

	@Override
	public List<MessageType> getMessageTypeList() {
		// TODO Auto-generated method stub
		return messageTypeMapper.selectByExample(null);
	}
}
