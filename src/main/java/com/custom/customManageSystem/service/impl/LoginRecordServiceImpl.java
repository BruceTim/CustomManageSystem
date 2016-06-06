package com.custom.customManageSystem.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.custom.customManageSystem.dao.ILoginRecordDao;
import com.custom.customManageSystem.entity.Page;
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
	public int selectCountByUname(String uname) {
		return this.loginRecordDao.selectCountByUname(uname);
	}

	@Override
	public List<LoginRecord> selectByUnamePage(Page page, String uname) {
		return this.loginRecordDao.selectByUnamePage(page, uname);
	}

}
