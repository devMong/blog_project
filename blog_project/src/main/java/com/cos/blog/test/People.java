package com.cos.blog.test;

public class People {
	private int hugryState = 50; // 최대가 100, 배고픔 상태가 50이라고 가정했을 때,
	
	
	
	public static void main(String[] args) {
		People p = new People();
//		p.hugryState = 100; // 100으로 설정하면 배고픔이 없는 상태, 변수에 다이렉트로 접근해서 이변수의 값을 바꾸는 것, 그래서 객체지향과 맞지않음
		p.eat(); 
	}
	
	public void eat() {
		hugryState += 10; // 뭔가를 먹을 때 마다 10씩 증가
		
		// 해당 메소드를 이용해서 hugryState를 변화시켜준다.
		
		// 그래서 변수를 private 로 설정, 함수는 public으로 설정 => 그래서 변수에 다이렉트로 접근하는 것이 아니라 eat 함수를 통해서 접근하는 것이 올바른 형식
	}
}
