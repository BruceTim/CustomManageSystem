package com.custom.customManageSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.custom.customManageSystem.entity.Page;
import com.custom.customManageSystem.model.LoginRecord;

public interface ILoginRecordDao {
    int deleteByPrimaryKey(Integer recordid);
    
    int deleteMoreByPrimaryKey(Integer[] recordids);

    int insert(LoginRecord record);
    
    int selectCountByUname(@Param("uname") String uname);
    
    List<LoginRecord> selectByUnamePage(@Param("page") Page page, @Param("uname") String uname);

}