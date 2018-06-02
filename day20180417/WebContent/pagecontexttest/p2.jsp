<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<!-- 通过p1.jsp的跳转页面，传入一个参数的值。在这个页面来得到这个值，并且进行展示 -->
 <%
    String info= pageContext.getRequest().getParameter("info");
  %>
  <%=info %>
</body>
</html>