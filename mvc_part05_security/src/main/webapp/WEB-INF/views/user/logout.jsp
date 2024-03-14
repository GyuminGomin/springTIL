<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user/logout.jsp</title>
</head>
<body>
	<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/logout">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	
	<script>
		window.onload= function() {
			loginForm.submit();
		}
	</script>
</body>
</html>