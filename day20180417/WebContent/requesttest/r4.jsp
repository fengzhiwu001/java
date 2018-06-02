<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>request的其他的方法</title>
</head>
<body>
<%
	String method = request.getMethod() ;
	String ip = request.getRemoteAddr() ;
	String path = request.getServletPath() ;
	String contextPath = request.getContextPath() ;
%>
<h3>请求方式：<%=method%></h3>
<h3>IP地址：<%=ip%></h3>
<h3>访问路径：<%=path%></h3>
<h3>上下文名称：<%=contextPath%></h3>
</body>
</html>