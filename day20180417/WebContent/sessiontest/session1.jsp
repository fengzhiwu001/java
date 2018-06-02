<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试每隔两秒个数加1</title>
</head>
<body>
需要注意的是，这是变量是一个全局变量。需要!范围内的<br/>
<%!
  int i=0;
%>
<%
  
  response.setHeader("refresh", "2");//设置刷新时间
%>
 浏览次数:<%=i++ %>
</body>
</html>