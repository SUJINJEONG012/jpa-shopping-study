package com.shopping.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.study.dto.UserFormDto;
import com.shopping.study.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
	
	private final UserService userService;
	
	@GetMapping(value="/new")
	public String memberForm(UserFormDto userFormDto, Model model ) {
		model.addAttribute("memberFormDto", userFormDto);
		return "members/memberForm";
	}
}
