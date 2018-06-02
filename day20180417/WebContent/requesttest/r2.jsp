<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>getParameterNames方法</title>
</head>
<body>
	<%
   request.setCharacterEncoding("UTF-8");
%>
	<table border="1">
		<tr>
			<td>参数</td>
			<td>值</td>
		</tr>
		<%
      Enumeration<String> enus = request.getParameterNames();//得到所有的name对应的值
      while(enus.hasMoreElements()){//判断是否有下一个，如果有需要得到key,然后通过key得到value的值。
    	 String key= enus.nextElement();
   %>
		<tr>
			<td><%=key %></td>
			<%
         if(key.startsWith("**")){//如果得到的是多个的话，遍历组成一个字符串，逗号隔开
        	 String[] values=request.getParameterValues(key);
        	 StringBuffer sb=new StringBuffer();
        	 for(int i=0;i<values.length;i++){
        		 if(i==values.length-1){
        			 sb.append(values[i]);
        		 }else{
        			 sb.append(values[i]+",");
        		 }
        	
        	 }
        	 %>
			<td><%=sb %></td>
			<% 	 
         }else{
        	 String value=request.getParameter(key);
       %>
			<td><%=value %></td>
			<% 
         }
      %>


		</tr>


		<% 	  
      }
  %>
	</table>
</body>
</html>