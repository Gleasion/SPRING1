<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/adminlte.min.css">
<title>공지게시판 등록</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h3 class="display-3">공지게시판 등록</h3>
		</div>
	</div>
	<div class="container">
		<form name="newWrite" action="/notice/insert.do" class="form-horizontal" method="post" id="noticeForm">
<%-- 			<input name="noticeNo" type="hidden" class="form-control" value="${notice.noticeNo }"> --%>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >제목</label>
				<div class="col-sm-5">
					<input name="noticeTitle" type="text" id="noticeTitle" class="form-control" placeholder="subject">
					<font color="red" style="font-size: 12px;">${errors.noticeTitle }</font>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >내용</label>
				<div class="col-sm-8">
					<textarea name="noticeContent" cols="50" rows="5" id="noticeContent" class="form-control" placeholder="content"></textarea>
				</div>
				<font color="red" style="font-size: 12px;">${errors.noticeContent }</font>
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
	CKEDITOR.replace("noticeContent")
	
	var addBtn = $("#addBtn");
	var cancelBtn = $("#cancelBtn");
	
	var noticeTitle = $("#noticeTitle");
	var noticeContent = $("#noticeContent");
	
	addBtn.on("click",function(){
		var title = $("#noticeTitle").val();
		var content = CKEDITOR.instances.noticeContent.getData();
		
// 		if(title == ""){
// 			alert("제목을 입력해주세요.");
// 			$("#noticeTitle").focus();
// 			return false;
// 		}
		
// 		if(content == ""){
// 			alert("내용을 입력해주세요.");
// 			$("#noticeContent").focus();
// 			return false;
// 		}
		
		noticeForm.submit();
	});
	
	cancelBtn.on("click",function(){
		location.href = "/notice/list.do";
	});
});
</script>

</html>



