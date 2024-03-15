<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="logoutForm" 
		action="${pageContext.request.contextPath}/user/logout" 
		  method="post"	>
		<input type="hidden" name="${_csrf.parameterName}"
		       value="${_csrf.token}"/>
	</form>
	<script>
		window.onload = function(){
			logoutForm.submit();
		}
	</script>
</body>
</html>











