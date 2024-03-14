<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/common/header.jsp"%>
 <h1>BOARD LIST</h1>
<% // TODO board search 검색기능 추가 뷰 check 확인후 삭제 %>
 <!-- 
 	2024/03/13 - 검색 기능 추가
 -->
 <div>
 	<h2>게시글 검색</h2>
 	<form action="listReply" method="GET">
 		<select name="searchType">
 			<option value="n">-----------------------------------</option>
 			<!-- EL을 이용하여 검색타입이 존재할 시 해당 option을 선택해서 출력하도록 추가 -->
 			<option value="t" ${cri.searchType eq 't' ? 'selected' : ''}>TITLE</option>
 			<option value="c" ${cri.searchType eq 'c' ? 'selected' : ''}>CONTENT</option>
 			<option value="w" ${cri.searchType eq 'w' ? 'selected' : ''}>WRITER</option>
 			<option value="tc" ${cri.searchType eq 'tc' ? 'selected' : ''}>TITLE &amp; CONTENT</option>
 			<option value="cw" ${cri.searchType eq 'cw' ? 'selected' : ''}>CONTENT &amp; WRITER</option>
 			<option value="tcw" ${cri.searchType eq 'tcw' ? 'selected' : ''}>TITLE &amp; CONTENT &amp; WRITER</option>
 		</select>
 		<input type="text" name="keyword" value="${cri.keyword}"/>
 		<input type="submit" value="SEARCH"/>
 		<!-- 한번에 보여줄 게시글 개수 지정 -->
 		<select name="perPageNum" onchange="form.submit();">
 			<c:forEach var="num" begin="5" end="20" step="5">
	 			<option value="${num}" ${num eq cri.perPageNum ? 'selected' : ''}>${num}개씩 보기</option>
 			</c:forEach>
 		</select>
 	</form>
 </div>
 <hr/>
 <br/>
 
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
 				<c:choose>
 					<c:when test="${board.showboard == 'y'}">
		 				<tr>
		 					<td>${board.bno}</td>
		 					<td>
		 						<a href="${path}/board/readPage?bno=${board.bno}">
		 							<!-- 답변글일 경우 -->
		 							<c:if test="${board.depth != 0}">
		 								<c:forEach var="i" begin="1" end="${board.depth}">
		 									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 								</c:forEach>
		 								<!-- ㅂ + 한자 key + 6 -->
		 								└>
		 							</c:if>
		 							${board.title} 
		 							<c:if test="${!empty board.files}">
		 								[${board.files.size()}]
		 							</c:if>
		 							<c:if test="${board.regdate ne board.updatedate}">
		 								- [수정된 게시글] 
		 							</c:if>
								</a>
							</td>
		 					<td>${board.writer}</td>
		 					<td>
		 						<f:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regdate}"/>
							</td>
		 					<td>${board.viewcnt}</td>
		 				</tr>
 					</c:when>
 					<c:otherwise>
 						<tr>
 							<td></td>
 							<td>삭제된 게시물 입니다.</td>
 							<td></td>
 							<td>
 								<!-- 게시글 삭제요청 처리 시간 -->
 								<f:formatDate value="${board.updatedate}" pattern="yyyy-MM-dd HH:mm"/>
 							</td>
 							<td></td>
 						</tr>
 					</c:otherwise>
				</c:choose>
 			</c:forEach>
 			<% // TODO board search 페이징 처리 뷰 check 확인후 삭제 %>
 			<!-- 2024-03-13 게시글 목록이 존재 할때 페이지 블럭 추가 -->
 			<tr>
 				<th colspan="5">
 					<c:if test="${pm.first}">
 						<a href="listReply${pm.makeQuery(1)}">[&laquo;]</a>
 					</c:if>
 					<c:if test="${pm.prev}">
 						<a href="listReply${pm.makeQuery(pm.startPage-1)}">[&lt;]</a>
 					</c:if>
 					<c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}">
 						<a href="listReply${pm.makeQuery(i)}">[${i}]</a>
 					</c:forEach>
 					<c:if test="${pm.next}">
 						<a href="listReply${pm.makeQuery(pm.endPage+1)}">[&gt;]</a>
 					</c:if>
 					<c:if test="${pm.last}">
 						<a href="listReply${pm.makeQuery(pm.maxPage)}">[&raquo;]</a>
 					</c:if>
 				</th>
 			</tr>
 		</c:when>
 		<c:otherwise>
 			<tr>
 				<td colspan="5">등록된 게시글이 없습니다.</td>
 			</tr>
 		</c:otherwise>
 	</c:choose>
 </table>
 
 <%@ include file="/WEB-INF/views/common/footer.jsp"%>
 
 
 
 
 
 
 
 
 