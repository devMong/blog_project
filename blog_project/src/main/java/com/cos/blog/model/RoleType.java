package com.cos.blog.model;

public enum RoleType {
	USER, ADMIN
}

// 내가 넣는 값을 강제할 수 있다.
// 데이터에 도메인을 생성할 때 만든다.(도메인=범위, 어떠한 범위 안에 데이터를 넣을 때 사용!)
// 예를 들어, 남 | 여 만 기입할 수 있을 때