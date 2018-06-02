<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面跳转</title>
</head>
<body>
<%
  System.out.println("页面跳转之前----------------------");
%>
<%response.sendRedirect("hello.html"); %>
<%
System.out.println("页面跳转之后----------------------");//这个代码会执行
%>
</body>
</html>