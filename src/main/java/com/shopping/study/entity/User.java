package com.shopping.study.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.shopping.study.constant.Role;
import com.shopping.study.dto.UserFormDto;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	//회원은 이메일을 통해유일하게 구분해야 하기때문에 동일한 값이 데이터베이스에 들어올 수 없도록 unique속성 지정
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String address;
	
	/*
	 * 자바의 Enum타입을 엔티티의 속성을 지정할 수 있다. 
	 * Enum을 사용할 때 기본적으로 순서가 저장되는데, 
	 * enum의 순서가 바뀔 경우, 문제가 발생할 수 있으므로 "EnumType.STRING" 옵션을 사용해서 String으로 저장하기를 권장
	 * */
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	/*
	 * User엔티티를 생성하는 메서드
	 * 유저 엔티티에 회원을 생성하는 메서드를 만들어서 관리를한다면 
	 * 코드가 변경되더라도 한 군데만 수정하면 되는 이점이 있다. 
	 * */
	public static User createUser(UserFormDto userFormDto, PasswordEncoder passwordEncoder) {
		User user = new User();
		
		user.setName(userFormDto.getName());
		user.setEmail(userFormDto.getEmail());
		user.setAddress(userFormDto.getAddress());
		
		// 스프링 시큐리티 설정을 클래스에 등록한 BCryptPassword Bean을 파라미터로 넘겨서 비밀번호를 암호화
		String password = passwordEncoder.encode(userFormDto.getPassword());
		user.setPassword(password);
		user.setRole(Role.USER);
		
		return user;
	}
	
	
}
