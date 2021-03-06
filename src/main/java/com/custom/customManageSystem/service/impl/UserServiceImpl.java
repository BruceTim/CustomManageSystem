package com.custom.customManageSystem.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.custom.customManageSystem.dao.IUserDao;
import com.custom.customManageSystem.entity.Page;
import com.custom.customManageSystem.model.User;
import com.custom.customManageSystem.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}
	@Override
	public User login(User user) {
		return this.userDao.login(user);
	}
	
	@Override
	public boolean addUser(User user) {
		return this.userDao.insert(user) > 0 ;
	}
	@Override
	public boolean deleteUser(int uid) {
		return this.userDao.deleteByPrimaryKey(uid) > 0 ;
	}
	@Override
	public User getUserByName(String uname) {
		return this.userDao.selectByName(uname) ;
	}
	@Override
	public List<User> getUsersByPage(Page page) {
		return this.userDao.selectAllByPage(page);
	}
	@Override
	public boolean updateUser(User user) {
		return this.userDao.updateByPrimaryKeySelective(user) > 0;
	}
	@Override
	public boolean delUsers(Integer[] uids) {
		return this.userDao.deleteMoreByPrimaryKey(uids) > 0;
	}
	@Override
	public int selectAllCount() {
		return this.userDao.selectAllCount();
	}

}
