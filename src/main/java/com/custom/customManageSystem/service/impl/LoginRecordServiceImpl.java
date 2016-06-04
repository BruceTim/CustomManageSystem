package com.custom.customManageSystem.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.custom.customManageSystem.dao.ILoginRecordDao;
import com.custom.customManageSystem.model.LoginRecord;
import com.custom.customManageSystem.service.ILoginRecordService;

@Service("loginRecordService")
public class LoginRecordServiceImpl implements ILoginRecordService{
	
	@Resource
	private ILoginRecordDao loginRecordDao;
	
	@Override
	public int deleteByPrimaryKey(Integer recordid) {
		return this.loginRecordDao.deleteByPrimaryKey(recordid);
	}

	@Override
	public int deleteMoreByPrimaryKey(Integer[] recordids) {
		return this.loginRecordDao.deleteMoreByPrimaryKey(recordids);
	}

	@Override
	public int insert(LoginRecord record) {
		return this.loginRecordDao.insert(record);
	}

	@Override
	public List<LoginRecord> selectByUid(Integer recordid) {
		return this.loginRecordDao.selectByUid(recordid);
	}

	@Override
	public List<LoginRecord> selectByUname(String uname) {
		return this.loginRecordDao.selectByUname(uname);
	}

	@Override
	public List<LoginRecord> selectAll() {
		return this.loginRecordDao.selectAll();
	}

}
