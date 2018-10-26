package cn.caecc.erp.modules.service.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.ex.dto.GoodsDto;
import cn.caecc.erp.modules.dao.ex.mapper.GoodsExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.Goods;
import cn.caecc.erp.modules.dao.mybatis.mapper.GoodsMapper;
import cn.caecc.erp.modules.service.GoodsService;
import cn.caecc.erp.support.exception.CommonException;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsExMapper goodsExMapper;

	private String superClass;

	@Override
	public int create(Goods goods) {
		int result = 0;
		if (goods != null) {
			this.goodsNum(goods);
			result = goodsMapper.insert(goods);
		}
		return result;
	}

	@Override
	public Goods findByCode(String code) {
		if (code == null || "".equals(code)) {
			return null;
		} else {
			Goods goods = goodsMapper.selectByPrimaryKey(code);
			return goods;
		}
	}

	@Override
	public List<GoodsDto> findGoodsList() {
		List<GoodsDto> list = goodsExMapper.findGoodsList();
		return list;
	}

	@Override
	public int delByCode(String code) {
		int result = 0;
		if (code == null || "".equals(code)) {
			throw new CommonException("物品编码为空");
		} else {
			List<GoodsDto> list = goodsExMapper.findGoodsByPCode(code);
			if (list != null && list.size() > 0) {
				throw new CommonException("请先删除其子类");
			} else {
				result = goodsMapper.deleteByPrimaryKey(code);
			}
		}
		return result;
	}

	@Override
	public List<Goods> findByPcode(Goods goods) {
		List<Goods> list = null;
		if (goods != null) {
			if (goods.getPcode() == null || "".equals(goods.getPcode())) {
				throw new CommonException("父编码为空");
			} else {
				list = goodsExMapper.findByPCode(goods.getPcode());
				
			}
		} else {
			throw new CommonException("参数对象为空");

		}
		return list;
	}

	@Override
	public List<Goods> findByGrade(Goods goods) {
		List<Goods> list = null;
		if (goods != null) {
			if (goods.getGrade() == null || "".equals(String.valueOf(goods.getGrade()))) {
				throw new CommonException("等级为空");
			} else {
				list = goodsExMapper.findByGrade(goods.getGrade());
			}
		} else {
			throw new CommonException("参数对象为空");
		}
		return list;
	}

	@Override
	public int updateByCode(Goods goods) {
		int result = 0;
		if (goods == null) {
			throw new CommonException("参数对象为空");
		} else {
			result = goodsMapper.updateByPrimaryKey(goods);
			
		}
		return result;
	}

	private Goods goodsNum(Goods goods) throws CommonException {
		if (goods == null) {
			throw new CommonException("对象为null");
		} else {
			if (goods.getGrade() == null || "".equals(String.valueOf(goods.getGrade()))) {
				throw new CommonException("未设置等级");
			} else if (goods.getGrade() == 1) {
				if (goods.getPcode() != null) {
					throw new CommonException("等级设置错误！");
				} else {
					int id = this.getId(goods);
					if (id != 0) {
						goods.setId(id);
						superClass = "0" + String.valueOf(id);
						goods.setCode(superClass);
					} else {
						throw new CommonException("获取id失败");
					}
				}
			} else {
				if (goods.getPcode() == null || "".equals(goods.getPcode())) {
					throw new CommonException("父编码为空");
				} else {
					superClass = goods.getPcode().substring(0, 2);
					if ("01".equals(superClass)) {
						if (goods.getGrade() > 5) {
							throw new CommonException("已达到子类上限");
						} else {
							this.setcode(goods, superClass);
						}
					} else {
						if (goods.getGrade() > 4) {
							throw new CommonException("已达到子类上限");
						} else {
							this.setcode(goods, superClass);
						}
					}
				}
			}
		}
		return goods;
	}

	private void setcode(Goods goods, String type) throws CommonException {
		int i = this.getId(goods);
		if (goods.getGrade() == 5 && "01".equals(type)) {
			this.setId(i, goods);
		}else if (goods.getGrade() == 4 && "02".equals(type)) {
			this.setId(i, goods);
		}else {
			if (i == 0) {
				throw new CommonException("获取id失败");
			} else if (i > 99) {
				throw new CommonException("已达到该子类上限");
			} else {
				String id = "";
				if (i < 10) {
					id = "0" + String.valueOf(i);
				} else {
					id = String.valueOf(i);
				}
				goods.setId(i);
				String code = goods.getPcode() + id;
				goods.setCode(code);
			}
		}
	}

	private int getId(Goods goods) {
		List<Goods> list = null;
		if (goods.getGrade() == 1) {
			list = this.findByGrade(goods);
		} else {
			list = this.findByPcode(goods);
		}
		int i = 0;
		if (list != null && list.size() == 0) {
			return ++i;
		}
		if (list != null && list.size() > 0) {
			Goods listGoods = list.get(0);
			i = listGoods.getId();
			return ++i;
		}else {
			return i;
		}
	}
	
	private void setId(int i, Goods goods) throws CommonException {
		if (i == 0) {
			throw new CommonException("获取id失败");
		} else {
			String id = "";
			String str = String.valueOf(i);
			int length = str.length();
			switch (length) {
			case 1:
				id = "0000" + str;
				break;
			case 2:
				id = "000" + str;
				break;
			case 3:
				id = "00" + str;
				break;
			case 4:
				id = "0" + str;
				break;
			case 5:
				id = str;
				break;
			default:
				throw new CommonException("已达到该子类上限");
			}
			goods.setId(i);
			String code = goods.getPcode() + id;
			goods.setCode(code);
		}
	}

	@Override
	public List<Goods> selectGoods() {
		return goodsMapper.selectByExample(null);
	}

	@Override
	public ByteArrayInputStream exportGoods() throws IOException{
		List<Goods> list = selectGoods();
		//创建工作簿  
        HSSFWorkbook workBook = new HSSFWorkbook();  
        //创建工作表  工作表的名字叫物资列表 
        HSSFSheet sheet = workBook.createSheet("物资列表");
        //创建行,第一行  
        HSSFRow row = sheet.createRow(0);  
        //创建单元格，操作第一行各列
        HSSFCell cell = row.createCell(0, CellType.STRING);  
        cell.setCellValue("编号"); 
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("名字"); 
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("物资型号"); 
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("单位"); 
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("物资描述"); 
        for (int i = 0; i < list.size(); i++) {
			row=sheet.createRow(i+1);
			cell = row.createCell(0, CellType.STRING);
			String code=list.get(i).getCode();
			
			cell.setCellValue(code); 
	        cell = row.createCell(1, CellType.STRING);
	        String name=list.get(i).getName();
	        
	        cell.setCellValue(name); 
	        cell = row.createCell(2, CellType.STRING);
	        String model=list.get(i).getModel();
	        
	        cell.setCellValue(model); 
	        cell = row.createCell(3, CellType.STRING);
	        String unit=list.get(i).getUnit();
	        
	        cell.setCellValue(unit); 
	        cell = row.createCell(4, CellType.STRING);
	        String remarks=list.get(i).getRemarks();
	        cell.setCellValue(remarks);
		}
        for (int i = 0; i < 5; i++) {
			// 调整每一列宽度
			sheet.autoSizeColumn(i, true);
		}
       ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
        	//工作簿写入流中
        	workBook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        ByteArrayInputStream is = new ByteArrayInputStream(content);
        os.close();
        workBook.close();
        return is;
	}
}
