<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面跳转，需要查看页面路径是否改变，页面地址改变</title>
</head>
<body>
<h3><a href="hello.html">如果没有跳转的话，请点击这里</a></h3>
<%
  response.setHeader("refresh", "2;url=hello.html");//设置刷新时间
%>
</body>
</html>