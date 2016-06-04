package com.custom.customManageSystem.service;

import java.util.List;

import com.custom.customManageSystem.model.Custom;

public interface ICustomService {

	int deleteByPrimaryKey(Integer customid);

	int deleteMoreByPrimaryKey(Integer[] customids);
	
    int insert(Custom custom);

    int insertSelective(Custom custom);

    Custom selectByPrimaryKey(Integer customid);

    List<Custom> selectByCondition(Custom custom, String time1, String time2);
    
    List<Custom> outPut(Integer[] customids);
    
    List<Custom> checkSingle(String carcode, String carframecode);
    
    int updateByPrimaryKeySelective(Custom custom);

    int updateByPrimaryKey(Custom custom);
}
