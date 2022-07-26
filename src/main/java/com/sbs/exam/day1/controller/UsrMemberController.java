package com.sbs.exam.day1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.day1.service.MemberService;
import com.sbs.exam.day1.util.Ut;
import com.sbs.exam.day1.vo.Article;
import com.sbs.exam.day1.vo.Member;

@Controller
public class UsrMemberController {
	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		if (Ut.empty(loginId)) {
			return "loginId(을)를 입력해주세요.";
		}
		if (Ut.empty(loginPw)) {
			return "loginPw(을)를 입력해주세요.";
		}
		if (Ut.empty(name)) {
			return "name(을)를 입력해주세요.";
		}
		if (Ut.empty(nickname)) {
			return "nickname(을)를 입력해주세요.";
		}
		if (Ut.empty(cellphoneNo)) {
			return "cellphoneNo(을)를 입력해주세요.";
		}
		if (Ut.empty(email)) {
			return "email(을)를 입력해주세요.";
		}

		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (id == -1) {
			return Ut.f("해당 로그인 아이디(%s)는 이미 사용중입니다.", loginId);
		}
		
		if (id == -2) {
			return Ut.f("해당 이름(%s)과 이메일(%s)은 이미 사용중입니다.", name, email);
		}

		Member member = memberService.getMemberById(id);
		return member;
	}
}