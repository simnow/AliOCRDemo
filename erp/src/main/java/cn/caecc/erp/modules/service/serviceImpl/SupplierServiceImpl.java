package cn.caecc.erp.modules.service.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.dto.SupplierExDto;
import cn.caecc.erp.modules.dao.ex.mapper.SupplierExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.Goods;
import cn.caecc.erp.modules.dao.mybatis.entity.Supplier;
import cn.caecc.erp.modules.dao.mybatis.entity.SupplierExample;
import cn.caecc.erp.modules.dao.mybatis.entity.SupplierExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.entity.SupplierGoodsRelationship;
import cn.caecc.erp.modules.dao.mybatis.mapper.SupplierMapper;
import cn.caecc.erp.modules.service.SupplierGoodsRelationshipService;
import cn.caecc.erp.modules.service.SupplierService;
import cn.caecc.erp.support.exception.CommonException;
@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierMapper supplierMapper;
	@Autowired
	private SupplierExMapper supplierExMapper;
	@Autowired
	private SupplierGoodsRelationshipService sgrService;
	
	@Override
	public PageInfo<SupplierExDto> getList(int pageNo, int pageSize, Integer isQualified, String name, String code) {
		PageHelper.startPage(pageNo, pageSize);
		PageHelper.orderBy("Id ASC");
		List<String> strings = null;
		if (code != null && !"".equals(code)) {
			strings = new ArrayList<>();
			String[] split = code.split(",");
			for (String string : split) {
				strings.add(string);
			}
		}
		List<SupplierExDto> list = supplierExMapper.getList(isQualified, name, strings);
		if (list != null && list.size() > 0) {
			PageInfo<SupplierExDto> pageInfo = new PageInfo<>(list);
			if (pageInfo != null) {
				return pageInfo; 
			}
		}
		return null;
	}

	@Override
	public SupplierExDto create(SupplierExDto supplier) {
		if (supplier == null) {
			throw new CommonException("参数异常");
		}else {
			Supplier sup = this.getSupplier(supplier);
			int result = supplierMapper.insertSelective(sup);
			if (result <= 0) {
				throw new CommonException("创建失败");
			}else {
				if (supplier.getList() != null && supplier.getList().size() > 0) {
					for (SupplierGoodsRelationship supplierGoodsRelationship : supplier.getList()) {
						supplierGoodsRelationship.setSupplierid(sup.getId());
					}
					sgrService.batchAdd(supplier.getList());
				}
			}
		}
		return supplier;
	}

	@Override
	public SupplierExDto findDetail(int id) {
		return supplierExMapper.findDetail(id);
	}

	@Override
	public Supplier findById(int id) {
		return supplierMapper.selectByPrimaryKey(id);
	}

	@Override
	public SupplierExDto update(SupplierExDto supplier) {
		if (supplier == null) {
			throw new CommonException("参数异常");

		}else {
			Supplier findById = this.findById(supplier.getId());
			if (findById == null) {
				throw new CommonException("需要更新的供应商不存在");
			}else {
				Supplier sup = this.getSupplier(supplier);
				int result = supplierMapper.updateByPrimaryKey(sup);
				if (result <= 0) {
					throw new CommonException("更新失败");
				}else {
					if (supplier.getList() != null) {
						int ret = sgrService.batchUpdate(supplier.getId(),supplier.getList());
						if (ret != supplier.getList().size()) {
							throw new CommonException("更新失败");
						}
					}
				}
			}
		}
		return supplier;
	}

	@Override
	public Supplier updateIsQualified(Supplier supplier) {
		if (supplier == null) {
			throw new CommonException("参数异常");
		}else {
			Supplier findById = this.findById(supplier.getId());
			if (findById == null) {
				throw new CommonException("需要更新的供应商不存在");
			}else {
				int result = supplierMapper.updateByPrimaryKeySelective(supplier);
				if (result <= 0) {
					throw new CommonException("更新失败");
				}
			}
		}
		return supplier;
	}
	
	@Override
	public int deleteById(int id) {
		int result = supplierMapper.deleteByPrimaryKey(id);
		return result;
	}

	public Supplier getSupplier(SupplierExDto supplierExDto) {
		Supplier sup= new Supplier();
		sup.setId(supplierExDto.getId());
		sup.setName(supplierExDto.getName());
		sup.setServicecontent(supplierExDto.getServicecontent());
		sup.setTelephone(supplierExDto.getTelephone());
		sup.setAddress(supplierExDto.getAddress());
		sup.setIsqualified(supplierExDto.getIsqualified());
		sup.setContacts(supplierExDto.getContacts());
		sup.setBusinesslicenseurl(supplierExDto.getBusinesslicenseurl());
		return sup;
	}
	
	@Override
	public ByteArrayInputStream exportSuppliers() throws IOException {
		SupplierExample supplierExample = new SupplierExample();
		Criteria criteria = supplierExample.createCriteria();
		criteria.andIdIsNotNull();
		List<Supplier> list = supplierMapper.selectByExample(supplierExample);
		//创建工作簿  
        HSSFWorkbook workBook = new HSSFWorkbook();  
        //创建工作表  工作表的名字叫物资列表 
        HSSFSheet sheet = workBook.createSheet("供应商列表");
        //创建行,第一行  
        HSSFRow row = sheet.createRow(0);  
        //创建单元格，操作第一行各列
        HSSFCell cell = row.createCell(0, CellType.STRING);  
        cell.setCellValue("供应商编码    "); 
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("供应商名称"); 
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("供应商联系人");
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("供应商联系电话");
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("供应商服务内容"); 
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("供应商地址"); 
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("供应商评价    "); 
        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("供应商分类    "); 
         
        for (int i = 0; i < list.size(); i++) {
			row=sheet.createRow(i+1);
			cell = row.createCell(0, CellType.STRING);
			Integer code=list.get(i).getId();
			cell.setCellValue(code); 
			
	        cell = row.createCell(1, CellType.STRING);
	        String name=list.get(i).getName();
	        cell.setCellValue(name); 
	        
	        cell = row.createCell(4, CellType.STRING);
	        String serviceContent=list.get(i).getServicecontent();
	        cell.setCellValue(serviceContent); 
	        
	        cell = row.createCell(2, CellType.STRING);
	        String contacts=list.get(i).getContacts();
	        cell.setCellValue(contacts); 
	        
	        cell = row.createCell(3, CellType.STRING);
	        String telephone=list.get(i).getTelephone();
	        cell.setCellValue(telephone);
	        
	        cell = row.createCell(5, CellType.STRING);
	        String address=list.get(i).getAddress();
	        cell.setCellValue(address);
	        
	        cell = row.createCell(6, CellType.STRING);
	        Integer isQualified=list.get(i).getIsqualified();
	        if (isQualified==1) {
	        	cell.setCellValue("合格");
			}else{
				cell.setCellValue("不合格");
			}
	        
	        cell = row.createCell(7, CellType.STRING);
	        List<Goods>goodsName=supplierExMapper.findGoodsName(list.get(i).getId());
	        String gname="";
	        if (goodsName!=null) {
		        for (int j = 0; j < goodsName.size(); j++) {
		        	if (j==goodsName.size()-1) {
		        		gname=gname+goodsName.get(j).getName();
					}else{
						gname=gname+goodsName.get(j).getName()+" / ";
					}
				}
	        }
	        cell.setCellValue(gname);
		}
        
        for (int i = 0; i < 8; i++) {
			// 调整每一列宽度
        	//sheet.setColumnWidth(i,30* 512); 
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

	/* (non-Javadoc)
	 * @see cn.caecc.erp.modules.service.SupplierService#generateBusinessLicenseOssKey(int, java.lang.String)
	 */
	@Override
	public String generateBusinessLicenseOssKey(Integer supplierId, String name) {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
		String path = "supplier/" + supplierId.toString() +  "/" +  uuid + "-" + name;
		return path;
	}

}
