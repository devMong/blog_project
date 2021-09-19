
let index = {
	init: function(){
	// function(){} 대신 ()=>{} 를 사용한 이유 : this를 바인딩 하기 위해서 사용!
		$('#btn-save').on('click', ()=>{ 
			this.save();
		});
	},
	
	save: function(){
		// alert('user의 save 함수 호출됨');
		let data ={
			username: $('#username').val(),
			password: $('#password').val(),
			email: $('#email').val()
		};
		
		//console.log(data);
		
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변환하여 insert 요청을 할 것이다.
		// ajax 호출 시 default가 비동기 호출
		// ajax 가 통신 성공 후 json 리턴해주면 서버가 자바 오브젝트로 변환해준다. 
		
		$.ajax({
			type: "POST",
			url: "/blog/api/user",
			// JSON 문자열, data:data => 자바스크립트 오브젝트
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(=MINE TYPE)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든것이 문자열 (default : String), 만약 json이면 => javascript로 변환
			
		}).done(function(response) {
		//	console.log(response);
			alert('회원가입이 완료되었습니다.');
			location.href="/blog";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}

index.init();