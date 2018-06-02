<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<html>
<head><title></title></head>
<body>
<%	
    //这个的用法是根据filename来创建一个文件夹，根据传递过来的filename来得到文件夹的名称。
    
	request.setCharacterEncoding("UTF-8") ;	// 解决乱码问题
	String name = request.getParameter("filename") ;
	String content = request.getParameter("filecontent") ;
	// 要想操作文件必须有绝对路径，那么这个时候getRealPath()
	String fileName = this.getServletContext().getRealPath("/") + "note" + File.separator + name ;	// 保存在note文件夹之中
	File file = new File(fileName) ;	// 实例化File类对象
	if(!file.getParentFile().exists()){
		file.getParentFile().mkdir() ;	// 建立一个文件夹
	}
	PrintStream ps = null ;
	ps = new PrintStream(new FileOutputStream(file)) ;
	ps.println(content) ;
	ps.close() ;
%>
<%
	Scanner scan = new Scanner(new FileInputStream(file)) ;
	scan.useDelimiter("\n") ;
	StringBuffer buf = new StringBuffer() ;
	while(scan.hasNext()){
		buf.append(scan.next()).append("<br>") ;
	}
	scan.close() ;
%>
<%=buf%>
</body>
</html>