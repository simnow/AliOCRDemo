package cn.caecc.erp.modules.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.ex.mapper.ConferenceRoomExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.ConferenceRoom;
import cn.caecc.erp.modules.dao.mybatis.mapper.ConferenceRoomMapper;
import cn.caecc.erp.modules.service.ConferenceRoomService;
import cn.caecc.erp.support.exception.CommonException;


@Service
public class ConferenceRoomServiceImpl implements ConferenceRoomService {
	@Autowired
	private ConferenceRoomExMapper crExMapper;

	@Autowired
	private ConferenceRoomMapper crMapper;

	@Override
	public int addConferenceRoom(ConferenceRoom conferenceRoom) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(conferenceRoom.getName())) {
			throw new CommonException("房间名称不能为空");
		}
		if (StringUtils.isBlank(conferenceRoom.getAddress())) {
			throw new CommonException("房间地址不能为空");
		}
		boolean isexist = queryCrbyNameAddress(conferenceRoom.getName(), conferenceRoom.getAddress());
		if (!isexist) {
			throw new CommonException("房间名称或地址已存在");
		}
		return crMapper.insertSelective(conferenceRoom);
	}

	@Override
	public int deleteConferenceRoom(int id) {
		List<ConferenceRoom> resultList = crExMapper.getCrInfoById(id);
		if (resultList != null && resultList.size() > 0) {
			throw new CommonException("会议室已被使用 无法删除");

		}
		// TODO Auto-generated method stub
		return crMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateConferenceRoom(ConferenceRoom conferenceRoom) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(conferenceRoom.getName())) {
			throw new CommonException("房间名称不能为空");

		}
		if (StringUtils.isBlank(conferenceRoom.getAddress())) {
			throw new CommonException("房间地址不能为空");
		}
		boolean isexist = queryCrbyNameAddress(conferenceRoom.getName(), conferenceRoom.getAddress());
		if (!isexist) {
			throw new CommonException("房间名称或地址已存在");
		}
		return crMapper.updateByPrimaryKeySelective(conferenceRoom);
	}

	@Override
	public boolean queryCrbyNameAddress(String name, String address) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		params.put("address", address);
		List<ConferenceRoom> result = crExMapper.getCrByNameAddress(params);
		if (result != null && result.size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public List<ConferenceRoom> getCrList() {
		// TODO Auto-generated method stub
		List<ConferenceRoom> resultList = crMapper.selectByExample(null);
		return resultList;
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.modules.service.ConferenceRoomService#queryConferenceRoomById(int)
	 */
	@Override
	public ConferenceRoom queryConferenceRoomById(int id) {
		// TODO Auto-generated method stub
		ConferenceRoom conferenceRoom = crMapper.selectByPrimaryKey(id);
		return conferenceRoom;
	}

}
