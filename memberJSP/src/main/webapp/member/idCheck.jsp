<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.bean.MemberDTO" %>
<%@ page import="member.dao.MemberDAO" %>
    
<%
//데이터
request.setCharacterEncoding("UTF-8");

String id = request.getParameter("id");

//DB
MemberDAO memberDAO = MemberDAO.getInstance();
boolean check = memberDAO.memberCheckId(id);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(check){ %>
	<h2>이미 있는 아이디입니다</h2>
<% } else { %>
	<h2>사용 가능한 아이디입니다</h2>
<% } %>
</body>
</html>