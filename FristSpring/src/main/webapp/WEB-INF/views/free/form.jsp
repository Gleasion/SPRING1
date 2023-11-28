<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/adminlte.min.css">
<title>자유게시판 등록</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h3 class="display-3">자유 게시판 등록</h3>
		</div>
	</div>
	<div class="container">
		<form name="newWrite" action="/free/insert.do" class="form-horizontal" method="post" id="freeForm">
			<div class="form-group row">
				<label class="col-sm-2 control-label" >제목</label>
				<div class="col-sm-5">
					<input name="freeTitle" id="freeTitle" type="text" class="form-control" placeholder="subject">
					<font color="red" style="font-size: 12px">${errors.freeTitle }</font>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >내용</label>
				<div class="col-sm-8">
					<textarea name="freeContent" id="freeContent" cols="50" rows="5" class="form-control" placeholder="content"></textarea>
					<font color="red" style="font-size: 12px">${errors.freeContent }</font>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<input type="submit" class="btn btn-primary" id="addBtn" value="등록 ">				
					<input type="reset" class="btn btn-primary" id="cancelBtn" value="취소 ">
				</div>
			</div>
		</form>
		<hr>
	</div>
</body>
<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/dist/js/adminlte.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(function(){
	CKEDITOR.replace("freeContent");
	
	var addBtn = $("#addBtn");
	var cancelBtn = $("#cancelBtn");
	
	var freeTitle = $("#freeTitle");
	var freeContent = $("#freeContent");
	
	addBtn.on("click", function(){
		var title = $("#noticeTitle").val();
		var content = CKEDITOR.instances.freeContent.getData();
		freeForm.submit();
	});
	
	cancelBtn.on("click", function(){
		location.href = "/free/list.do";
	});
	
});
</script>
</html>



