<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- mngt/main.jsp -->
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container">
	<h1>MANAGEMENT MAIN</h1>
	<!-- memberList -->
	<table class="container table tabled-bordered">
		<tr>
			<td>회원번호</td>
			<td>아이디</td>
			<td>이름</td>
			<td>권한</td>
			<td>회원가입일</td>		
			<td>최종방문일</td>
			<td>활성화여부</td><!-- 탈퇴여부 -->
			<td>권한선택</td>
		</tr>
		<c:choose>
			<c:when test="${!empty memberList}">
				<c:forEach var="member" items="${memberList}">
				<tr>	
					<td>${member.u_no}</td>
					<td id="user_id">${member.u_id}</td>
					<td>${member.u_name}</td>
					<td>
						<select id="memberAuth">
							<c:forEach var="auth" items="${member.authList}">
								<c:if test="${auth eq 'ROLE_USER'}">
									<option value="ROLE_USER">일반사용자</option>
								</c:if>
								<c:if test="${auth eq 'ROLE_MEMBERSHIP'}">
									<option value="ROLE_MEMBERSHIP">매니저</option>
								</c:if>
								<c:if test="${auth eq 'ROLE_ADMIN'}">
									<option value="ROLE_ADMIN">관리자</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td>
						<f:formatDate value="${member.u_date}" 
							pattern="yyyy-MM-dd (E) HH:mm"/>
					</td>
					<td>
						<f:formatDate value="${member.u_visit}" 
							pattern="yyyy-MM-dd (E) HH:mm"/>
					</td>
					<td>
						<select>
							<option value="y" 
							    ${member.u_withdraw eq 'y' ? 'selected' : ''}>비활성화</option>
							<option value="n" 
								${member.u_withdraw eq 'n' ? 'selected' : ''}>활성화</option>
						</select>
						<sec:authorize access="hasRole('ROLE_ADMIN')"> 
							<input type="button" class="deleteBtn btn btn-danger btn-xs" value="DELETEYN"/>
						</sec:authorize>
					</td>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<td>
						<select class="changeAuth">
							<option disabled selected>권한선택</option>
							<option value="ROLE_USER">일반사용자</option>
							<option value="ROLE_MEMBERSHIP">매니저</option>
							<option value="ROLE_ADMIN">관리자</option>
						</select>
					</td>
					</sec:authorize>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<th colspan="8">등록된 회원정보가 없습니다.</th>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
</div>
<script>
	$(".deleteBtn").on("click",function(){
		var u_delete = $(this).parent().find("select").val();
		console.log(u_delete);
		var parentTr = $(this).closest("tr");
		var u_id = parentTr.find("#user_id").text();
		console.log(u_id);
		
		$.ajax({
			type : "POST",
			url : "${path}/mngt/user/delete",
			data : {
				u_id : u_id,
				u_withdraw : u_delete,
				'${_csrf.parameterName}' : '${_csrf.token}'
			},
			dataType : "text",
			success : function(data){
				if(data == 'y' || data == 'n'){
					alert("변경 완료 : " + data);	
				}else{
					alert('권한이 부족합니다. ADMIN자리를 노리세요!');
				}
			}
		});
	});
	
	
	// 권한 회수 및 부여
	$(".changeAuth").on("change",function(){
		var selectBox = $(this);
		var changeAuthValue = selectBox.val();
		
		var parentTr = $(this).parent().parent();
		var u_id = parentTr.find("#user_id").text();
		
		$.ajax({
			type : "POST",
			url : "${path}/mngt/user/changeAuth",
			data : {
				u_id : u_id,
				u_auth : changeAuthValue,
				'${_csrf.parameterName}' : '${_csrf.token}'
			},
			dataType : "json",
			success : function(result){
				console.log(result);
				var el = parentTr.find("#memberAuth");
				var str = "";
				$(result).each(function(){
					if(this.u_auth == 'ROLE_USER'){
						str+="<option>일반사용자</option>";
					}else if(this.u_auth == 'ROLE_MEMBERSHIP'){
						str+="<option>매니저</option>";
					}else if(this.u_auth == 'ROLE_ADMIN'){
						str+="<option>관리자</option>";
					}
				});
				el.html(str);
				selectBox.find("option").eq(0).prop("selected",true);
			}
		});
		
	});
	
</script>
</body>
</html>





