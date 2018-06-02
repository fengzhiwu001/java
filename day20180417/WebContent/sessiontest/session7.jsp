<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>获取客户端所有的cookie</title>
</head>
<body>
	<%
		Cookie[] cookies = request.getCookies();//得到所有的客户端的信息
	    System.out.println(cookies);
	    for(int i=0;i<cookies.length;i++){//遍历
	    	String key=cookies[i].getName();//key
	    	String value=cookies[i].getValue();//值
	 %>
       <%=key%>::<%=value %><br>
	 <% 
	    }
	%>
</body>
</html>