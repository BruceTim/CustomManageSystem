package com.custom.customManageSystem.service;

import java.util.List;

import com.custom.customManageSystem.entity.Page;
import com.custom.customManageSystem.model.LoginRecord;

public interface ILoginRecordService {

	int deleteByPrimaryKey(Integer recordid);
    
    int deleteMoreByPrimaryKey(Integer[] recordids);

    int insert(LoginRecord record);

    int selectCountByUname(String uname);
    
    List<LoginRecord> selectByUnamePage(Page page, String uname);
    
}
