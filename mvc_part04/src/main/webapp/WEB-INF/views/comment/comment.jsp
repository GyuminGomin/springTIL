<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% // TODO comment 게시글별 댓글 처리 view page 확인 후 삭제 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
	#comments li {
		list-style:none;
		padding:10px;
		border: 1px solid #ccc;
		height:150px;
		margin : 5px 0;
	}
	
	#modDiv{
		border:1px solid black;
		padding:10px;
		display:none;
	}
	
	#pagination{
		margin : 0 auto;
		display:flex;
		justify-content: center;
	}
	
	
	#pagination li{
		list-style:none;
		padding:3px;
		border:1px solid skyblue;
		margin:3px;
	}
	
	#pagination li a{
		text-decoration: none;
		color:black;
	}
	
	#pagination li a.active{
		color:red;
	}
</style>
	<div id="modDiv">
		<!-- 수정할 댓글 번호 저장 -->
		<div id="modCno"></div>
		<div>
			<!-- 댓글 내용 수정 -->
			<input type="text" id="modText"/>
		</div>
		<div>
			<!-- 댓글 작성자 수정 -->
			<input type="text" id="modAuth"/>
		</div>
		<div>
			<button id="modBtn">MODIFY</button>
			<button id="delBtn">DELETE</button>
			<button id="closeBtn">CLOSE</button>
		</div>
	</div>
	<h2>댓글 목록</h2>
	<c:if test="${!empty userInfo}">
	<div>
		<div>
			comment author : <input type="text" id="cAuth" value="${userInfo.uname}"/>
		</div>
		<div>
			comment content : <input type="text" id="cText"/>
		</div>
		<button id="addBtn">ADD COMMENT</button>
	</div>
	</c:if>
	<div>
		<!-- 댓글 리스트 -->
		<ul id="comments"></ul>
		
		<!-- 페이징 블럭 정보 -->
		<ul id="pagination">
		
		</ul>
		<br/><br/><br/><br/><br/><br/><br/><br/>
	</div>
	<script>
		var bno = ${board.bno};
		
		var page = 1;
		
		listPage(page);
		
		function listPage(page){
			$("#modDiv").css("display","none");
			$("body").prepend($("#modDiv"));
			let url = "${path}/comments/"+bno+"/"+page;
			$.getJSON(url,function(data){
				// data == Map
				// {'list':{}, 'pm' : {}}
				printList(data.list);
				printPage(data.pm);
			});
		}
		/*
			페이징 블럭으로 처리 시 추가
		*/
		function printPage(pm){
			let str = "";
			if(pm.maxPage != 0){
				if(pm.first){
					str += "<li><a href='1'> 처음 </a></li>";
				}
				if(pm.prev){
					str += "<li><a href='"+(pm.startPage - 1)+"'> < </a></li>";
				}
				
				for(let i = pm.startPage; i <= pm.endPage; i++){
					if(pm.cri.page == i){
						str += "<li><a href='"+i+"' class='active'>"+i+"</a></li>"
					}else{
						str += "<li><a href='"+i+"'>"+i+"</a></li>"
					}
				}
				
				if(pm.next){
					str += "<li><a href='"+(pm.endPage + 1)+"'> > </a></li>";
				}
				if(pm.last){
					str += "<li><a href='"+(pm.maxPage)+"'> 마지막 </a></li>";
				}
			}else{
				str += "<li><a href='1'> 등록된 댓글이 없습니다. </a></li>";
			}
			$("#pagination").html(str);
		}
		
		/*
			페이징 블럭 추가 시 활성화
		*/
		$("#pagination").on("click","li a",function(e){
			e.preventDefault();
			let commentPage = $(this).attr("href");
			page = commentPage;
			listPage(page);
		});
		
		// 검색된 댓글 목록을 출력할 함수
		function printList(list){
			// #comments
			let str = "";
			$(list).each(function(){
				// commentVO == this
				let cno = this.cno;
				let cText = this.commentText;
				let cAuth = this.commentAuth;
				console.log(`\${cno}:\${cText}:\${cAuth}`);
				str += "<li>";
				str += cno+"-"+cAuth;
				// 수정할 댓글 번호, text, auth
				str += ` - <button data-cno='\${cno}' data-text='\${cText}' data-auth='\${cAuth}'>MODIFY</button>`;
				str += "<br/><hr/>"+cText;
				str += "</li>";
			});
			
			// scoroll 시 주석
			$("#comments").html(str);	
			
			/*
			// scroll 시 주석 해제
			if(page == 1){
				$("#comments").html(str);	
			}else{
				$("#comments").append(str);	
			}
			*/
		}
		
		// 댓글 삽입 요청 처리
		$("#addBtn").click(function(){
			let auth = $("#cAuth").val();
			let text = $("#cText").val();
						
			$.ajax({
				type : "POST",
				url : "${path}/comments",
				data : {
					bno : bno,
					commentText : text,
					commentAuth : auth
				},
				dataType : "text",
				success : function(result){
					alert(result);
					// getCommentList();
					page = 1;
					listPage(page);
				},
				error : function(res, stauts){
					console.log(res);
					console.log(status);
				}
			});
		});
		
		// 수정 창 
		$("#comments").on("click","li button",function(){
			console.log("click!");
			let cno = $(this).attr('data-cno');
			let text = $(this).attr('data-text');
			let auth = $(this).attr('data-auth');
			console.log(`\${cno}:\${text}:\${auth}`);
			
			$("#modCno").text(cno);
			$("#modText").val(text);
			$("#modAuth").val(auth);
			
			$(this).parent().after($("#modDiv"));
			
			// display:block, display:none animation : toggle
			$("#modDiv").toggle("slow");
			
		});
		
		// 수정창 닫기  Close modDiv
		$("#closeBtn").click(function(){
			/*
				toggle == show(), hide() 반복 실행
				fadeIn ,   fadeOut  == opacity 투명도로 animation 효과 적용
				slideUp, slide Down == 위에서 아래로, 아래에서 위로 
			*/
			// $("#modDiv").slideDown("slow"); 노출
			// 삭제
			$("#modDiv").slideUp("slow");			
		});
		
		// 댓글 수정 요청 modBtn click event
		$("#modBtn").click(function(){
			let cno = $("#modCno").text();
			let text = $("#modText").val();
			let auth = $("#modAuth").val();
			
			$.ajax({
				type : "PATCH",
				url : "${path}/comments/"+cno,
				headers : {
					"Content-Type" : "application/json"
				},
				data : JSON.stringify({
					commentAuth : auth,
					commentText : text
				}),
				dataType : "text",
				success : function(data){
					alert(data);
					$("#modDiv").slideUp("fast");
					// 해당 페이지 게시글 목록 호출
					listPage(page);
				}
			});
		});
		
		// 삭제 버튼 요청 처리
		$("#delBtn").click(function(){
			let cno = $("#modCno").text();
			$.ajax({
				type : "DELETE",
				url : "${path}/comments/"+cno,
				dataType : "text",
				success : function(result){
					alert(result);
					$("#modDiv").slideUp("slow");
					// 해당 페이지 게시글 목록 호출
					listPage(page);
				}
			});
		});
		
		// scoroll 로 댓글 추가 시 활성화
		/*
		$(window).scroll(function(){
			let dh = $(document).height();
			let wh = $(window).height();
			let wt = $(window).scrollTop();
				
			if((wt+wh) >= (dh - 10)){
				if($("#comments li").size() <= 1){
					return false;
				}
				page++;
				listPage(page);
			}	
		});
		*/
	</script>









