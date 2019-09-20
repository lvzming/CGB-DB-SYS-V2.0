package com.tedu.cgb.team.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
	
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	
	@RequestMapping("{module}/{moduleUI}")
	public String doModuleUI(@PathVariable String moduleUI) {
		return "sys/" + moduleUI;
	}
	
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "starter";
	}
	
	@RequestMapping("doLoginUI")
	public String doLoginUI() {
		return "login";
	}
	@RequestMapping("home")
	public String home() {
		return "shop/index";
	}
}
