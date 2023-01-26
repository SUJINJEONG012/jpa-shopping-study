package com.shopping.study.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
	
	@GetMapping(value="/ex03")
	public String thymeleafExample03(Model model) {
		List<ItemDto> itemDtoList = new ArrayList<>();
		
		//반복문을 통해 화며에서출력할 10개의 itemDto객체를 만들어서 itemDtoList에 넣어준다.
		for(int i =1; i <=10; i++) {
			ItemDto itemDto = new ItemDto();
			itemDto.setItemNm("테스트상품" + i);
			itemDto.setItemDetail("테스트 상품 상세설명"+ i);
			itemDto.setPrice(10000*i);
			itemDto.setRegTime(LocalDateTime.now());
			itemDtoList.add(itemDto);
		}
		model.addAttribute("itemDtoList", itemDtoList);	//화면에서 출력할 itemDtoList를 model에 담아서 View에 전딜
		return "thymeleaf/thymeleaf03";
	}
	
	
	@GetMapping(value="/ex04")
	public String thymeleafExample04(Model model) {
		List<ItemDto> itemDtoList = new ArrayList<>();
		
		for(int i = 1; i<=10; i++) {
		ItemDto itemDto = new ItemDto();
		
		itemDto.setItemNm("상품명" + i);
		itemDto.setItemDetail("상품상세설명" + i);
		itemDto.setPrice(10000 * i);
		itemDto.setRegTime(LocalDateTime.now());
		itemDtoList.add(itemDto);
		}
		
		model.addAttribute("itemDtoList" , itemDtoList);
		return "thymeleaf/thymeleaf04";
	}
	
	@GetMapping(value="/ex05")
	public String thymeleafExample05() {
		return "thymeleaf/thymeleaf05";
	}
	
	@GetMapping(value="/ex06")
	public String thymeleafExample06(String param1, String param2, Model model) {
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		return "thymeleaf/thymeleaf06";
	}
	
	@GetMapping(value="/ex07")
	public String thymeleafExample07() {
		return "thymeleaf/thymeleaf07";
	}

}
