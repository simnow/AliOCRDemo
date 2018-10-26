package cn.caecc.erp.modules.service.serviceImpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.dto.ContractActivitiApplyExDto;
import cn.caecc.erp.modules.dao.ex.dto.ContractGoodsRelationshipDto;
import cn.caecc.erp.modules.dao.ex.mapper.ContractGoodsRelationshipExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.ContractGoodsRelationship;
import cn.caecc.erp.modules.dao.mybatis.entity.ContractGoodsRelationshipExample;
import cn.caecc.erp.modules.dao.mybatis.entity.ContractGoodsRelationshipExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.entity.Goods;
import cn.caecc.erp.modules.dao.mybatis.mapper.ContractGoodsRelationshipMapper;
import cn.caecc.erp.modules.dao.mybatis.mapper.GoodsMapper;
import cn.caecc.erp.modules.service.ContractGoodsRelationshipService;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.ExcelUtil;

@Service
public class ContractGoodsRelationshipServiceImpl implements ContractGoodsRelationshipService {

	@Autowired
	private ContractGoodsRelationshipMapper cgRelationshipMapper;
	@Autowired
	private ContractGoodsRelationshipExMapper cgRelationshipExMapper;
	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public List<ContractGoodsRelationship> create(List<ContractGoodsRelationship> list) {
		if (list != null) {
			// 否则保存
			// int result = cgRelationshipMapper.insertSelective(contractGoodsRelationship);
			int result = cgRelationshipExMapper.batchAdd(list);
			if (result <= 0) {
				throw new CommonException("创建失败");
			}
		} else {
			throw new CommonException("参数异常");
		}
		return list;
	}

	@Override
	public ContractGoodsRelationship findById(int id) {
		return cgRelationshipMapper.selectByPrimaryKey(id);
	}

	@Override
	public ContractGoodsRelationship findDetail(int id) {
		return cgRelationshipExMapper.findDetail(id);
	}

	@Override
	public PageInfo<ContractGoodsRelationshipDto> getList(int pageNo, int pageSize, String gcode) {
		PageHelper.orderBy("CId DESC");
		PageHelper.startPage(pageNo, pageSize);
		List<ContractGoodsRelationshipDto> list = cgRelationshipExMapper.getList(gcode);
		PageInfo<ContractGoodsRelationshipDto> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 只用于驳回后更新
	 */
	@Override
	public Message batchUpdate(ContractActivitiApplyExDto contractActivitiApplyExDto) {
		Message message = new Message();
		message.setSuccess(false);
		ContractGoodsRelationshipExample example = new ContractGoodsRelationshipExample();
		Criteria criteria = example.createCriteria();
		criteria.andCidEqualTo(contractActivitiApplyExDto.getId());
		List<ContractGoodsRelationship> selectByExample = cgRelationshipMapper.selectByExample(example);
		if ((selectByExample != null && selectByExample.size() > 0)
				|| (contractActivitiApplyExDto.getList() == null
						&& (selectByExample != null && selectByExample.size() > 0))
				|| (contractActivitiApplyExDto.getList().size() == 0
						&& (selectByExample != null && selectByExample.size() > 0))) {
			int ret = cgRelationshipMapper.deleteByExample(example);
			if (ret > 0) {
				int result = cgRelationshipExMapper.batchAdd(contractActivitiApplyExDto.getList());
				if (result > 0) {
					message.setObj(contractActivitiApplyExDto.getList());
					message.setSuccess(true);
				} else {
					message.setMsg("更新失败");
				}
			}
		} else {
			message.setObj(contractActivitiApplyExDto.getList());
			message.setSuccess(true);
		}
		return message;
	}

	/**
	 * 更新
	 */
	@Override
	public ContractActivitiApplyExDto update(ContractActivitiApplyExDto contractActivitiApplyExDto) {
		if (contractActivitiApplyExDto == null || contractActivitiApplyExDto.getList() == null
				|| contractActivitiApplyExDto.getList().size() == 0) {
			throw new CommonException("参数异常");
		} else {
			List<Integer> idList = new ArrayList<>();
			for (ContractGoodsRelationship cgr : contractActivitiApplyExDto.getList()) {
				idList.add(cgr.getId());
			}
			List<ContractGoodsRelationship> list = cgRelationshipExMapper.batchFind(idList);
			if (list != null && list.size() == contractActivitiApplyExDto.getList().size()) {
				int update = cgRelationshipExMapper.batchUpdate(contractActivitiApplyExDto.getList());
				if (update != contractActivitiApplyExDto.getList().size()) {
					throw new CommonException("更新失败");
				}
			} else {
				throw new CommonException("更新对象数据异常");
			}
		}
		return contractActivitiApplyExDto;
	}

	@Override
	public int deleteById(int id) {
		int result = 0;
		ContractGoodsRelationship findById = this.findById(id);
		if (findById == null) {
			throw new CommonException("更新的对象不存在");
		} else {
			result = cgRelationshipMapper.deleteByPrimaryKey(id);
		}
		return result;
	}

	@Override
	public List<ContractGoodsRelationship> exprotExcel(InputStream in, String fileName, int contractId)
			throws Exception {
		// TODO Auto-generated method stub

		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return exportBeforeExcel(in, contractId);
		} else if ("xlsx".equals(extension)) {
			return exportLatestExcel(in, contractId);
		} else {
			throw new CommonException("文件格式不匹配");
		}

	}

