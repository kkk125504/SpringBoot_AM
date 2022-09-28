package com.kjh.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjh.exam.demo.service.ArticleService;
import com.kjh.exam.demo.service.MemberService;
import com.kjh.exam.demo.vo.Article;

@Controller
public class UsrMemberController {

	@Autowired
	MemberService memberService;

	// 액션 메소드
	@RequestMapping("usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname,
			String cellphoneNum, String email) {
		memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		return nickname+"님 회원가입 했습니다.";
	}

}