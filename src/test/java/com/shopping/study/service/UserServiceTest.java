package com.shopping.study.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.study.dto.UserFormDto;
import com.shopping.study.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public User createUser() {
		UserFormDto userFormDto = new UserFormDto();
		userFormDto.setName("정수진");
		userFormDto.setEmail("test@naver.com");
		userFormDto.setAddress("부산광역시 수영구 ");
		userFormDto.setPassword("1234");
		return User.createUser(userFormDto, passwordEncoder);
		
	}
	
	
	@Test
	@DisplayName("회원가입 테스트")
	public void saveUserTest() {
		User user = createUser();
		User savedUser = userService.saveUser(user);
		
		assertEquals(user.getName(), savedUser.getName());
		assertEquals(user.getEmail(), savedUser.getEmail());
		assertEquals(user.getAddress(), savedUser.getAddress());
		assertEquals(user.getPassword(), savedUser.getPassword());
		assertEquals(user.getRole(), savedUser.getRole());
		
	}
	
	
	
	
}
