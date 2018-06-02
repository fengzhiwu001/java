<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--得到浏览器的所有的属性和属性对应的值 -->
 <%
     Enumeration<String>  enus= this.getServletContext().getAttributeNames();
     while(enus.hasMoreElements()){
	   String name=enus.nextElement();
   %>
   <%=name %>------------<%=this.getServletContext().getAttribute(name) %>
   <%
     }
   %>
</body>
</html>