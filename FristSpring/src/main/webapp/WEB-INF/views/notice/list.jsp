<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/adminlte.min.css">
<title>공지게시판 목록</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">공지게시판 목록</h1>
		</div>
	</div>
	<div class="container">
		<div class="text-right">
			<span class="badge badge-success">전체 건</span>
			<form class="input-group input-group-sm" method="post" id="searchForm">
				<input type="hidden" name="page" id="page" value="${pagingVO.currentPage }">
				<select class="form-control" name="searchType">
					<option value="title" <c:if test="${searchType eq 'title' }">selected</c:if>>제목</option>
					<option value="content" <c:if test="${searchType eq 'content' }">selected</c:if>>작성자</option>
				</select>
				<input name="searchWord" style="width: 50%;" type="text" class="form-control float-right" placeholder="Search">
				<div class="input-group-append">
					<button type="submit" class="btn btn-default">
						<i class="fas fa-search"></i>검색
					</button>
				</div>
			</form>
		</div>
		<form action="" method="post">
			<div style="padding-top: 50px">
				<table class="table">
					<thead class="table-dark">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성일</th>
							<th>작성자</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:set value="${pagingVO.dataList }" var="noticeList" />
						<c:choose>
							<c:when test="${empty noticeList }">
								<tr>
									<td colspan="5">조회하신 게시글이 존재하지 않습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${noticeList }" var="notice">
									<tr>
										<td>${notice.noticeNo }</td>
										<td>
											<a href="/notice/detail.do?noticeno=${notice.noticeNo }">
												${notice.noticeTitle }
											</a>
										</td>
										<td>${notice.noticeDate }</td>
										<td>${notice.noticeWriter }</td>
										<td>${notice.noticeHit }</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div class="card-footer clearfix" id="pagingArea">
				${pagingVO.pagingHTML }
			</div>			
			<div align="left">
				<a href="/notice/form.do" onclick="checkForm(); return false;" class="btn btn-primary">&laquo;글쓰기</a>
			</div>
		</form>
		<hr>
	</div>
	<footer class="main-footer">
			<div class="float-right d-none d-sm-block">
				<b>DDIT Spring1</b> 1.0.0
			</div>
			<strong>Copyright &copy; 2023 DDIT SPRING.</strong> All rights reserved.
		</footer>

		<aside class="control-sidebar control-sidebar-dark">
		</aside>
</body>
<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/dist/js/adminlte.min.js"></script>
<script type="text/javascript">
$(function(){
	var pagingArea = $("#pagingArea");
	var searchForm = $("#searchForm");
	
	pagingArea.on("click", "a", function(){
		event.preventDefault();
		var pageNo = $(this).data("page");
		searchForm.find("#page").val(pageNo);
		searchForm.submit();
	});
});
</script>

</html>





