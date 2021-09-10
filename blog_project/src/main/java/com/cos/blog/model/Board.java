package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Board {

	@Id
	private int id;
	@Column(nullable = false, length = 100)
	private String title;

	@Lob // 대용량 데이터를 쓸 때 사용
	private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 된다.

	@ColumnDefault("0")
	private int count; // 조회수

	// Many = Board, User = One | 한명의 유저는 여러 게시글을 사용할 수 있다.
	// 만약 OneToOne 일 경우 한 명의 유저는 하나의 게시글만 사용할 수 있다.
	// OneToMany : 하나의 게시글은 여러명의 유저가 사용할 수 있다. 그러나 문법 상 맞지 않음.

	@JoinColumn(name = "userId") // 실제로 테이블 생성 시 userId 라는 컬럼명으로 만들어진다.
	@ManyToOne(fetch = FetchType.EAGER) // 기본값
	private Users userId; // DB는 오브젝트로 저장할 수 없다, FK, 자바(=ORM)는 오브젝트 자체로 저장 가능

															// board)
// mappedBy 연관관계의 주인이 아니다. (난 FK가 아니다) DB에 컬럼을 만들지 마라. 나의 주인은 Reply 테이블의 board가 FK이다.
//	@JoinColumn(name="replyId") 실제 FK가 필요X, 왜냐하면 1 정규화가 성립되지 않기 때문이다.
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // Reply 테이블에 있는 Board를 넣어준다. (board는 필드값, 즉 reply 클래스에 있는
	private List<Reply> reply;
// FK가 아니기 때문에 DB에 만들지 말라. 조인문을 통해서 값을 얻기 위해 필요하다

	@CreationTimestamp
	private Timestamp createDate;

}
