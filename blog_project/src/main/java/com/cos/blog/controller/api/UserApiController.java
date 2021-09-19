package com.cos.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Users;

@RestController
public class UserApiController {
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody Users user) {
		System.out.println("UserApiController : save 호출");
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
}
