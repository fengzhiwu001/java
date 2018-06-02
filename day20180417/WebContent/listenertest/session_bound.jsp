<%@page import="org.lxh.listenerdemo.LoginUser"%>
<%@ page contentType="text/html" pageEncoding="GBK"%>
<html>
<head><title></title></head>
<body>
<%
	LoginUser user = new LoginUser("MLDN") ;
	session.setAttribute("info",user) ;	// 直接保存LoginUser对象
%>
</body>
</html>