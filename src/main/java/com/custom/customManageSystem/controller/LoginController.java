package com.custom.customManageSystem.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.custom.customManageSystem.model.LoginRecord;
import com.custom.customManageSystem.model.User;
import com.custom.customManageSystem.service.ILoginRecordService;
import com.custom.customManageSystem.service.IUserService;
import com.custom.customManageSystem.util.EncodeUtils;

@Controller
@SessionAttributes(value="loginuser", types=User.class)
public class LoginController {
	@Resource
	private IUserService userService;
	@Resource
	private ILoginRecordService loginRecordService;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,ModelMap model){
		return "login";
	}
	
	@RequestMapping("/logout")
	public String login(HttpSession session){
		session.removeAttribute("loginuser");
		return "redirect:login";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String doLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam("username")String username,@RequestParam("userpwd")String userpwd,ModelMap model){
		User user = null;
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			user = this.userService.login(new User(username, EncodeUtils.encryptMD5(userpwd.getBytes("utf-8"))));
			if(user != null){
				LoginRecord record = new LoginRecord();
				record.setLogintime(new Date());
				String ip = request.getRemoteAddr();
				record.setIpaddress(ip);
				record.setUid(user.getUid());
				record.setUname(user.getUname());
				record.setUrole(user.getRole());
				boolean flag = loginRecordService.insert(record) > 0;
				user.setUpwd(userpwd);
				model.addAttribute("loginuser", user);
				return "redirect:custom/showCustom";
			}else {
				out = response.getWriter();
				out.println("<script>alert('用户名或密码错误，登录失败！');history.go(-1);</script>");
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}
}
