<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadAjax.jsp</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
	.fileDrop{
		width:100%;
		height:200px;
		background-color:#ccc;
		border:1px solid black;
	}
</style>
</head>
<body>
	<h2>File drag &amp; drop</h2>
	
	<!-- upload할 파일을 drop할 박스 -->
	<div class="fileDrop"></div>
	
	<!-- upload 된 파일 목록 출력 -->
	<div id="uploadedList"></div>
	
	<script>
		// drag drop 이벤트 시 파일을 인식 하려는 브라우저의 기본 이벤트 무시
		$(".fileDrop").on("dragenter dragover", function(e) {
			// 발생한 이벤트 객체를 매개변수로 전달
			e.preventDefault(); // event 객체의 기본 이벤트 무시
		});
		
		$(".fileDrop").on("drop", function(e) {
			e.preventDefault();
			
			// 발생 한 drag drop 이벤트 객체에서 사용자가 드랍한 파일 정보를 읽어옴
			let files = e.originalEvent.dataTransfer.files;
			console.log(files);
			
			let formData = new FormData(); // 가상의 폼 태그를 의미
			for (let i=0; i < files.length; i++) {
				let file = files[i];
				let maxSize = 10485760; // 10MB
				if (maxSize < file.size) {
					alert("업로드 할 수 없는 크기의 파일입니다.");
					return false;
				}
				formData.append("files", file); // input file type에 multiple이란 녀석으로 데이터를 삽입한것과 같다.
			}
			
			$.ajax({
				type : "POST",
				url : "uploadFiles",
				data : formData,
				contentType : false,
				processData : false, // true가 되어있으면, 쿼리스트링으로 변환시켜줌 (막아주는 역할)
				dataType : "json",
				success : function(result) {
					console.log(result);
					let str = "";
					for (let i =0; i <result.length; i++) {
						let isImage = checkImageType(result[i]);
						console.log(isImage);
						if(isImage) {
							console.log("이미지 파일");
							str += "<div>";
							str += "<img src='${path}/upload"+result[i]+"' />";
							str += "</div>";
							str += "<a href=''>"+ getOriginalName(result[i]) +"</a>";
						} else {
							console.log("일반 파일");
							str += "<div>";
							str += "<img src='${path}/resources/img/file.png' />";
							str += "</div>";
							str += "<a href=''>"+ getOriginalName(result[i]) +"</a>";
						}
					} // end for
					$("#uploadedList").append(str);
				} // end success
			}); // end ajax
		}); // end drop event
		
		function getOriginalName(fileName) {
			let index = fileName.lastIndexOf("_") + 1;
			// 이름만 반환
			return fileName.substr(index);
		}
		
		// upload된 파일이 이미지 파일인지 확인
		function checkImageType(fileName) {
			let pattern = /jpg|jpeg|gif|png/i; // js의 정규 표현식의 시작과 끝은 /, i는 ignorecase (대소문자 구분 x)
			let result = pattern.test(fileName); // 모든 문자열에서 찾는 것
			// console.log(result);
			// return result;
			let img = ['jpg','jpeg','png','gif'];
			for (let i = 0; i< img.length; i++) {
				let isImage = fileName.toLowerCase().endsWith(img[i]);
				if (isImage) { // endsWith 해당되는 문자열로 끝이 나는가
					return true;
				}
			}
			return false;
		}
	</script>
</body>
</html>