package com.shopping.study.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.study.entity.User;
import com.shopping.study.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	public User saveUser(User user) {
		validateDuplicateUser(user);
		return userRepository.save(user);
	}
	

	//이미 가입된 회원일 경우 예외처리
	private void validateDuplicateUser(User user) {
		User findUser = userRepository.findByEmail(user.getEmail());
		if(findUser != null) {
			throw new IllegalStateException("이미가입된 회원입니다.!");
		}
		
	}
}
