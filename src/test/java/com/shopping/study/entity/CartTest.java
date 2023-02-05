package com.shopping.study.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.study.dto.UsersFormDto;
import com.shopping.study.repository.CartRepository;
import com.shopping.study.repository.UsersRepository;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class CartTest {
 
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	EntityManager em;
	
	
	public Users createUsers() {
		UsersFormDto usersFormDto = new UsersFormDto();
		
		usersFormDto.setEmail("angela@naver.com");
		usersFormDto.setName("안젤");
		usersFormDto.setAddress("대한민국");
		usersFormDto.setPassword("1234");
		return Users.createUser(usersFormDto, passwordEncoder);
	}
	
	@Test
	@DisplayName("장바구니 회원 엔티티 매핑 테스트 ")
	public void findCartAndUsersTest() {
		Users users = createUsers();
		usersRepository.save(users);
		
		Cart cart = new Cart();
		cart.setUsers(users);
		cartRepository.save(cart);
		
		em.flush();
		em.clear();
		
		
		Cart savedCart = cartRepository.findById(cart.getId()).orElseThrow(EntityNotFoundException::new);
		assertEquals(savedCart.getUsers().getId(), users.getId());

	}
}
