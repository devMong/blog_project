package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Reply { 
	@Id
	private int id;
	
	@Column(nullable = false, length = 200) 
	private String content;
	
	@ManyToOne // Many = Reply | One = Board => 여러개의 답변은 하나의 게시글에 존재 할 수 있다.
	@JoinColumn(name = "boardId")
	private Board Board;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Users user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
