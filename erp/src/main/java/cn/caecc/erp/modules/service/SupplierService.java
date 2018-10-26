package cn.caecc.erp.modules.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.dto.SupplierExDto;
import cn.caecc.erp.modules.dao.mybatis.entity.Supplier;

public interface SupplierService {

	public SupplierExDto create(SupplierExDto supplier);

	public Supplier findById(int id);

	public PageInfo<SupplierExDto> getList(int pageNo, int pageSize, Integer isQualified, String name, String code);

	public SupplierExDto update(SupplierExDto supplier);

	public int deleteById(int id);

	public ByteArrayInputStream exportSuppliers() throws IOException;

	public String generateBusinessLicenseOssKey(Integer supplierId, String name);

	public SupplierExDto findDetail(int id);

	public Supplier updateIsQualified(Supplier supplier);
}
