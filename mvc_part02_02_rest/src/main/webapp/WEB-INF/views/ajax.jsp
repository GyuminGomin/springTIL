<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX with jQuery</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div style="background-color:#ccc; height:900px;"></div>
	<div>
		<input type="text" id="name" autofocus /> <br/>
		<input type="number" id="age" /> <br/>
		<button id="submit">get submit</button> <br/>
		<button id="post">post submit</button> <br/>
		<button id="put">put submit</button> <br/>
	</div>
	
	<div id="result" style="border:1px solid black; padding:10px; margin-top:10px;"></div>
	<div style="height:500px;"></div>
	
	<script>
		$("#submit").click(function() {
			// click 이벤트가 발생하면 실행 될 callback 함수
			// alert("실행");
			let input_name = $("#name").val();
			let input_age = $("#age").val();
			
			// ajax() - 매개변수로 ajax 통신 요청과 결과를 처리하기 위한 설정 값을 javascript 객체로 전달
			let obj = {
				type : 'GET',		// 전송 방식
				async : true,		// 비동기, 동기 제어
				url : "sample2",	// 요청 URL, 요청 경로
				data : {			// 전달 파라미터
					name : input_name,
					age : input_age
				},
				// 응답으로 전달 받은 responseText를 어떤 타입으로 사용할 건지 지정
				dataType : "json",			// text, json, xml 3가지 지정 가능
				// 응답이 정상적으로 완료 되었을 때 실행 될 함수
				success : function(data, statusText, response) { // readyStatus 가 4, 응답 코드 가 200일 때 작동
					console.log("data : ", data);
					console.log("statusText : ", statusText);
					console.log("response : ", response);
					
					let html = "<table border='1'>";
					html += "<tr><th>이름</th><th>나이</th></tr>";
					html += `<tr><td>\${data.name}</td><td>\${data.age}</td></tr>`; // template 문자열이라고 함 (`) -> js에 만들어 논 변수를 문자열 완성할 때 더하기로 이어서 작성할 필요없이 바로 문자안에 삽입할 수 있다.
					html += "</table>";
					console.log(html);
					$("#result").html(html); // innerHtml 역할을 해주는 함수
				},
				error : function(response, status) {
					console.log("error response : ", response); // response는 XMLHttpRequest다.
					console.log("처리 상태 : ", status);
					console.log("error message : ", response.responseText);
				}
			}; // obj end
			
			$.ajax(obj);
			
		}); // get submit ajax event end
		
		$("#post").click(function() {
			
			let input_name = $("#name").val();
			let input_age = $("#age").val();
			
			$.ajax({
				type : "POST",
				url : "sampleList", // async 생략 default true (비동기)
				data : {
					name : input_name,
					age : input_age
				},
				dataType : "json", // 응답된 데이터를 어떤 형식으로 지정해줄 것인가 (data로 넘겨줄 때 변경된 형식으로 넘겨줌)
				success : function(data) {
					console.log(data);
					let result = `<table border=1>
									<tr>
										<th>이름</th>
										<th>나이</th>
									</tr>`;
					for(var i = 0; i < data.length; i++) {
						result += `<tr>
									<td>\${data[i].name}</td>
									<td>\${data[i].age}</td>
								  </tr>`;
					}
					result += `</table>`;
					console.log(result);
					$("#result").append(result); // 기존 컨텐츠는 그대로 두고 새롭게 쓰는 것 (이어쓰기)
				},
				error : function(res) {
					alert(res.responseText);	
				}
			}); 
		}); // end ajax event post
		
		// (GET, POST) - PUT, PATCH, DELETE
		$("#put").click(function() {
			$.ajax({
				type : "PUT",
				url : "sampleList",
				dataType : "json",
				headers : {
					// application/x-www-form-urlencoded 로 되어 있는 것을 변경
					'Content-Type' : 'application/json'
				},
				// JSON.parse(문자열) JSON 형식으로 작성 된 문자열을 javascript Object로 변환
				// JSON.stringify(Jscript Object) javascript Object를 JSON 형식의 문자열로 변환
				data : JSON.stringify({
					name : $("#name").val(),
					age : $("#age").val()
				}),
				success : function(data) {
					console.log(data);
				},
				error : function(res) {
					alert(res.responseText);
					console.log(res);
				}
			});
		});
	</script>
</body>
</html>