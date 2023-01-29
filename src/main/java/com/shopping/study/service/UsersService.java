package com.shopping.study.service;

import java.sql.Timestamp;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.shopping.study.entity.Users;
import com.shopping.study.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

	private final UsersRepository usersRepository;
	
	public Users saveUser(Users users) {
		validateDuplicateUser(users);
		return usersRepository.save(users);
	}
	
	//이미 가입된 회원일 경우 예외처리
	private void validateDuplicateUser(Users users) {
		Users findUser = usersRepository.findByEmail(users.getEmail());
		
		if(findUser != null) {
			
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}	
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		
		Users users = usersRepository.findByEmail(email);
		
		if(users == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return User.builder()
                .username(users.getEmail())
                .password(users.getPassword())
                .roles(users.getRole().toString())
                .build();
		
	}
	
	
	
	
	
}
