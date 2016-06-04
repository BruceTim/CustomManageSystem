package com.custom.customManageSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping("/404")
	public String go404(){
		return "404";
	}
	
	@RequestMapping("/405")
	public String go405(){
		return "405";
	}
	
	@RequestMapping("/500")
	public String go500(){
		return "500";
	}
}
