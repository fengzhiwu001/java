package com.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		Socket socket =null;
		try {
			 socket =new Socket("127.0.0.1", 9898);
             PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);//通过socket得到写入流
             BufferedReader bri=new BufferedReader(new InputStreamReader(System.in));//读取流，读取控制台
             BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));//通过socket得到读取流
             String str=null;
             while((str=bri.readLine())!=null){//读取控制台的内容
            	 pw.println(str);
            	 String len=null;
            	 if((len=br.readLine())!=null){//读取控制台写入的信息，如果不为空，输出到服务器
            		 if("disconnect".equals(len)){//判断服务器端相应的内容，如果是特定值，则直接跳出
            			 break;
            		 }
            		 System.out.println(len);
            	 }
             }
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(socket!=null){
					socket.close();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
