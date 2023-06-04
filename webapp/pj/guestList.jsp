<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="pj/style.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class= "GList" id="GList">
    <h1 class="list_title">방명록 목록</h1>
    <table>
        <tr>
        <th>번호</th>
        <th>작성자</th>
        <th>이메일</th>
        <th>작성일</th>
        <th>제목</th>
     </tr>
     <c:forEach var = "guest" items = "${guestlist}" varStatus = "status">
     <tr>
         <td>${f:length(guestlist)-status.index}</td>
         <td>${guest.user}</td>
         <td>${guest.email}</td>
         <td>${guest.date}</td>
         <td><a href="guest.nhn?action=getGuest&aid=${guest.aid}">${guest.title}</a></td>
     </tr>
     </c:forEach>
    </table>
    <div class="btnstyle">
        <button class="insertBtn" onclick="insertBtn();">등록</button>
    </div>
</div> <!-- 목록 -->
   <div id="Ginsert">
        <h1 class="institle">방명록 입력</h1>
        <form method="post" action="/hw/guest.nhn?action=addGuest" onsubmit="return checkForm()">
            
        <div class="ins">
            <label>작성자</label>
            <input type="text" id="user" name="user" class="form-control">
        </div>

        <div class="ins">
            <label>이메일</label>
            <input type="text" id="email" name="email" class="form-control">
        </div>
        
        <div class="ins">
            <label>제목</label>
            <input type="text" id="title" name="title" class="form-control">
        </div>
        
        <div class="ins">
            <label>비밀번호</label>
            <input type="text" id="pw" name="pw" class="form-control">
        </div>

        <div class="ins">
            <textarea id="content" name="content" cols="50" rows="15" class="form-control"></textarea>
        </div>
        <div class="insertbtnstyle">
            <button type="submit" class="submitBtn">입력</button>
            <button type="reset" class="cancelBtn" onclick="reset();">취소</button>
            <button type="button" onclick="location.href='/hw/guest.nhn'">목록</button>
        </div>  
            </form>
        </div>
<script src="pj/script.js"></script>
</body>
</html>




