package com.custom.customManageSystem.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
    
    int selectCountByCondition(@Param("custom") Custom custom, @Param("time1") String time1, @Param("time2") String time2);
    
    List<Custom> selectByConditionPage(@Param("custom") Custom custom, @Param("page") Page page, @Param("time1") String time1, @Param("time2") String time2);
    
    
    int updateByPrimaryKeySelective(Custom custom);

    int updateByPrimaryKey(Custom custom);
}
