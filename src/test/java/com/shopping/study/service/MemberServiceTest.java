package com.shopping.study.service;


import com.shopping.study.dto.MemberFormDto;
import com.shopping.study.entity.Member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;



import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class MemberServiceTest {

	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Member createUser() {
		MemberFormDto memberFormDto = new MemberFormDto();
		memberFormDto.setName("정수진");
		memberFormDto.setEmail("test@naver.com");
		memberFormDto.setAddress("부산광역시 수영구 ");
		memberFormDto.setPassword("1234");
		return Member.createUser(memberFormDto, passwordEncoder);
	}
	
	
	@Test
	@DisplayName("회원가입 테스트")
	public void saveUserTest() {
		Member member = createUser();
		Member savedUser = memberService.saveUser(member);
	
		assertEquals(member.getName(), savedUser.getName());
		assertEquals(member.getEmail(), savedUser.getEmail());
		assertEquals(member.getAddress(), savedUser.getAddress());
		assertEquals(member.getPassword(), savedUser.getPassword());
		assertEquals(member.getRole(), savedUser.getRole());
		
	}
	
}
