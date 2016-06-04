package com.custom.customManageSystem.dao;

import java.util.List;

import com.custom.customManageSystem.model.User;

public interface IUserDao {
	int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);
    
    User login(User user);
    
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User selectByName(String uname);

	List<User> selectAll();
	
	int deleteMoreByPrimaryKey(Integer[] uids);
}