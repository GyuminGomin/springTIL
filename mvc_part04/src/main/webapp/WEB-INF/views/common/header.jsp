<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC project</title>
</head>
<body>
	<br/><hr/>
	<a href="${path}">HOME</a>
	<a href="${path}/user/signIn">SIGN IN</a>
	<a href="<c:url value='/user/signUp'/>">SIGN UP</a> <!-- /를 지정해주면 자동으로 앞에 contextPath를 붙여줌 -->
	
	<a href="">글작성</a>
	<a href="">SIGN OUT</a>
	
	<a href="">게시글 보러 가기</a>
	<br/><hr/>
