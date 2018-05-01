package cn.kc.graduation.rss.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendMsg implements Runnable {
	protected void connectServerWithTCPSocket() {

		Socket socket;
		try {// 创建一个Socket对象，并指定服务端的IP及端口号
			socket = new Socket("localhost", 1989);
			// 创建一个InputStream用户读取要发送的文件。
//			InputStream inputStream = new FileInputStream("e://a.txt");
			// 获取Socket的OutputStream对象用于发送数据。
			// 创建一个byte类型的buffer字节数组，用于存放读取的本地文件
			byte buffer[] = new byte[4 * 1024];
			int temp = 0;
			// 循环读取文件
			for (int i =100; i < 200l; i++){
				Thread.sleep(10);
				OutputStream outputStream = socket.getOutputStream();

				String msg = new StringBuilder("gzc").append(i)
						.append("^2018").append(i).append("\r\n").toString();
				outputStream.write(msg.getBytes());
				// 发送读取的数据到服务端
				outputStream.flush();
			}


			/** 或创建一个报文，使用BufferedWriter写入,看你的需求 **/
//          String socketData = "[2143213;21343fjks;213]";
//          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
//                  socket.getOutputStream()));
//          writer.write(socketData.replace("\n", " ") + "\n");
//          writer.flush();
			/************************************************/
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		connectServerWithTCPSocket();
	}
}
