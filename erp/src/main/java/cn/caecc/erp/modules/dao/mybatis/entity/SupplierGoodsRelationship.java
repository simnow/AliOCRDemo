package cn.caecc.erp.modules.dao.mybatis.entity;

import java.io.Serializable;

public class SupplierGoodsRelationship implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column supplier_goods_relationship.SupplierID
     *
     * @mbggenerated Wed Aug 15 13:52:14 CST 2018
     */
    private Integer supplierid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column supplier_goods_relationship.GoodCode
     *
     * @mbggenerated Wed Aug 15 13:52:14 CST 2018
     */
    private String goodcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table supplier_goods_relationship
     *
     * @mbggenerated Wed Aug 15 13:52:14 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column supplier_goods_relationship.SupplierID
     *
     * @return the value of supplier_goods_relationship.SupplierID
     *
     * @mbggenerated Wed Aug 15 13:52:14 CST 2018
     */
    public Integer getSupplierid() {
        return supplierid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column supplier_goods_relationship.SupplierID
     *
     * @param supplierid the value for supplier_goods_relationship.SupplierID
     *
     * @mbggenerated Wed Aug 15 13:52:14 CST 2018
     */
    public void setSupplierid(Integer supplierid) {
        this.supplierid = supplierid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column supplier_goods_relationship.GoodCode
     *
     * @return the value of supplier_goods_relationship.GoodCode
     *
     * @mbggenerated Wed Aug 15 13:52:14 CST 2018
     */
    public String getGoodcode() {
        return goodcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column supplier_goods_relationship.GoodCode
     *
     * @param goodcode the value for supplier_goods_relationship.GoodCode
     *
     * @mbggenerated Wed Aug 15 13:52:14 CST 2018
     */
    public void setGoodcode(String goodcode) {
        this.goodcode = goodcode;
    }
}