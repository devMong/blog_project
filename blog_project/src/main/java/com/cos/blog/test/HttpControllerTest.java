package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 그에 맞는 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("ssar@naver.com").build();
		System.out.println(TAG + "getter : " + m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG + "setter : " + m.getUsername());
		return "lombok test 완료";
	}
	
	// http://localhost:8092/http/get => select
	// 인터넷 브라우저 요청은 무조건 get 요청밖에 안된다.
	@GetMapping("/http/get")
	public String getTest() {
		return "get 요청";
	}
//	public String getTest(Member m) {
//		return "get 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
//	}
	
	
	// http://localhost:8089/http/post => insert
	@PostMapping("/http/post") // text/plain, application/JSON
	public String postTest(@RequestBody Member m) { 
		return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	// http://localhost:8089/http/put => update	 
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	// http://localhost:8089/http/delete => delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
