<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>得到项目的真实路径</title>
</head>
<body>
<!--得到项目的根路径 -->
    <%
    //E:\workwang\eclipse-jee-mars-1-win32\eclipse\xiangmu\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\day20180417\
      String realPath= application.getRealPath("/");
      String realPath1=  this.getServletContext().getRealPath("/");
    %>
    <%=realPath %><br/>
    <%=realPath1 %><br/>
</body>
</html>