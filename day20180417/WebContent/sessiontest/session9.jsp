<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>判断用户是否是新的</title>
</head>
<body>
<%
  boolean b= session.isNew();
  if(b){
%>
欢迎新用户！
<%	  
  }else{
%>
  已经是老用户了！！
<%	  
  }
%>

</body>
</html>