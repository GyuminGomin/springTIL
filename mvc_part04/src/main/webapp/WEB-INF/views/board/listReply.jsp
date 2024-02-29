<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<h1>BOARD LIST</h1>
<!-- qnaList == 질문과 답변 게시글 목록 -->
<table border="1">
	<tr>
		<th>BNO</th>
		<th>TITLE</th>
		<th>WRITER</th>
		<th>REGDATE</th>
		<th>viewcnt</th>
	</tr>
	<c:choose>
		<c:when test="${!empty qnaList}">
			<c:forEach var="board" items="${qnaList}">
				<tr>
					<td>${board.bno}</td>
					<td>${board.title}</td>
					<td>${board.writer}</td>
					<td>${board.regdate}</td>
					<td>${board.viewcnt}</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="5">등록된 게시글이 없습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>
</table>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>