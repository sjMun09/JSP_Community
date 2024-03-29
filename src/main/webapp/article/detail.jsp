<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
  Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Community DetailPage</title>
</head>
<body>

  <h1>게시물 상세페이지</h1>

  <div>번호 : <%=(int) articleRow.get("id")%></div>
  <div>현재 날짜 : <%=(String) articleRow.get("regDate")%></div>
  <div>수정 날짜 : <%=(String) articleRow.get("updateDate")%></div>
  <div>제목 : <%=(String) articleRow.get("title")%></div>
  <div>내용 : <%=(String) articleRow.get("body")%></div>
  <div>
    <a href="list">리스트로 돌아가기</a>
  </div>
</body>
</html>
