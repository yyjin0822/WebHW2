<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="pj/style.css" />
</head>
<body>
<h1 class = "list_title">방명록 수정</h1>
<form method="post" action="/hw/guest.nhn?action=updateGuest&aid=${guest.aid}" onsubmit="return checkForm()">
<div id="Ginsert" style="display:block;">
	<div class="ins">
            <label>작성자</label>
			<input type="text" id="user" name="user" value="${guest.user}" class="form-control">
        </div>

        <div class="ins">
            <label>이메일</label>
            <input type="text" id="email" name="email" value="${guest.email}" class="form-control">
        </div>
        
        <div class="ins">
            <label>제목</label>
            <input type="text" id="title" name="title" value="${guest.title}" class="form-control">
        </div>
        
        <div class="ins">
            <label>비밀번호</label>
            <input type="text" id="pw" name="pw" value="${guest.pw}" class="form-control">
        </div>

        <div class="ins">
            <textarea id="content" name="content" cols="50" rows="15" class="form-control">${guest.content}</textarea>
        </div>
	<div class="insertbtnstyle">
            <button type="submit" class="submitBtn">수정</button>
            <button type="button" onclick="location.href='guest.nhn?action=deleteGuest&aid=${guest.aid}'">삭제</button>
            <button type="button" onclick="location.href='/hw/guest.nhn'">목록</button>
    </div>
    </div>
</form>
<script src="pj/script.js"></script>
</body>
</html>