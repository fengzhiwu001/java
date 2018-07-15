package com.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
   public static void main(String[] args) {
	   ServerSocket ss=null;
	   Socket socket = null;
	   String name="";
	   try {
		 ss=new ServerSocket(9898);
		 while(true){//这个while的作用是连续得到不同的链接
				  try {
					socket = ss.accept();
					 name=socket.getInetAddress().getHostAddress()+":"+socket.getPort();//得到链接的IP和端口号
					  BufferedReader br=
							  new BufferedReader(new InputStreamReader(socket.getInputStream()));//得到socket的读取流
					  PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);//得到socket的写入流
					 String tem=null;
					   while((tem=br.readLine())!=null){//判断读取的内容，如果不为空，输出
							System.out.println(name+":"+tem);
							if("quit".equals(tem)){//判断得到的是否是结束的内容，如果是的话，相应一个特定的信息，跳出读取的循环
								pw.println("disconnect");
								break;
							}else{
								pw.println("server:"+tem);
							}
							
					   }
				  }catch (SocketException e) {//这个是解决客户端强制离开报错的问题
						System.out.println(name+"客户端强制断开");
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					socket.close();
				}
		 
		 }
		 
	
	} catch (IOException e) {
		e.printStackTrace();
	}finally{
		
		try {
			if(ss!=null){
				ss.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
}
