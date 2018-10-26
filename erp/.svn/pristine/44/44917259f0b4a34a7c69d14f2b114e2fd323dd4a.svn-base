package cn.caecc.erp.modules.service.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.ex.dto.ConferenceArrangementDto;
import cn.caecc.erp.modules.dao.ex.dto.ConferenceArrangementSaveDto;
import cn.caecc.erp.modules.dao.ex.mapper.ConferenceArrangementExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.ConferenceArrangement;
import cn.caecc.erp.modules.dao.mybatis.entity.ConferenceUserRelationshipExample;
import cn.caecc.erp.modules.dao.mybatis.entity.ConferenceUserRelationshipExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.entity.ConferenceRoom;
import cn.caecc.erp.modules.dao.mybatis.entity.ConferenceUserRelationship;
import cn.caecc.erp.modules.dao.mybatis.mapper.ConferenceArrangementMapper;
import cn.caecc.erp.modules.dao.mybatis.mapper.ConferenceUserRelationshipMapper;
import cn.caecc.erp.modules.service.ConferenceArrangementService;
import cn.caecc.erp.modules.service.ConferenceRoomService;
import cn.caecc.erp.modules.service.MessageService;
import cn.caecc.erp.support.exception.CommonException;

@Service
public class ConferenceArrangementServiceImpl implements ConferenceArrangementService {
	@Autowired
	private ConferenceArrangementMapper caMapper;

	@Autowired
	private ConferenceArrangementExMapper caExMapper;
	@Autowired
	private ConferenceUserRelationshipMapper cuMapper;
	@Autowired
	private ConferenceRoomService conferenceRoomService;
	@Autowired
	private MessageService messageService;

	@Override
	public ConferenceArrangementSaveDto addConferenceArrangement(ConferenceArrangementSaveDto conferenceArrangementSaveDto) {
		// TODO Auto-generated method stub
		ConferenceArrangement conferenceArrangement = conferenceArrangementSaveDto.getConferenceArrangement();
		
		if(conferenceArrangement.getUid() == null){
			
			throw new CommonException("请选择会议发起人");	
		}
		if(conferenceArrangement.getCid() == null){
			
			throw new CommonException("请选择召开会议地点");	
		}
		if(conferenceArrangement.getHostid() == null){
			
			throw new CommonException("请选择会议主持人");	
		}
		if(conferenceArrangement.getTime()== null){
			
			throw new CommonException("请选择会议时间");	
		}
		if(conferenceArrangement.getContractorid()== null){
			
			throw new CommonException("请选择会议承办人");	
		}
		List<Integer> userids = conferenceArrangementSaveDto.getUserids();
		if (userids == null || userids.size() == 0) {
			throw new CommonException("请选中参会人员");
		} else {
			String title = "待参加会议提醒";
			ConferenceRoom conferenceRoom = conferenceRoomService.queryConferenceRoomById(conferenceArrangement.getCid());
			String conferenceRoomName = conferenceRoom.getName();
			Date date = conferenceArrangement.getTime();
			DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String formatDate = dFormat.format(date);
			String content = String.format("请您于%s准时参加在%s召开的会议，会议议题:%s", formatDate, conferenceRoomName, conferenceArrangement.getDescription());
			for (Integer userId : userids) {
				messageService.createConferenceMessage(userId, title, content);
			}
		}
		int i = caMapper.insertSelective(conferenceArrangement);
		if (i > 0) {
			caExMapper.batchInsertCuser(conferenceArrangementSaveDto);
		} else {
			throw new CommonException("会议录入失败");

		}
		return conferenceArrangementSaveDto;
	}

