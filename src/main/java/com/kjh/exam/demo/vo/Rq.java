package com.kjh.exam.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kjh.exam.demo.util.Ut;

import lombok.Getter;

public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		this.session = req.getSession();
		if (session.getAttribute("loginedMemberId") != null) {
			this.isLogined = true;
			this.loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
	}

	public void printHistoryBackJs(String msg) {
		resp.setContentType("text/html; charset=UTF-8");
		println("<script>");
		if (msg.length() != 0) {
			print("alert('" + msg + "');");
		}
		println("history.back();");
		println("</script>"); 
	}

	public void print(String msg) {
		try {
			resp.getWriter().append(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void println(String str) {
		print(str + "\n");
	}

	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
		session.setAttribute("loginedMemberLoginId", member.getLoginId());		
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
		session.removeAttribute("loginedMemberLoginId");		
	}

}
