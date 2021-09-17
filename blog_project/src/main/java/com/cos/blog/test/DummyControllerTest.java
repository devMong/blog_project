package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	// 데이터 삭제하기
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);			
		} catch (EmptyResultDataAccessException e) { // Exception은 에러계의 최상위 부모
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		return "삭제 되었습니다. id : " + id;
	}
	
	
	// email, password
	@Transactional // 함수 종료 시 자동 커밋
	@PutMapping("/dummy/user/{id}")
	public Users updateUser(@PathVariable int id, @RequestBody Users requestUser) {
		
		// json 데이터를 요청 => Spring boot가 java Object(MessageConverter의 Jackson 라이브러리)로 변환해서 받는다
		// 이 떄 필요한 어노테이션이 RequestBody
		
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		// 실제 데이터베이스를 User 객체에 담음
		Users user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		
		System.out.println("변경하기 전 -> " + user);
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		System.out.println("user의 객체 -> " + user);
		
		//userRepository.save(user); 
		
		/**
		 * 더티 체킹
		 * 
		 */
		
		// save()는 insert 할 때 쓰는 메소드
		// 만약에 이미 존재하는 id 값이라면?
		// 그 땐 insert 가 아니라 update로 적용이 된다.
		// 그러나 update로 이용하게 될 경우, 내가 직접 작성한 파라미터만 변경되고 나머지 필드값들은 null로 변환된다.
		// 그러므로 웬만하면 update로 사용하지 않는 것을 추천!
		
		return user;
	}
	

	@GetMapping("/dummy/users")
	public List<Users> list() {
		return userRepository.findAll();
	}
	
	// 한 페이지당 두 건의 데이터를 리턴받아 출력
	@GetMapping("/dummy/user")
	public List<Users> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Users> pagingUser = userRepository.findAll(pageable);
		
		List<Users> users = pagingUser.getContent();
		return users;
	}
	
	@GetMapping("/dummy/user/{id}")
	public Users detail(@PathVariable int id) {
		Users user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다.");
			}
		});
		
		return user;
	} 
	

	// http의 body에 username, password, email 데이터를 가지고 (요청해보자)
	@PostMapping("/dummy/join")
	public String join(Users user) { // key=value(약속된규칙)
		 
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
