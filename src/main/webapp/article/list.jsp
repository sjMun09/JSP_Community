<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
  List<Map<String, Object>> articleRows = (List<Map<String</String>, Object>>) request.getAttribute("articleRows");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Community List</title>
</head>
<body>
  <h1>게시물 리스트 v1</h1>

  <ul>
    <li><%= articleRows.get(0).get("id")%>번, <%= (String) articleRows.get(0).get("regDate")%>, <%= (String) articleRows.get(0).get("updateDate")%>,  <%= (String) articleRows.get(0).get("title")%></li>
    <li><%= articleRows.get(1).get("id")%>번, <%= (String) articleRows.get(1).get("regDate")%>, <%= (String) articleRows.get(1).get("updateDate")%>,  <%= (String) articleRows.get(1).get("title")%></li>
    <li><%= articleRows.get(2).get("id")%>번, <%= (String) articleRows.get(2).get("regDate")%>, <%= (String) articleRows.get(2).get("updateDate")%>,  <%= (String) articleRows.get(2).get("title")%></li>
  </ul>

  <h1>게시물 리스트 v2</h1>

  <ul>
    <%
      for (int i=0; i<3; i++){
    %>
    <li><%= articleRows.get(i).get("id")%>번, <%= (String) articleRows.get(i).get("regDate")%>, <%= (String) articleRows.get(i).get("updateDate")%>,  <%= (String) articleRows.get(i).get("title")%></li>
    <%
      }
    %>
  </ul>

  <h1>게시물 리스트 v3</h1>

  <ul>
    <%
    for (int i=0; i<3; i++){
      Map<String, Object> articleRow = articleRows.get(i);
    %>
    <li><%= articleRow.get("id")%>번, <%= (String) articleRow.get("regDate")%>, <%= (String) articleRow.get("updateDate")%>,  <%= (String) articleRow.get("title")%></li>
    <%
    }
    %>
  </ul>

  <h1>게시물 리스트 v4</h1>

  <ul>
    <%
    for (int i=0; i< articleRows.size(); i++){
    Map<String, Object> articleRow = articleRows.get(i);
    %>
    <li><%= articleRow.get("id")%>번, <%= (String) articleRow.get("regDate")%>, <%= (String) articleRow.get("updateDate")%>,  <%= (String) articleRow.get("title")%></li>
    <%
    }
    %>
  </ul>

  <h1>게시물 리스트 v5</h1>

  <ul>
    <%
    for (<String, Object> articleRow : articleRows){
    %>
    <li><%= articleRow.get("id")%>번, <%= (String) articleRow.get("regDate")%>, <%= (String) articleRow.get("updateDate")%>,  <%= (String) articleRow.get("title")%></li>
    <%
    }
    %>
  </ul>
</body>
</html>
