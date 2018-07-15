package com.network1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
/**
 *使用多线程来进行聊天
 */
public class Server {
	ServerSocket ss = null;
	Socket socket = null;
	
	public static void main(String[] args) {
	   new Server().serverStart();
	}
	
	/**
	 * 创建这个方法的作用是静态方法不能调用非静态方法，使用一个中间的方法来进行替换
	 */
	public void serverStart(){
		try {
			ss = new ServerSocket(9898);
		
			while (true) {// 这个while的作用是连续得到不同的链接
				try {
					socket = ss.accept();
					new Thread(new ServerThread(socket)).start();
				} catch (Exception e) {
					e.printStackTrace();
				} 

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (ss != null) {
					ss.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 *创建一个服务端的多线程,接受不同的请求
	 */
	private class ServerThread implements Runnable{
	
		Socket socket = null;
		String name = "";
		BufferedReader br = null;
		PrintWriter pw =null;
		private boolean flag=false;//跳出循环使用
		
		/**
		 * 再构造方法中，初始化socket，输入流和输出流,异常直接抛出
		 * @param socket
		 * @throws IOException
		 */
		public ServerThread(Socket socket) throws IOException {
		  this.socket=socket;
		  name = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();// 得到链接的IP和端口号
		  br=new BufferedReader(new InputStreamReader(socket.getInputStream()));// 得到socket的读取流
		  pw = new PrintWriter(socket.getOutputStream(), true);// 得到socket的写入流
		  System.out.println(name+"已经连接");
		}
		
		/**
		 * 接受读取的内容，并且直接抛出异常，在run方法中进行处理
		 * @throws IOException
		 */
		public void receive() throws IOException{
			String tem = null;
			while ((tem = br.readLine()) != null) {// 判断读取的内容，如果不为空，输出
				if ("quit".equals(tem)) {// 判断得到的是否是结束的内容，如果是的话，相应一个特定的信息，跳出读取的循环
					flag=true;
					pw.println("disconnect");
					System.out.println(name+"退出谈话");
					
					break;
				} else {
					System.out.println(name + ":" + tem);
					pw.println("server:" + tem);
				}

			}
		
		}
		
		@Override
		public void run() {
			try {
				while (true) {
					   receive();
					   if(!flag){
						   break;
					   }
				}
			} catch (SocketException e) {// 这个是解决客户端强制离开报错的问题
				System.out.println(name + "客户端强制断开");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