	@SuppressWarnings("deprecation")
	public List<ContractGoodsRelationship> exportLatestExcel(InputStream in, int contractId) throws Exception {

		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		XSSFRow xssfRow = null;
		XSSFCell xssfCell = null;
		String code = "";
		// 表头集合
		List<String> headerList = new ArrayList<String>();
		// 循环获取行
		List<ContractGoodsRelationship> dataList = new ArrayList<ContractGoodsRelationship>();
		// 获取数据库存在物品列表
		List<Goods> existGoodsList = goodsMapper.selectByExample(null);
		// 设置读取列数为表头列数
		int rowNum = 0;
		// 读取EXCEL
		for (int i = xssfSheet.getFirstRowNum(); i < xssfSheet.getLastRowNum() + 1; i++) {
			xssfRow = xssfSheet.getRow(i);
			// i判断行是否null 为null进入下一行
			if (xssfRow == null) {
				continue;
			}
			// 保存表头信息
			if (i == xssfSheet.getFirstRowNum()) {
				// 设置列数
				rowNum = xssfRow.getLastCellNum();
				for (int n = xssfRow.getFirstCellNum(); n < rowNum; n++) {
					xssfCell = xssfRow.getCell(n);
					// 如果表头信息为null返回异常
					if (xssfCell == null || xssfCell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
						xssfWorkbook.close();
						throw new CommonException("excel表头信息需要填写");
					}
					headerList.add(xssfCell.getStringCellValue());
				}

			}
			// 循环获取内容信息
			else {
				ContractGoodsRelationship cgRelation = new ContractGoodsRelationship();
				cgRelation.setCid(contractId);
				for (int j = xssfRow.getFirstCellNum(); j < rowNum; j++) {
					xssfCell = xssfRow.getCell(j);
					String headerName = headerList.get(j);
					if (xssfCell == null || xssfCell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
						xssfWorkbook.close();
						throw new CommonException("excel第" + (i + 1) + "记录" + headerName + "是必填项");
					}
					switch (headerName) {
					case "编码":
						code = ExcelUtil.getCellValueByType(xssfCell);
						if (!isExistGoods(code, existGoodsList)) {
							xssfWorkbook.close();
							throw new CommonException("excel第" + (i + 1) + "条记录物品编码不存在，请进行核对");
						} else if (!isExistContractGoods(code, dataList)) {
							xssfWorkbook.close();
							throw new CommonException("excel存在多条编码为" + code + "记录，每天记录只能存在一条");
						} else {
							cgRelation.setGcode(code);
							break;
						}
					case "单价":
						if (xssfCell.getCellType() != XSSFCell.CELL_TYPE_NUMERIC) {
							xssfWorkbook.close();
							throw new CommonException("excel第" + (i + 1) + "条记录单价格式不符，必须为数值形式");
						}
						cgRelation.setUnitprice(xssfCell.getNumericCellValue());
						break;
					case "数量":
						if (xssfCell.getCellType() != XSSFCell.CELL_TYPE_NUMERIC) {
							xssfWorkbook.close();
							throw new CommonException("excel第" + (i + 1) + "条记录单价格式不符，必须为数值形式");
						}
						cgRelation.setCount((int) xssfCell.getNumericCellValue());
						break;
					default:
						xssfWorkbook.close();
						throw new CommonException("Excel表头不匹配，应为编码，数量，单价");
					}
				}
				dataList.add(cgRelation);
			}

		}
		xssfWorkbook.close();
		return dataList;
	}

