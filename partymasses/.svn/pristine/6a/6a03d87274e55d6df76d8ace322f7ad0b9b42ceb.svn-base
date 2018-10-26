package com.partymasses.modules.dao.ex.mapper;

import com.partymasses.modules.dao.mybatis.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserExMapper {
    int addUsers(@Param("users") List<User> userlist);
    List<User> selectAll();
    List<User> selectAllByUnitId(@Param("unitId") int unitId);
}