	@Override
	public void updateConferenceArrangement(ConferenceArrangementSaveDto conferenceArrangementSaveDto) {
		// TODO Auto-generated method stub
		//获取参会信息
		ConferenceArrangement conferenceArrangement = conferenceArrangementSaveDto.getConferenceArrangement();
		//参会信息条件筛选
		if(conferenceArrangement.getUid() == null){
			
			throw new CommonException("请选择会议发起人");	
		}
		if(conferenceArrangement.getCid() == null){
			
			throw new CommonException("请选择召开会议地点");	
		}
		if(conferenceArrangement.getHostid() == null){
			
			throw new CommonException("请选择会议主持人");	
		}
		if(conferenceArrangement.getTime()== null){
			
			throw new CommonException("请选择会议时间");	
		}
		if(conferenceArrangement.getContractorid()== null){
			
			throw new CommonException("请选择会议承办人");	
		}
		//获取参会人信息
		List<Integer> userids = conferenceArrangementSaveDto.getUserids();
		//参会人信息筛选
		if (userids == null || userids.size() == 0) {
			throw new CommonException("请选中参会人员");

		}
		//清除原有参会人信息
		ConferenceUserRelationshipExample cuRelationshipExample=new ConferenceUserRelationshipExample();
		Criteria criteria=cuRelationshipExample.createCriteria();
		criteria.andCaidEqualTo(conferenceArrangement.getId());
		cuMapper.deleteByExample(cuRelationshipExample);
		//重新录入参会人信息
		int i = caMapper.updateByPrimaryKeySelective(conferenceArrangement);
		if (i > 0) {
			caExMapper.batchInsertCuser(conferenceArrangementSaveDto);
		    String title = "待参加会议提醒";
			ConferenceRoom conferenceRoom = conferenceRoomService.queryConferenceRoomById(conferenceArrangement.getCid());
			String conferenceRoomName = conferenceRoom.getName();
			Date date = conferenceArrangement.getTime();
			DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String formatDate = dFormat.format(date);
			String content = String.format("请您于%s准时参加在%s召开的会议，会议议题:%s", formatDate, conferenceRoomName, conferenceArrangement.getDescription());
			for (Integer userId : userids) {
				messageService.createConferenceMessage(userId, title, content);
			}
		} else {
			throw new CommonException("会议录入失败");

		}
	}

	@Override
	public List<ConferenceArrangementDto> getConferenceArrangementByType(int type) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);

		return caExMapper.getConferenceArrangementByType(params);

	}

	@Override
	public List<ConferenceArrangementDto> getConferenceArrangementList(
			ConferenceArrangementDto conferenceArrangementDto) {
		// TODO Auto-generated method stub
		return caExMapper.getConferenceArrangementList(conferenceArrangementDto);
	}

	@Override
	public void deleteConferenceArrangement(int id) {
		// TODO Auto-generated method stub
		ConferenceArrangement conferenceArrangement = caMapper.selectByPrimaryKey(id);
		if (conferenceArrangement == null) {
			throw new CommonException("当前会议记录不存在,请刷新重试");
		}
		//获取参会人信息
		ConferenceUserRelationshipExample cuRelationshipExample=new ConferenceUserRelationshipExample();
		Criteria criteria=cuRelationshipExample.createCriteria();
		criteria.andCaidEqualTo(id);
		List<ConferenceUserRelationship> conferenceUserlist=cuMapper.selectByExample(cuRelationshipExample);
		//删除参会人信息
		if(conferenceUserlist!=null&&conferenceUserlist.size()>0){
			cuMapper.deleteByExample(cuRelationshipExample);	
		}
		//删除会议信息
		caMapper.deleteByPrimaryKey(id);
		//发送短信
	    String title = "待参加会议提醒";
		ConferenceRoom conferenceRoom = conferenceRoomService.queryConferenceRoomById(conferenceArrangement.getCid());
		String conferenceRoomName = conferenceRoom.getName();
		Date date = conferenceArrangement.getTime();
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formatDate = dFormat.format(date);
		String content = String.format("您于%s参加在%s召开的会议临时取消召开,望周知", formatDate, conferenceRoomName);
		for (ConferenceUserRelationship cr : conferenceUserlist) {
			messageService.createConferenceMessage(cr.getUid(), title, content);
		}
	}

	@Override
	public ConferenceArrangementDto getConferenceArrangementById(int id) {
		// TODO Auto-generated method stub
		ConferenceArrangementDto conferenceArrangementDto=caExMapper.getConferenceArrangementById(id);
		if(conferenceArrangementDto == null){
			throw new CommonException("记录已不存在，刷新重试");
		}
		return  conferenceArrangementDto;
	}

}
