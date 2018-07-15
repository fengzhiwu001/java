package com.network2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	Socket socket =null;
	PrintWriter pw=null;
	BufferedReader bri=null;
	BufferedReader br=null;
	boolean flag=false;
	
	public static void main(String[] args) {
		new Client().startup();
	}
	
	/**
	 * 启动读取的流程
	 */
	public void startup(){
		try {
			socket =new Socket("127.0.0.1", 9999);
            pw=new PrintWriter(socket.getOutputStream(),true);//通过socket得到写入流
            bri=new BufferedReader(new InputStreamReader(System.in));//读取流，读取控制台
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));//通过socket得到读取流
            String str=null;
            //使用多线程来读取响应的信息
            new Thread(new clientThread()).start();
            while((!flag)&&(str=bri.readLine())!=null){//读取控制台的内容，多添加一个判断的作用是如果跳出程序，这个循环将不会使用
            	pw.println(str);
           
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
	
	/**
	 * 读取响应过来的内容
	 * @throws IOException 
	 */
	public void receive() throws IOException{
		 String len=null;
		if((len=br.readLine())!=null){//读取控制台写入的信息，如果不为空，输出到服务器
    		 if("disconnect".equals(len)){//判断服务器端相应的内容，如果是特定值，则直接跳出
    			// break;//必须存在循环中，如果上层没有循环会报错：break cannot be used outside of a loop or a switch。
    			 //因为在这里不能使用break。所以退出的话，需要使用flag来进行判断
    			 flag=true;
    			 System.out.println("已经退出");//作用是使得while的循环可以结束
    		 }
    		 System.out.println(len);
    	 }
	}
	
	/**
	 *客户端的多线程的作用主要是解决读取延迟的问题，并且需要注意关闭连接
	 */
	public class clientThread implements Runnable{
		@Override
		public void run() {
			while(true){
				try {
					if(flag){
						break;//break使用在循环中，如果没有循环，不可以进行使用
					}else{
						receive();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
