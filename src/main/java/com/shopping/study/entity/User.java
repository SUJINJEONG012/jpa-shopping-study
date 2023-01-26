package com.shopping.study.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shopping.study.constant.Role;

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
	private String Email;
	
	private String password;
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}
