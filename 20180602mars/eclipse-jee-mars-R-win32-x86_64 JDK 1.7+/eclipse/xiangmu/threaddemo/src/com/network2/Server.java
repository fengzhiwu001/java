package com.network2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
/**
 *使用多线程来进行聊天
 */
import java.util.List;
/**
 * 服务器多线程就是不断的接收信息
 *
 */
public class Server {

	List<ServerThread> clients;//创建一个集合，接收所有的客户端

	public static void main(String[] args) {
	   new Server().serverStart();
	}
	
	/**
	 * 创建这个方法的作用是静态方法不能调用非静态方法，使用一个中间的方法来进行替换
	 */
	public void serverStart(){
		ServerSocket ss = null;
		Socket socket = null;
		try {
			ss = new ServerSocket(9999);
			clients=new ArrayList();
			//再接收到客户端请求时，初始化对象  注意位置，如果在while里边，那么这个一直只会添加一个客户端，会存在问题
			while (true) {// 这个while的作用是连续得到不同的链接
					socket = ss.accept();
					ServerThread sThread=new ServerThread(socket);
					new Thread(sThread).start();
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
		private boolean flag=false;//跳出循环使用
		Socket socket = null;
		String name = "";
		BufferedReader br = null;
		PrintWriter pw =null;
		
		
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
		  clients.add(this);//创建对象时，把这个对象添加到集合中
		  send(name+"已经连接");
		}
		/**
		 * 所有的响应的内容都是使用这个方法来进行输出
		 */
		public void send(String msg){
			System.out.println("连接的个数 ："+clients.size());
			for (ServerThread serverThread : clients) {
				//System.out.println("send"+msg);
				serverThread.pw.println(msg);
			}
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
					clients.remove(this);//移除添加进来的
					System.out.println(name+"退出谈话");
					 send(name+"退出谈话");
					
					break;
				} else {
					System.out.println(tem);
					send(name+":"+tem);
					//pw.println("server:" + tem);
				}

			}
		
		}
		
		@Override
		public void run() {
			try {
				while (true) {//这个线程处理接受的方法
					   if(flag){
						   break;
					   }else{
						   receive(); 
					   }
					 
				}
			} catch (SocketException e) {// 这个是解决客户端强制离开报错的问题
				System.out.println(name + "客户端强制断开");
				 send(name+"客户端强制断开");
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
