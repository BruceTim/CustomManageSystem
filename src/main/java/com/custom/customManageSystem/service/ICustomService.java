package com.custom.customManageSystem.service;

import java.util.List;

import com.custom.customManageSystem.entity.Page;
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
    
    int selectCountByCondition(Custom custom, String time1, String time2);
    
    List<Custom> selectByConditionPage(Custom custom,Page page, String time1, String time2);
    
    int selectCountByDate(String year, String month);
    
    List<Custom> selectByDate(String year, String month);
    
    List<Custom> selectByDatePage(Page page, String year, String month);
    
    int updateByPrimaryKeySelective(Custom custom);

    int updateByPrimaryKey(Custom custom);
}
