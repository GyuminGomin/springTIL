<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home.jsp</title>
</head>
<script>
	if ('${msg}' != '') {
		alert('${msg}');
		<%session.removeAttribute("msg"); %>
	}
</script>
<body>
	<a href="board/register">글쓰기</a> <br/>
	<a href="board/listPage">paging 글 목록</a>
</body>
</html>





