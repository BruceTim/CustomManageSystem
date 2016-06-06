package com.custom.customManageSystem.service;

import java.util.List;

import com.custom.customManageSystem.entity.Page;
import com.custom.customManageSystem.model.User;

public interface IUserService {
	public User getUserById(int userId);
	public User login(User user);
	public boolean addUser(User user);
	public boolean deleteUser(int uid);
	public User getUserByName(String uname);
	
	public int selectAllCount();
	
	public List<User> getUsersByPage(Page page);
	
	public boolean updateUser(User user);
	public boolean delUsers(Integer[] uids);
}
