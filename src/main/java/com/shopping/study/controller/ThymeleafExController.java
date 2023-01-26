package com.shopping.study.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.study.dto.ItemDto;

@Controller
@RequestMapping(value="/thymeleaf")
public class ThymeleafExController {
	
	@GetMapping(value="/ex01")
	public String thymeleafExample01(Model model) {
		model.addAttribute("data", "타임리프 예제입니다.");
		return "thymeleaf/thymeleaf01";
	}
	
	@GetMapping(value="/ex02")
	public String thymeleafExample02(Model model) {
		ItemDto itemDto = new ItemDto();
		itemDto.setItemNm("테스트 상품 1");
		itemDto.setItemDetail("상품 상세 설명");
		itemDto.setPrice(10000);
		itemDto.setRegTime(LocalDateTime.now());
		
		model.addAttribute("itemDto", itemDto);
		return "thymeleaf/thymeleaf02";
	}

}