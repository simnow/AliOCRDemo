package cn.caecc.erp.modules.dao.mybatis.mapper;

import cn.caecc.erp.modules.dao.mybatis.entity.Goods;
import cn.caecc.erp.modules.dao.mybatis.entity.GoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 24 14:05:44 CST 2018
     */
    int countByExample(GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 24 14:05:44 CST 2018
     */
    int deleteByExample(GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 24 14:05:44 CST 2018
     */
    int deleteByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 24 14:05:44 CST 2018
     */
    int insert(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 24 14:05:44 CST 2018
     */
    int insertSelective(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 24 14:05:44 CST 2018
     */
    List<Goods> selectByExample(GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 24 14:05:44 CST 2018
     */
    Goods selectByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 24 14:05:44 CST 2018
     */
    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 24 14:05:44 CST 2018
     */
    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 24 14:05:44 CST 2018
     */
    int updateByPrimaryKeySelective(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 24 14:05:44 CST 2018
     */
    int updateByPrimaryKey(Goods record);
}