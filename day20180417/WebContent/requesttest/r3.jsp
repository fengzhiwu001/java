<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>getHeaderNames方法的用法</title>
</head>
<body>
<%
     Enumeration<String> headerNames= request.getHeaderNames();//得到请求的相关信息
     while(headerNames.hasMoreElements()){//遍历，得到每一个的值
    	 String key=headerNames.nextElement();
    	 String value=request.getHeader(key);
    %>
       <%=key%>:::<%=value %><br>
    <% 	 
      }
     %>
</body>
</html>