<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/adminlte.min.css">
<title>자유게시판 상세보기</title>
</head>
<body>
	<div class="jumbotron">
	<c:set value="수정" var="textName" />
	<c:if test="${status eq 'd' }">
		<c:set value="상세보기" var="textName" />
	</c:if>
		<div class="container">
			<h3 class="display-3">자유게시판 ${textName }</h3>
		</div>
	</div>
	<div class="container">
		<form name="newUpdate" action="/free/update.do" class="form-horizontal" method="post" id="updateForm">
		<input type="hidden" name="freeNo" value="${free.freeNo }" />
			<div class="form-group row">
				<label class="col-sm-2 control-label" >게시글 번호</label>
				<div class="col-sm-5">
					${free.freeNo }
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >작성자</label>
				<div class="col-sm-5">
					${free.freeWriter }
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >작성일</label>
				<div class="col-sm-5">
					${free.freeDate }
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >조회수</label>
				<div class="col-sm-5">
					${free.freeHit }
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >제목</label>
				<div class="col-sm-5">
					<c:if test="${status eq 'd' }">
						${free.freeTitle }
					</c:if>
					<c:if test="${status ne 'd' }">
						<input name="freeTitle" class="form-control" id="freeTitle" value="${free.freeTitle }" >
					</c:if>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >내용</label>
				<div class="col-sm-8" style="word-break: break-all;">
					<c:if test="${status eq 'd' }">
						${free.freeContent }
					</c:if>
					<c:if test="${status ne 'd' }">
						<textarea name="freeContent" class="form-control" cols="50" rows="5">${free.freeContent }</textarea>
					</c:if>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<p>
						<a href="/free/list.do" class="btn btn-primary"> 목록</a>
						<c:if test="${status eq 'd' }">
							<a href="/free/view.do?freeno=${free.freeNo }" class="btn btn-success">수정</a> 
							<a href="#" onclick="fn_del(); return false;" class="btn btn-danger">삭제</a> 
						</c:if>
						<c:if test="${status ne 'd' }">
							<input type="submit" class="btn btn-success" value="수정 ">
						</c:if>
					</p>
				</div>
			</div>
		</form>
		<hr>
	</div>
</body>
<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/dist/js/adminlte.min.js"></script>
<script type="text/javascript">
function fn_del(){
var updateForm = $("#updateForm");
	
if(confirm("정말 삭제하시겠습니까?")){
	updateForm.attr("method", "post");
	updateForm.attr("action", "/free/delete.do");
	updateForm.submit();
}else{
	updateForm.reset();
}
	
	
}
</script>
</html>


