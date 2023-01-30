package com.shopping.study.service;


import com.shopping.study.dto.UsersFormDto;
import com.shopping.study.entity.Users;

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
class UsersServiceTest {

	@Autowired
	UsersService usersService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Users createUser() {
		UsersFormDto usersFormDto = new UsersFormDto();
		usersFormDto.setName("정수진");
		usersFormDto.setEmail("test@naver.com");
		usersFormDto.setAddress("부산광역시 수영구 ");
		usersFormDto.setPassword("1234");
		return Users.createUser(usersFormDto, passwordEncoder);
	}
	
	
	@Test
	@DisplayName("회원가입 테스트")
	public void saveUserTest() {
		Users users = createUser();
		Users savedUser = usersService.saveUser(users);
	
		assertEquals(users.getName(), savedUser.getName());
		assertEquals(users.getEmail(), savedUser.getEmail());
		assertEquals(users.getAddress(), savedUser.getAddress());
		assertEquals(users.getPassword(), savedUser.getPassword());
		assertEquals(users.getRole(), savedUser.getRole());
		System.out.println("@@@@" + savedUser);
		
	}
	
	
	
}
