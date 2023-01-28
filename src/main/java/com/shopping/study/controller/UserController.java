package com.shopping.study.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.study.dto.UserFormDto;
import com.shopping.study.entity.User;
import com.shopping.study.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping(value="/new")
	public String userForm(Model model) {
		model.addAttribute("userFormDto", new UserFormDto());
		return "user/userForm";
	}
	
	
	@PostMapping(value="/new")
	public String userForm(@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "user/userForm";
		}
		
		try {
			User user = User.createUser(userFormDto, passwordEncoder);
			userService.saveUser(user);
			System.out.println("@@@@@ 입력한거 저장되는지확인하기" + user);
		}catch(IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "user/userForm";
		}
		
		return "redirect:/";
	}
}
