package com.shopping.study.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shopping.study.entity.Member;
import com.shopping.study.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService  {

	private final MemberRepository memberRepository;
	
	public Member saveUser(Member member) {
		validateDuplicateUser(member);
		return memberRepository.save(member);
	}
	
	//이미 가입된 회원일 경우 예외처리
	private void validateDuplicateUser(Member member) {
		Member findUser = memberRepository.findByEmail(member.getEmail());
		
		if(findUser != null) {
			
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}	
	}
	
}
