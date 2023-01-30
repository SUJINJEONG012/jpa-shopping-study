package com.shopping.study.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.study.dto.UsersFormDto;
import com.shopping.study.entity.Users;
import com.shopping.study.service.UsersService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class UsersControllerTest {

	@Autowired
	private UsersService usersService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	PasswordEncoder passwordEncoder;

	// 회원등록
	public Users createUsers(String email, String password) {
		UsersFormDto usersFormDto = new UsersFormDto();
		usersFormDto.setEmail(email);
		usersFormDto.setName("정수진");
		usersFormDto.setAddress("부산광역시 수영구 광안동");
		usersFormDto.setPassword(password);

		Users users = Users.createUser(usersFormDto, passwordEncoder);
		return usersService.saveUser(users);
	}
	

	@Test
	@DisplayName("로그인 성공 테스트")
	public void loginSuccessTest() throws Exception {
		String email = "test@naver.com";
		String password = "1234";
		this.createUsers(email, password);
		mockMvc.perform(formLogin()
				.userParameter("email")
				.loginProcessingUrl("/user/login")
				.user(email)
				.password(password))
				.andExpect(SecurityMockMvcResultMatchers.authenticated());
		
		System.out.println("로그인 성공 테스트 :: " + email);
		System.out.println("로그인 성공 테스트 :: " +  password);

	}
	
	@Test
	@DisplayName("로그인 실패 테스트")
	public void loginFailTest() throws Exception {
		String email= "test@naver.com";
		String password = "1234";
		mockMvc.perform(formLogin().userParameter("email")
				.loginProcessingUrl("/user/login")
				.user(email).password("12345"))
		.andExpect(SecurityMockMvcResultMatchers.unauthenticated());
		
		System.out.println("로그인 실패 테스트 :"  + email);
	}
	
	
	

}
