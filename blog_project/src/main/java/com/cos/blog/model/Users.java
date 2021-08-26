package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//ORM -> Java(다른 언어도 포함) Object를 테이블로 매핑해주는 것이다.
//Users 클래스가 Oracle에 테이블이 생성된다.
@SequenceGenerator(
			name = "USER_SEQ_GEN", // 시퀀스 제너레이터 이름
			sequenceName = "USER_SEG", // 시퀀스 이름
			initialValue = 1, // 시작값
			allocationSize = 1 // 메모리를 통해 할당할 범위 사이즈
		)
public class Users {
	
	@Id // primary Key
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "USER_SEQ_GEN") 
	// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. (= sequnce를 사용)
	
	private int id; // 시퀀스
	
	@Column(nullable = false, length = 30)
	private String username;
	
	@Column(nullable = false, length = 100) 
	// 123456 => 해쉬(비밀번호 암호화)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'") // 컬럼의 기본값을 'user'
	private String role; // Enum을 쓰는 게 좋음 => 어떤 데이터의 도메인을 생성 
	// admin, user, manager .. 이러한 권한을 줘서 각자가 맡은 행위에 제한을 둔다.
	
	@CreationTimestamp // 데이터가 update 및 insert 할 때 시간이 현재 시간으로 자동 입력
	private Timestamp createDate;
}
