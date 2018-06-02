<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cookie设置</title>
</head>
<body>
<%
  Cookie cookie1=new Cookie("name","lxh");
  Cookie cookie2=new Cookie("age","11");
  cookie1.setMaxAge(10);//设置的时间是以秒为单位的，如果过的话，就会注销，如果是-1的话，不关闭浏览器会一直存在。如果关闭浏览器也会消失。cookie存在客户端
  cookie2.setMaxAge(30);
  response.addCookie(cookie1);
  response.addCookie(cookie2);
%>
</body>
</html>