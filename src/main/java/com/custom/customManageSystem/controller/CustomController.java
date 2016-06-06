package com.custom.customManageSystem.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.custom.customManageSystem.entity.Page;
import com.custom.customManageSystem.model.Custom;
import com.custom.customManageSystem.model.User;
import com.custom.customManageSystem.service.ICustomService;
import com.custom.customManageSystem.util.ExcelUtils;

@Controller
@RequestMapping("custom")
public class CustomController {

	@Resource
	private ICustomService customService;
	
	@RequestMapping("/showCustom")
	public String showCustom(@ModelAttribute Page page, HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, ModelMap model, 
			@RequestParam(value="carCode", defaultValue="") String licensePlates, 
			@RequestParam(value="carFrameCode", defaultValue="") String carFrameCode){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			User user = (User) session.getAttribute("loginuser");
			if(user == null){
				out.println("<script>alert('你还没有登录或登录已过期，请登录！');location='../login';</script>");
				return null;
			}
			if("".equals(licensePlates) && "".equals(carFrameCode)){
				licensePlates = (String) session.getAttribute("carCode");
				carFrameCode = (String) session.getAttribute("carFrameCode");
				session.removeAttribute("carCode");
				session.removeAttribute("carFrameCode");
			}
			Custom custom = new Custom();
			custom.setLicenseplates(licensePlates);
			custom.setCarframecode(carFrameCode);
			int totalCount = customService.selectCountByCondition(custom, null, null);
			page.setTotalCount(totalCount);
			page.count();
			List<Custom> customs = customService.selectByConditionPage(custom, page, null, null);
			if(user.getRole() == 2){
				String phone = "";
				StringBuffer phoneBuffer = null;
				String[] phones = null;
				for(Custom c:customs){
					phoneBuffer = new StringBuffer();
					phone = c.getPhonenum();
					phones = phone.split(",");
					for(String p:phones){
						phoneBuffer.append(p.substring(0, 3)).append("****").append(p.substring(p.length() - 5, p.length() - 1)).append(",");
					}
					phoneBuffer.deleteCharAt(phoneBuffer.length()-1);
					c.setPhonenum(phoneBuffer.toString());
				}
			}
			
			model.addAttribute("carCode", licensePlates);
			model.addAttribute("carFrameCode", carFrameCode);
			model.addAttribute("customs", customs);
			model.addAttribute("page", page);
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:../500";
		}
		return "showCustom";
	}
	
	@RequestMapping("/addCustom")
	public String addCustom(HttpServletResponse response, HttpSession session, ModelMap model){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			User user = (User) session.getAttribute("loginuser");
			if(user == null){
				out.println("<script>alert('你还没有登录或登录已过期，请登录！');location='../login';</script>");
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:../500";
		}
		return "addCustom";
	}
	
	@RequestMapping(value="/checkAdd", method=RequestMethod.POST)
	@ResponseBody
	public String checkAdd(HttpSession session, @RequestParam("carCode") String carCode, @RequestParam("carFrameCode") String carFrameCode){
		try {
			User user = (User) session.getAttribute("loginuser");
			if(user == null){
				return "nologin";
			}
			List<Custom> queryCustom = new ArrayList<Custom>();
			if("".equals(carCode)){
				if("".equals(carFrameCode)){
					return "nocodeframe";
				}
				queryCustom = customService.checkSingle(null, carFrameCode);
				if(queryCustom.size() > 0){
					return "true|" + "carFrameCode|" + queryCustom.get(0).getCustomid();
				}
			}else {
				queryCustom = customService.checkSingle(carCode, null);
				if(queryCustom.size() > 0){
					return "true|" + "carCode|" + queryCustom.get(0).getCustomid();
				}
				if(queryCustom.size() == 0 && !"".equals(carFrameCode)){
					queryCustom = customService.checkSingle(null, carFrameCode);
					if(queryCustom.size() > 0){
						return "true|" + "carFrameCode|" + queryCustom.get(0).getCustomid();
					}
				}
			}
			return "false";
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}
	
	@RequestMapping("/addCustom.do")
	@ResponseBody
	public String doAddCustom(HttpServletRequest request, HttpSession session, @ModelAttribute() Custom custom, ModelMap model){
		try {
			User user = (User) session.getAttribute("loginuser");
			if(user == null){
				return "nologin";
			}
			String firstTime = request.getParameter("firstTime");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			if("".equals(firstTime)){
				custom.setFirsttime(null);
			}else {
				custom.setFirsttime(formatStr2Date(firstTime));
			}
			if("".equals(startTime)){
				custom.setStarttime(null);
			}else {
				custom.setStarttime(formatStr2Date(startTime));
			}
			if("".equals(endTime)){
				custom.setEndtime(null);
			}else {
				custom.setEndtime(formatStr2Date(endTime));
			}
			String three = request.getParameter("three");
			String glass = request.getParameter("glass");
			String nick = request.getParameter("nick");
			String robbery = request.getParameter("robbery");
			String cardamage = request.getParameter("cardamage");
			String auto = request.getParameter("autoignition");
			String wading = request.getParameter("wading");
			if(robbery == null){
				custom.setRobbery("");
			}
			if(cardamage == null){
				custom.setCardamage("");
			}
			if(auto == null){
				custom.setAutoignition("");
			}
			if(wading == null){
				custom.setWading("");
			}
			if("0".equals(three)){
				custom.setThree20("");
				custom.setThree30("");
				custom.setThree50("");
				custom.setThree100("");
				custom.setThree150("");
			} else if("20".equals(three)){
				custom.setThree20("√");
				custom.setThree30("");
				custom.setThree50("");
				custom.setThree100("");
				custom.setThree150("");
			} else if("30".equals(three)){
				custom.setThree20("");
				custom.setThree30("√");
				custom.setThree50("");
				custom.setThree100("");
				custom.setThree150("");
			} else if("50".equals(three)){
				custom.setThree20("");
				custom.setThree30("");
				custom.setThree50("√");
				custom.setThree100("");
				custom.setThree150("");
			} else if("100".equals(three)){
				custom.setThree20("");
				custom.setThree30("");
				custom.setThree50("");
				custom.setThree100("√");
				custom.setThree150("");
			} else if("150".equals(three)){
				custom.setThree20("");
				custom.setThree30("");
				custom.setThree50("");
				custom.setThree100("");
				custom.setThree150("√");
			}
			if("none".equals(glass)){
				custom.setForeignglass("");
				custom.setDomesticglass("");
			}else if("foreign".equals(glass)){
					custom.setForeignglass("√");
					custom.setDomesticglass("");
			} else if("domestic".equals(glass)){
				custom.setForeignglass("");
				custom.setDomesticglass("√");
			}
			if("0".equals(nick)){
				custom.setNick2("");
				custom.setNick5("");
				custom.setNick10("");
				custom.setNick15("");
			} else if("2000".equals(nick)){
				custom.setNick2("√");
				custom.setNick5("");
				custom.setNick10("");
				custom.setNick15("");
			} else if("5000".equals(nick)){
				custom.setNick2("");
				custom.setNick5("√");
				custom.setNick10("");
				custom.setNick15("");
			} else if("10000".equals(nick)){
				custom.setNick2("");
				custom.setNick5("");
				custom.setNick10("√");
				custom.setNick15("");
			} else if("15000".equals(nick)){
				custom.setNick2("");
				custom.setNick5("");
				custom.setNick10("");
				custom.setNick15("√");
			}
			boolean flag = customService.insert(custom) > 0;
			if(flag){
				return "true";
			}else {
				return "false";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:../500";
		}
	}
	
	@RequestMapping("/input")
	public String inPutCustom(HttpServletResponse response, HttpSession session, ModelMap model){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			User user = (User) session.getAttribute("loginuser");
			if(user == null){
				out.println("<script>alert('你还没有登录或登录已过期，请登录！');location='../login';</script>");
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:../500";
		}
		return "input";
	}
	
//	@RequestMapping(value="/search.do", method=RequestMethod.POST)
//	public String searchCustom(HttpSession session, HttpServletResponse response, @RequestParam("carCode") String licensePlates, @RequestParam("carFrameCode") String carFrameCode, ModelMap model){
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = null;
//		try {
//			out = response.getWriter();
//			User user = (User) session.getAttribute("loginuser");
//			if(user == null){
//				out.println("<script>alert('你还没有登录或登录已过期，请登录！');location='../login';</script>");
//				return null;
//			}
//			if("".equals(licensePlates)){
//				licensePlates = null;
//			}
//			if("".equals(carFrameCode)){
//				carFrameCode = null;
//			}
//			Custom custom = new Custom();
//			custom.setLicenseplates(licensePlates);
//			custom.setCarframecode(carFrameCode);
//			List<Custom> customs = customService.selectByCondition(custom, null, null);
//			model.addAttribute("carCode", licensePlates);
//			model.addAttribute("carFrameCode", carFrameCode);
//			model.addAttribute("customs", customs);
//			return "showCustom";
//		}catch(Exception e){
//			e.printStackTrace();
//			return "redirect:../500";
//		}
//	}
	
//	@RequestMapping(value="/searchOut.do", method=RequestMethod.POST)
//	public String searchCustomOut(HttpSession session, HttpServletResponse response, @RequestParam("time1") String time1, @RequestParam("time2") String time2, ModelMap model){
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = null;
//		try {
//			out = response.getWriter();
//			User user = (User) session.getAttribute("loginuser");
//			if(user == null){
//				out.println("<script>alert('你还没有登录或登录已过期，请登录！');location='../login';</script>");
//				return null;
//			}
//			if(user.getRole() == 2){
//				return "redirect:405";
//			}
//			Custom custom = new Custom();
//			List<Custom> customs = customService.selectByCondition(custom, time1, time2);
//			model.addAttribute("time1", time1);
//			model.addAttribute("time2", time2);
//			model.addAttribute("customs", customs);
//			return "outCustom";
//		}catch(Exception e){
//			e.printStackTrace();
//			return "redirect:../500";
//		}
//	}
	
	@RequestMapping(value="/delCustom.do")
	public String delCustom(HttpServletRequest request, HttpSession session, HttpServletResponse response, @RequestParam("customids") Integer[] customids, ModelMap model, RedirectAttributes attrs){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			User user = (User) session.getAttribute("loginuser");
			if(user == null){
				out.println("<script>alert('你还没有登录或登录已过期，请登录！');location='../login';</script>");
				return null;
			}
			String carCode = request.getParameter("carCode");
			String carFrameCode = request.getParameter("carFrameCode");
			boolean flag = customService.deleteMoreByPrimaryKey(customids) > 0;
			if(flag){
				session.setAttribute("carCode", carCode);
				session.setAttribute("carFrameCode",carFrameCode);
				return "redirect:showCustom";
			}
			out = response.getWriter();
			out.println("<script>history.go(-1);</script>");
			return null;
		} catch (Exception e) {
			return "redirect:../500";
		}
	}
	
	@RequestMapping(value="/getCustom.do", method=RequestMethod.POST)
	@ResponseBody
	public String getCustom(HttpSession session, HttpServletResponse response, @RequestParam("customid") Integer customid, ModelMap model){
		User user = (User) session.getAttribute("loginuser");
		if(user == null){
			return "nologin";
		}
		Custom custom = customService.selectByPrimaryKey(customid);
		if(custom == null){
			return "none";
		}
		if(user.getRole() == 2){
			String phone = custom.getPhonenum();
			StringBuffer phoneBuffer = new StringBuffer();
			String[] phones = phone.split(",");
			for(String p:phones){
				phoneBuffer.append(p.substring(0, 3)).append("****").append(p.substring(p.length() - 5, p.length() - 1)).append(",");
			}
			phoneBuffer.deleteCharAt(phoneBuffer.length()-1);
			custom.setPhonenum(phoneBuffer.toString());
		}
		return JSON.toJSONString(custom);
	}
	
	@RequestMapping(value="/outCustom")
	public String outPut(@ModelAttribute Page page, HttpSession session, HttpServletResponse response, ModelMap model,
			@RequestParam(value="year",defaultValue="") String year,
			@RequestParam(value="month",defaultValue="") String month
			){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			User user = (User) session.getAttribute("loginuser");
			if(user == null){
				out.println("<script>alert('你还没有登录或登录已过期，请登录！');location='../login';</script>");
				return null;
			}
			if(user.getRole() == 2){
				return "redirect:../405";
			}
			
			int totalCount = customService.selectCountByDate(year, month);
			page.setTotalCount(totalCount);
			page.count();
			List<Custom> customs = customService.selectByDatePage(page, year, month);
			model.addAttribute("customs", customs);
			model.addAttribute("page", page);
			model.addAttribute("year",year);
			model.addAttribute("month", month);
			
			return "outCustom";
		} catch (Exception e) {
			return "redirect:../500";
		}
	}
	
	@RequestMapping(value="/output.do", method=RequestMethod.POST)
	public String outPut(HttpSession session, HttpServletResponse response, @RequestParam("customids") Integer[] customids, ModelMap model,
			@RequestParam(value="year",defaultValue="") String year,
			@RequestParam(value="month",defaultValue="") String month){
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			User user = (User) session.getAttribute("loginuser");
			if(user == null){
				out.println("<script>alert('你还没有登录或登录已过期，请登录！');location='../login';</script>");
				return null;
			}
			if(user.getRole() == 2){
				return "redirect:../405";
			}
			List<Custom> customs = customService.outPut(customids);
			String path = session.getServletContext().getRealPath("file/");
			String filename = ExcelUtils.writeExcel(customs, path);
			
			return "redirect:../file/download/" + filename;
		} catch (Exception e) {
			return "redirect:../500";
		}
	}
	
	@RequestMapping(value="/outputAll.do", method=RequestMethod.POST)
	public String outPutAll(HttpSession session, HttpServletResponse response, ModelMap model,
			@RequestParam(value="year",defaultValue="") String year,
			@RequestParam(value="month",defaultValue="") String month){
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			User user = (User) session.getAttribute("loginuser");
			if(user == null){
				out.println("<script>alert('你还没有登录或登录已过期，请登录！');location='../login';</script>");
				return null;
			}
			if(user.getRole() == 2){
				return "redirect:../405";
			}
			List<Custom> customs = customService.selectByDate(year, month);
			String path = session.getServletContext().getRealPath("file/");
			String filename = ExcelUtils.writeExcel(customs, path);
			return "redirect:../file/download/" + filename;
		} catch (Exception e) {
			return "redirect:../500";
		}
	}
	
	@RequestMapping("/updateCustom.do")
	@ResponseBody
	public String goUpdate(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Custom custom, HttpSession session, ModelMap model){
		try {
			User user = (User) session.getAttribute("loginuser");
			if(user == null){
				return "nologin";
			}
			String firstTime = request.getParameter("firstTime");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			if("".equals(firstTime)){
				custom.setFirsttime(null);
			}else {
				custom.setFirsttime(formatStr2Date(firstTime));
			}
			if("".equals(startTime)){
				custom.setStarttime(null);
			}else {
				custom.setStarttime(formatStr2Date(startTime));
			}
			if("".equals(endTime)){
				custom.setEndtime(null);
			}else {
				custom.setEndtime(formatStr2Date(endTime));
			}
			String three = request.getParameter("three");
			String glass = request.getParameter("glass");
			String nick = request.getParameter("nick");
			String robbery = request.getParameter("robbery");
			String cardamage = request.getParameter("cardamage");
			String auto = request.getParameter("autoignition");
			String wading = request.getParameter("wading");
			if(robbery == null){
				custom.setRobbery("");
			}
			if(cardamage == null){
				custom.setCardamage("");
			}
			if(auto == null){
				custom.setAutoignition("");
			}
			if(wading == null){
				custom.setWading("");
			}
			if("0".equals(three)){
				custom.setThree20("");
				custom.setThree30("");
				custom.setThree50("");
				custom.setThree100("");
				custom.setThree150("");
			} else if("20".equals(three)){
				custom.setThree20("√");
				custom.setThree30("");
				custom.setThree50("");
				custom.setThree100("");
				custom.setThree150("");
			} else if("30".equals(three)){
				custom.setThree20("");
				custom.setThree30("√");
				custom.setThree50("");
				custom.setThree100("");
				custom.setThree150("");
			} else if("50".equals(three)){
				custom.setThree20("");
				custom.setThree30("");
				custom.setThree50("√");
				custom.setThree100("");
				custom.setThree150("");
			} else if("100".equals(three)){
				custom.setThree20("");
				custom.setThree30("");
				custom.setThree50("");
				custom.setThree100("√");
				custom.setThree150("");
			} else if("150".equals(three)){
				custom.setThree20("");
				custom.setThree30("");
				custom.setThree50("");
				custom.setThree100("");
				custom.setThree150("√");
			}
			if("none".equals(glass)){
				custom.setForeignglass("");
				custom.setDomesticglass("");
			}else if("foreign".equals(glass)){
					custom.setForeignglass("√");
					custom.setDomesticglass("");
			} else if("domestic".equals(glass)){
				custom.setForeignglass("");
				custom.setDomesticglass("√");
			}
			if("0".equals(nick)){
				custom.setNick2("");
				custom.setNick5("");
				custom.setNick10("");
				custom.setNick15("");
			} else if("2000".equals(nick)){
				custom.setNick2("√");
				custom.setNick5("");
				custom.setNick10("");
				custom.setNick15("");
			} else if("5000".equals(nick)){
				custom.setNick2("");
				custom.setNick5("√");
				custom.setNick10("");
				custom.setNick15("");
			} else if("10000".equals(nick)){
				custom.setNick2("");
				custom.setNick5("");
				custom.setNick10("√");
				custom.setNick15("");
			} else if("15000".equals(nick)){
				custom.setNick2("");
				custom.setNick5("");
				custom.setNick10("");
				custom.setNick15("√");
			}
			if(custom.getPhonenum().contains("*")){
				custom.setPhonenum(null);
			}
			boolean flag = customService.updateByPrimaryKeySelective(custom) > 0 ;
			if(flag){
				return "true";
			}else {
				return "500";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return "500";
		}
	}
	
	public Date formatStr2Date(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
}
