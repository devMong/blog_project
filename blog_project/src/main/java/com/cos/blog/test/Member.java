package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@Data // getter + setter
//@AllArgsConstructor // = 모든 생성자를 생성
@NoArgsConstructor // 빈 생성자
//@RequiredArgsConstructor // final를 붙은 애들에 대한 생성자 생성
public class Member {
	
	// final은 불변성 유지를 위해 사용한다.
	
	private int id;
	private String username;
	private String password;
	private String email;

	// 생성자 생성
	
	@Builder
	public Member(int id, String username, String password, String email) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public Member(String username, String password, String email) {
		
		this.username = username;
		this.password = password;
		this.email = email;
	}	
	
	// 위의 private변수들의 상태값들은 함수(Getter/Setter)를 통해서 변경되게 한다.

	
	
}
