<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- views/chat.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<sec:authorize access="isAuthenticated()">
	<sec:authentication var="member" property="principal.member"/>
</sec:authorize>
<style>
	.chatBox{
		height:200px;
		overflow-y:scroll;
		border:1px solid #ccc;
		border-radius:5px;
	}
	
	.chatWrap{
		margin-top:3px;
		margin-bottom:3px;
	}
	
	.chatWrap img{
		width:30px;
		height:30px;
		border-radius:15px;
		border:1px solid black;
	}
</style>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<div class="container">
	<h1>CHAT PAGE - </h1>
	<!-- 어떤 사용자가 어떤 메세지를 전달 했는지를 u_profile 출력 -->
	<div class="container chatBox" id="data">
	</div>
	<br/>
	<div class="row">
		<div class="col-md-10">
			<input type="text" class="form-control" id="message" />
		</div>
		<div class="col-md-2">
			<input type="button" id="sendBtn" 
			class="form-control btn btn-primary" value="SEND"/>
		</div>	
	</div>
</div>
<script>
	var sock = new SockJS("chatHandler"); // 소켓끼리 연결
	// 서버와 연결이 성사되었을 때
	sock.onopen = function(){
		console.log("연결 완료");
		// sock.send("연결 됨");
		var msg = "${member.u_profile},${member.u_name},님이 입장하셨습니다.";
		sock.send(msg);
	}
	
	// 서버와 연결이 끊겼을 때 호출
	sock.onclose = function(){
		console.log("연결 끊김");
	}
	
	// server에서 메세지가 전달되었을 호출
	sock.onmessage = function(message){
		console.log(message);
		var msg = message.data;
		var datas = msg.split(",");
		/*
		"${member.u_profile}
		,
		${member.u_name}
		,
		님이 입장하셨습니다.";
		*/
		// 0 : u_profile
		// 1 : u_name
		// 2 : message
		var userProfile = datas[0];
		var userName = datas[1];
		var userMessage = datas[2];
		if(userMessage.indexOf("입장") > -1){
			notify(userName+" "+userMessage);
		}
		var str = "<div class='chatWrap'>";
		if(userProfile != null && userProfile != ''){
			str += "<img src='${path}/upload"+userProfile+"'/>";
		}else{
			str += "<img src='${path}/resources/img/profile.jpg'/>";
		}
		str += "&nbsp;";
		str += userName +" : " + userMessage;
		str +="</div>";
		$("#data").append(str);
		$("#data").focus();
		$("#data").scrollTop($("#data").prop("scrollHeight"));
		$("#message").focus();
	}
	
	// send 버튼이 눌러졌을때
	$("#sendBtn").click(function(){
		// 메세지 전달
		sendMessage();
	});
	
	// 메세지 작성 칸에서 enter가 눌러졌을 때
	$("#message").keydown(function(e){
		console.log(e.keyCode);
		// 13 == Enter
		if(e.keyCode == 13){
			// 메세지 전달
			sendMessage();
		}
	});	
	
	function sendMessage(){
		var message = $("#message").val();
		var userProfile = "${member.u_profile}";
		var userName = "${member.u_name}";
		sock.send(userProfile+","+userName+","+message);
		$("#message").val("");
	}
	
	/* 오른쪽 하단 알림창 */
	 window.onload = function () {
          if (window.Notification) {
              Notification.requestPermission();
          }
      }
	
	 function notify(text) {
         if (Notification.permission !== 'granted') {
             alert('notification is disabled');
         }
         else {
             var notification = new Notification('새로운 사용자', {
                 icon: '${path}/resources/img/cat3.jpg',
                 body: text,
             });
			
             /*
             notification.onclick = function () {
                 window.open('http://google.com');
             };
             */
         }
     }
</script>

</body>
</html>




