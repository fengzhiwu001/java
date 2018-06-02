<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>getParameterValues方法</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8") ;// 设置的是统一编码，否则会乱码
     String uname= request.getParameter("uname");
     String id= request.getParameter("id");
     String[] values= request.getParameterValues("inst");
  %>
	id:<%=id %><br> uname:<%=uname %><br> 
	爱好:<br> 
	<%
     for(int i=0;i<values.length;i++){
   %>
	<%=values[i] %><br /><!--这个得到的值是value对应的值  -->
	<%
     }
  %>

</body>
</html>