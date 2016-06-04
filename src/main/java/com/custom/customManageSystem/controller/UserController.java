package com.custom.customManageSystem.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
import com.custom.customManageSystem.model.User;
import com.custom.customManageSystem.service.IUserService;
import com.custom.customManageSystem.util.EncodeUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	
	@RequestMapping(value="/showUser")
	public String showUsers(HttpSession session, ModelMap model){
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "redirect:../login";
		}
		if(loginUser.getUid() != 1){
			return "redirect:../405";
		}
		List<User> users = this.userService.getUsers();
		model.put("users", users);
		return "showUser";
	}
	
	@RequestMapping(value="/getUser.do")
	@ResponseBody
	public String getUser(HttpSession session, @RequestParam("userid") Integer uid, ModelMap model){
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "redirect:../login";
		}
		if(loginUser.getUid() != 1){
			return "redirect:../405";
		}
		User user = this.userService.getUserById(uid);
		user.setUpwd("");
		return JSON.toJSONString(user);
	}
	
	@RequestMapping(value="/checkUser.do", method=RequestMethod.POST)
	@ResponseBody
	public String checkUser(HttpSession session, @RequestParam("username")String uname, ModelMap model){
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "nologin";
		}
		if(loginUser.getUid() != 1){
			return "405";
		}
		User user = this.userService.getUserByName(uname);
		if(user != null)
			return "true";
		return "false";
	}
	
	@RequestMapping(value="/addUser")
	public String toAddUser(HttpSession session){
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "redirect:../login";
		}
		if(loginUser.getUid() != 1){
			return "redirect:../405";
		}
		return "addUser";
	}
	
	@RequestMapping(value="/addUser.do", method=RequestMethod.POST)
	public String doAddUser(HttpSession session, @RequestParam("username")String uname, @RequestParam("upwd")String upwd, @RequestParam("role")int role, ModelMap model){
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "redirect:../login";
		}
		if(loginUser.getUid() != 1){
			return "redirect:../405";
		}
		try {
			upwd = EncodeUtils.encryptMD5(upwd.getBytes("utf-8"));
			User user = new User(uname, upwd, role);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			user.setLasttime(sdf.format(new Date()));
			boolean flag = this.userService.addUser(user);
			if(flag)
				return "redirect:showUser";
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
		return "500";
	}
	
	@RequestMapping(value="/updateUser.do", method=RequestMethod.POST)
	public String doUpdateUser(HttpSession session, @RequestParam("userid")int uid, @RequestParam("username")String uname, @RequestParam("userpwd")String upwd, @RequestParam("role")int role, ModelMap model){
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "redirect:../login";
		}
		if(loginUser.getUid() != 1){
			return "redirect:../405";
		}
		try{
			User user = new User();
			user.setUid(uid);
			if("".equals(uname) ||  uname == null){
				user.setUname(null);
			}else {
				user.setUname(uname);
			}
			
			if("".equals(upwd) || upwd == null){
				user.setUpwd(null);
			}else {
				user.setUpwd(EncodeUtils.encryptMD5(upwd.getBytes("utf-8")));
			}
			user.setRole(role);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			user.setLasttime(sdf.format(new Date()));
			boolean flag = userService.updateUser(user);
			if(flag){
				return "redirect:showUser";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "500";
		}
		return "500";
	}
	
	@RequestMapping(value="/delUser.do", method=RequestMethod.POST)
	public String delUser(HttpSession session, @RequestParam("selectuid")Integer[] uids, ModelMap model){
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "redirect:../login";
		}
		if(loginUser.getUid() != 1){
			return "redirect:../405";
		}
		boolean flag = userService.delUsers(uids);
		if(flag){
			return "redirect:showUser";
		}
		return "500";
	}
	
	@RequestMapping("/changePwd")
	public String goChangePwd(HttpSession session, HttpServletResponse response){
		User loginuser = (User) session.getAttribute("loginuser");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(loginuser == null){
				out.println("<script>alert('你没有登录或登录已过期，请重新登录！');location='../login'</script>");
				return null;
			}
			return "changePwd";
		} catch (Exception e) {
			return "redirect:500";
		}
	}
	
	@RequestMapping(value="/changePwd.do", method=RequestMethod.POST)
	@ResponseBody
	public String changePwd(HttpSession session, @RequestParam("username") String uname, @RequestParam("oldpwd") String oldpwd, @RequestParam("newpwd") String newpwd, ModelMap model){
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "nologin";
		}
		try {
			User user = new User();
			user.setUname(uname);
			user.setUpwd(EncodeUtils.encryptMD5(oldpwd.getBytes("utf-8")));
			User checkUser = userService.login(user);
			if(checkUser == null) {
				return "false";
			} else {
				User newUser = new User();
				newUser.setUid(checkUser.getUid());
				newUser.setUpwd(EncodeUtils.encryptMD5(newpwd.getBytes("utf-8")));
				boolean flag = userService.updateUser(newUser);
				if(flag){
					session.removeAttribute("loginuser");
					return "true";
				}
				return "500";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}
}
