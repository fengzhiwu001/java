<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>getParameterNames方法,这个是视频提供的方法</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8") ;// 设置的是统一编码
	Enumeration enu = request.getParameterNames() ;
%>
<table border="1">
	<tr>
		<td>参数名称</td>
		<td>参数内容</td>
	</tr>
<%
	while(enu.hasMoreElements()){
		String paramName = (String) enu.nextElement() ;
%>
		<tr>
			<td><%=paramName%></td>
			<td><!--需要注意的是td包括的范围  -->
<%
			if(paramName.startsWith("**")){		// 是以**开头
				String paramValue[] = request.getParameterValues(paramName) ;
				for(int x=0;x<paramValue.length;x++){
%>
					<%=paramValue[x]%>、
<%
				}
			} else {
				String paramValue = request.getParameter(paramName) ;
%>
				<%=paramValue%>
<%
			}
%>
			</td>
		</tr>
<%
	}
%>
</table>
</body>
</html>