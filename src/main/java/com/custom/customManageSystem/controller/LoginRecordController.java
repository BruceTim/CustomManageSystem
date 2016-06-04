package com.custom.customManageSystem.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.custom.customManageSystem.model.LoginRecord;
import com.custom.customManageSystem.model.User;
import com.custom.customManageSystem.service.ILoginRecordService;

@Controller
@RequestMapping("/loginRecord")
public class LoginRecordController {

	@Resource
	private ILoginRecordService loginRecordService;
	
	@RequestMapping("/showRecord")
	public String showRecord(HttpSession session, ModelMap model){
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "redirect:../login";
		}
		if(loginUser.getUid() != 1){
			return "405";
		}
		List<LoginRecord> records = loginRecordService.selectAll();
		model.addAttribute("records", records);
		return "showRecord";
	}
	
	@RequestMapping("/showMyRecord")
	public String showRecordByUid(HttpSession session, @PathVariable("userid") Integer uid, ModelMap model){
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "redirect:../login";
		}
		if(loginUser.getUid() != 1){
			return "405";
		}
		List<LoginRecord> records = loginRecordService.selectByUid(uid);
		model.addAttribute("records", records);
		return "showRecord";
	}
	
	@RequestMapping("/showRecord/{username}")
	public String showRecordByUName(HttpSession session, @PathVariable("username") String uname, ModelMap model){
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "redirect:../login";
		}
		if(loginUser.getUid() != 1){
			return "405";
		}
		List<LoginRecord> records = loginRecordService.selectByUname(uname);
		model.addAttribute("records", records);
		return "showRecord";
	}
	
	@RequestMapping(value="/del.do", method=RequestMethod.POST)
	public String delRecords(HttpSession session, @RequestParam("recordids") Integer[] recordids, ModelMap model){
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "redirect:../login";
		}
		if(loginUser.getUid() != 1){
			return "405";
		}
		boolean flag = loginRecordService.deleteMoreByPrimaryKey(recordids) > 0;
		if(flag){
			return "redirect:showRecord";
		}
		return "500";
	}
}
