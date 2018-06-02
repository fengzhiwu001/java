<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>out测试</title>
</head>
<body>
	<%
		int buffersize = out.getBufferSize();
		int available = out.getRemaining();
		int use = buffersize - available;
	%>
	缓存大小:<%=buffersize%><br /> 
	可用大小大小:<%=available%><br /> 
	已经用去的大小:<%=use%><br />
</body>
</html>