package com.shopping.study.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.study.dto.UsersFormDto;
import com.shopping.study.entity.Users;
import com.shopping.study.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersController {
	
	private final UsersService usersService;
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping(value="/new")
	public String userForm(Model model) {
		model.addAttribute("userFormDto", new UsersFormDto());
		return "user/userForm";
	}
	
	
	@PostMapping(value="/new")
	public String userForm(@Valid UsersFormDto usersFormDto, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "user/userForm";
		}
		
		try {
			Users users = Users.createUser(usersFormDto, passwordEncoder);
			usersService.saveUser(users);
			System.out.println("@@@@@ 입력한거 저장되는지확인하기" + users);
		}catch(IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "user/userForm";
		}
		return "redirect:/";
	}
	
	
	
	@GetMapping("/login")
	public String loginUser() {
		return "user/userLoginForm";
	}
	
	@GetMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
		return "/user/userLoginForm";
	}
	
}
