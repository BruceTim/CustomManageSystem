package com.custom.customManageSystem.dao;

import java.util.List;

import com.custom.customManageSystem.model.LoginRecord;

public interface ILoginRecordDao {
    int deleteByPrimaryKey(Integer recordid);
    
    int deleteMoreByPrimaryKey(Integer[] recordids);

    int insert(LoginRecord record);

    List<LoginRecord> selectByUid(Integer uid);
    
    List<LoginRecord> selectByUname(String uname);

    List<LoginRecord> selectAll();
}