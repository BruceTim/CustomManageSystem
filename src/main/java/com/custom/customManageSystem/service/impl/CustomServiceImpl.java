package com.custom.customManageSystem.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.custom.customManageSystem.dao.ICustomDao;
import com.custom.customManageSystem.entity.Page;
import com.custom.customManageSystem.model.Custom;
import com.custom.customManageSystem.service.ICustomService;

@Service("customService")
public class CustomServiceImpl implements ICustomService{

	@Resource
	private ICustomDao customDao;

	@Override
	public int deleteByPrimaryKey(Integer customid) {
		return this.customDao.deleteByPrimaryKey(customid);
	}

	@Override
	public int insert(Custom custom) {
		return this.customDao.insert(custom);
	}

	@Override
	public int insertSelective(Custom custom) {
		return this.customDao.insertSelective(custom);
	}

	@Override
	public Custom selectByPrimaryKey(Integer customid) {
		return this.customDao.selectByPrimaryKey(customid);
	}

	@Override
	public List<Custom> selectByCondition(Custom custom, String time1, String time2) {
		return this.customDao.selectByCondition(custom, time1, time2);
	}

	@Override
	public int updateByPrimaryKeySelective(Custom custom) {
		return this.customDao.updateByPrimaryKeySelective(custom);
	}

	@Override
	public int updateByPrimaryKey(Custom custom) {
		return this.customDao.updateByPrimaryKey(custom);
	}

	@Override
	public int deleteMoreByPrimaryKey(Integer[] customids) {
		return this.customDao.deleteMoreByPrimaryKey(customids);
	}

	@Override
	public List<Custom> outPut(Integer[] customids) {
		return this.customDao.selectMoreById(customids);
	}

	@Override
	public List<Custom> checkSingle(String carcode, String carframecode) {
		return this.customDao.checkSingle(carcode, carframecode);
	}

	@Override
	public int selectCountByCondition(Custom custom, String time1, String time2) {
		return this.customDao.selectCountByCondition(custom, time1, time2);
	}

	@Override
	public List<Custom> selectByConditionPage(Custom custom, Page page,
			String time1, String time2) {
		return this.customDao.selectByConditionPage(custom, page, time1, time2);
	}

	@Override
	public int selectCountByDate(String year, String month) {
		return this.customDao.selectCountByDate(year, month);
	}

	@Override
	public List<Custom> selectByDatePage(Page page, String year, String month) {
		return this.customDao.selectByDatePage(page, year, month);
	}

	@Override
	public List<Custom> selectByDate(String year, String month) {
		return this.customDao.selectByDate(year, month);
	}
	
	
}
