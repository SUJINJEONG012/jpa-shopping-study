package com.shopping.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.study.dto.MemberFormDto;
import com.shopping.study.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping(value="/new")
	public String memberForm(MemberFormDto memberFormDto, Model model ) {
		model.addAttribute("memberFormDto", memberFormDto);
		return "members/memberForm";
	}
}
