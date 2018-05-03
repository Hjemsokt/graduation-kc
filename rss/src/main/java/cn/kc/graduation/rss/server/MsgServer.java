package cn.kc.graduation.rss.server;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MsgServer implements Runnable{
	private final static Logger logger =
			Logger.getLogger(MsgServer.class.getName());
	private static final int nThreads = 2;
	private static final int queueCapacity = 1000;
	private MsgHandler msgHandler = new MsgHandler(queueCapacity);



	public void ServerReceviedByTcp(MsgHandler msgHandler) throws IOException {
		// 声明一个ServerSocket对象
		ServerSocket serverSocket = null;
		while (true) {
			try {
				// 创建一个ServerSocket对象，并让这个Socket在1989端口监听
				serverSocket = new ServerSocket(1989);
				logger.info("server start");
				// 调用ServerSocket的accept()方法，接受客户端所发送的请求，
				// 如果客户端没有发送数据，那么该线程就停滞不继续
				Socket socket = serverSocket.accept();
				// 从Socket当中得到InputStream对象
				System.out.println("one client connected");
				InputStream inputStream = socket.getInputStream();
				byte buffer[] = new byte[1024 * 4];
				int temp = 0;
				// 从InputStream当中读取客户端所发送的数据
				while ((temp = inputStream.read(buffer)) != -1) {
					System.out.println(new String(buffer, 0, temp));
					msgHandler.put(new String(buffer, 0, temp));
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			} finally {
				serverSocket.close();
			}
		}

	}

	@Override
	public void run() {
		for (int i = 0; i < nThreads; i++) {
			new Thread(msgHandler, "msgHandler" + i).start();
		}
		try {
			ServerReceviedByTcp(msgHandler);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
