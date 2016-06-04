package com.custom.customManageSystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.custom.customManageSystem.model.Custom;

public interface ICustomDao {
	int deleteByPrimaryKey(Integer customid);

	int deleteMoreByPrimaryKey(Integer[] customids);
	
    int insert(Custom custom);

    int insertSelective(Custom custom);

    Custom selectByPrimaryKey(Integer customid);

    List<Custom> selectByCondition(@Param("custom") Custom custom, @Param("time1") String time1, @Param("time2") String time2);
  
    List<Custom> checkSingle(@Param("licenseplates") String licenseplates, @Param("carframecode") String carframecode);
   
    List<Custom> selectMoreById(Integer[] customids);
    
    int updateByPrimaryKeySelective(Custom custom);

    int updateByPrimaryKey(Custom custom);
}