package com.custom.customManageSystem.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.custom.customManageSystem.entity.Page;
import com.custom.customManageSystem.model.LoginRecord;
import com.custom.customManageSystem.model.User;
import com.custom.customManageSystem.service.ILoginRecordService;

@Controller
@RequestMapping("/record")
public class LoginRecordController {

	@Resource
	private ILoginRecordService loginRecordService;
	
	@RequestMapping("/showRecord")
	public String showRecord(@ModelAttribute("page") Page page,  @RequestParam(value="username", defaultValue="") String uname, HttpSession session, ModelMap model){
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "redirect:../login";
		}
		if(loginUser.getUid() != 1){
			return "405";
		}
		if("".equals(uname)){
			uname = (String) session.getAttribute("username");
			session.removeAttribute("username");
		}
		int totalCount = loginRecordService.selectCountByUname(uname);
		page.setTotalCount(totalCount);
		page.count();
		List<LoginRecord> records = loginRecordService.selectByUnamePage(page, uname);
		model.addAttribute("records", records);
		model.addAttribute("page", page);
		model.addAttribute("username", uname);
		return "showRecord";
	}
	
	@RequestMapping(value="/delRecord.do", method=RequestMethod.POST)
	public String delRecords(HttpSession session, @RequestParam("username") String uname, @RequestParam("recordids") Integer[] recordids, ModelMap model){
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "redirect:../login";
		}
		if(loginUser.getUid() != 1){
			return "405";
		}
		boolean flag = loginRecordService.deleteMoreByPrimaryKey(recordids) > 0;
		if(flag){
			session.setAttribute("username", uname);
			return "redirect:showRecord";
		}
		return "500";
	}
}
