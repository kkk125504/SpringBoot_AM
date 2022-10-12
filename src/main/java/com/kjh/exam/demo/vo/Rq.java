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

	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		HttpSession httpSession = req.getSession();
		if (httpSession.getAttribute("loginedMemberId") != null) {
			this.isLogined = true;
			this.loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void println(String str) {
		print(str + "\n");
	}

}
