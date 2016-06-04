package com.custom.customManageSystem.service;

import java.util.List;

import com.custom.customManageSystem.model.LoginRecord;

public interface ILoginRecordService {

	int deleteByPrimaryKey(Integer recordid);
    
    int deleteMoreByPrimaryKey(Integer[] recordids);

    int insert(LoginRecord record);

    List<LoginRecord> selectByUid(Integer recordid);
    
    List<LoginRecord> selectByUname(String uname);

    List<LoginRecord> selectAll();
}
