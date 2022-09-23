package com.kjh.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	@RequestMapping("/usr/main/home")
	@ResponseBody
	public String showMain() {
		return "ㅎㅇ";
	}
}
