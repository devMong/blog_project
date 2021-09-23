package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.Users;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해준다. IoC를 해준다.
/**
 * 
 * 서비스란? 데이터베이스에서 하나의 기능을 담당? update 자체 하나로는 서비스라고 하지 못함 update, insert, delete
 * 등 묶어 하나의 로직을 구성한다면 서비스라고 할 수 있다. 입금서비스 등.. 두 개의 트랜잭션을 하나의 트랙잭션으로 묶어서 서비스화 시킬
 * 수 있다!
 * 
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void 회원가입(Users user) {
		userRepository.save(user);
	}
	// 위의 트랜잭션 과정이 성공하면 Commit, 실패하면 Rollback(현재는 서비스 기능이 하나이므로 rollback X)
}
