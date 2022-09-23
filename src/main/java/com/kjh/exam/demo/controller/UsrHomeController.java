package com.kjh.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	private int count = 0;
	
	@RequestMapping("/usr/home/getCount")
	@ResponseBody
	public int getCount() {		
		return count;
	}
	
	@RequestMapping("/usr/home/doSetCount")
	@ResponseBody
	public String doSetCount(int count) {
		this.count = count;
		return "Count의 값이"+ count +"로 초기화 됬습니다.";
	}
}
