package com.shopping.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import com.shopping.study.service.UsersService;

@Configuration // 빈 등록(Ioc관리)
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	UsersService usersService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.formLogin()
				.loginPage("/user/login") //로그인페이지 URL
				.defaultSuccessUrl("/") //로그인 성공시 이동할 페이지
				.usernameParameter("email") // 로그인시 사용할 파라미터 이름 설정
				.failureUrl("/user/login/error") // 로그인 실패시 이동할 URL
				.and()  
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))//로그아웃 URL
				.logoutSuccessUrl("/"); //로그아웃 성공시 URL

		
		http.authorizeRequests()
        		.mvcMatchers("/css/**", "/js/**", "/images/**").permitAll()
        		.mvcMatchers("/", "/user/**", "/item/**", "/images/**").permitAll()
        		.mvcMatchers("/admin/**").hasRole("ADMIN")
        		.anyRequest().authenticated();
		
		
		http.exceptionHandling()
        .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
		
		return http.build();
	
	}



	/*
	 * 비밀번호를 데이터베이스에 그대로 저장했을 경우, 데이터베이스가 해킹당하면 고객의 회원 정보가 그대로 노출 된다. 이를 해결하기 위해
	 * BCryptPasswordEncoder의 해쉬 함수를 이용해 비밀번호를 암호화하여 저장 BCryptPasswordEncoder를 빈으로
	 * 등록하여 사용
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