	@SuppressWarnings("deprecation")
	public List<ContractGoodsRelationship> exportBeforeExcel(InputStream in, int contractId) throws Exception {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		HSSFRow hssfRow = null;
		HSSFCell hssfCell = null;
		String code = "";
		// 表头集合
		List<String> headerList = new ArrayList<String>();
		// 循环获取行
		List<ContractGoodsRelationship> dataList = new ArrayList<ContractGoodsRelationship>();
		// 获取数据库存在物品列表
		List<Goods> existGoodsList = goodsMapper.selectByExample(null);
		// 设置读取列数为表头列数
		int rowNum = 0;
		// 读取EXCEL
		for (int i = hssfSheet.getFirstRowNum(); i < hssfSheet.getLastRowNum() + 1; i++) {
			hssfRow = hssfSheet.getRow(i);
			// i判断行是否null 为null进入下一行
			if (hssfRow == null) {
				continue;
			}
			// 保存表头信息
			if (i == hssfSheet.getFirstRowNum()) {
				// 设置列数
				rowNum = hssfRow.getLastCellNum();
				for (int n = hssfRow.getFirstCellNum(); n < rowNum; n++) {
					hssfCell = hssfRow.getCell(n);
					// 如果表头信息为null返回异常
					if (hssfCell == null || hssfCell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
						hssfWorkbook.close();
						throw new CommonException("excel表头信息需要填写");
					}
					headerList.add(hssfCell.getStringCellValue());
				}

			}
			// 循环获取内容信息
			else {
				ContractGoodsRelationship cgRelation = new ContractGoodsRelationship();
				cgRelation.setCid(contractId);
				for (int j = hssfRow.getFirstCellNum(); j < rowNum; j++) {
					hssfCell = hssfRow.getCell(j);
					String headerName = headerList.get(j);
					if (hssfCell == null || hssfCell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
						hssfWorkbook.close();
						throw new CommonException("excel第" + (i + 1) + "记录" + headerName + "是必填项");
					}
					switch (headerName) {
					case "编码":
						code = ExcelUtil.getCellValueByType(hssfCell);
						if (!isExistGoods(code, existGoodsList)) {
							hssfWorkbook.close();
							throw new CommonException("excel第" + (i + 1) + "条记录物品编码不存在，请进行核对");
						} else if (!isExistContractGoods(code, dataList)) {
							hssfWorkbook.close();
							throw new CommonException("excel存在多条编码为" + code + "记录，每天记录只能存在一条");
						} else {
							cgRelation.setGcode(code);
							break;
						}
					case "单价":
						if (hssfCell.getCellType() != XSSFCell.CELL_TYPE_NUMERIC) {
							hssfWorkbook.close();
							throw new CommonException("excel第" + (i + 1) + "条记录单价格式不符，必须为数值形式");
						}
						cgRelation.setUnitprice(hssfCell.getNumericCellValue());
						break;
					case "数量":
						if (hssfCell.getCellType() != XSSFCell.CELL_TYPE_NUMERIC) {
							hssfWorkbook.close();
							throw new CommonException("excel第" + (i + 1) + "条记录单价格式不符，必须为数值形式");
						}
						cgRelation.setCount((int) hssfCell.getNumericCellValue());
						break;
					default:
						hssfWorkbook.close();
						throw new CommonException("Excel表头不匹配，应为编码，数量，单价");
					}
				}
				dataList.add(cgRelation);
			}

		}
		hssfWorkbook.close();
		return dataList;
	}

	public boolean isExistGoods(String code, List<Goods> goodsList) {
		if (goodsList != null && goodsList.size() > 0) {
			for (int i = 0; i < goodsList.size(); i++) {

				if (code.equals(goodsList.get(i).getCode())) {
					return true;
				}
			}
		}
		return false;

	}

	public boolean isExistContractGoods(String code, List<ContractGoodsRelationship> cgList) {
		if (cgList != null && cgList.size() > 0) {
			for (int i = 0; i < cgList.size(); i++) {

				if (code.equals(cgList.get(i).getGcode())) {
					return false;
				}
			}
		}
		return true;

	}

}
