<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user/login.jsp</title>
</head>
<body>
	<h1>LOGIN</h1>
	<form action="${pageContext.request.contextPath}/login" method="POST">
		<input type="text" name="u_id" /> <br/>
		<input type="password" name="u_pw" /> <br/>
		<div>
			<label>
				<input type="checkbox" name="rememberMe" />
				Remember... Me...
			</label>
		</div>
		<input type="submit" value="로그인" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>