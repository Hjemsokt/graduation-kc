package cn.kc.graduation.rss;


import cn.kc.graduation.rss.client.SendMsg;
import cn.kc.graduation.rss.server.MsgServer;

public class TCPTest {
	public static void main(String[] args) throws InterruptedException {
		MsgServer msgServer =  new MsgServer();
		Thread serverThread = new Thread(msgServer);
		serverThread.start();
//		Thread.sleep(5000l);
//		SendMsg sendMsg = new SendMsg();
//		Thread sendThread = new Thread(sendMsg);
//		sendThread.start();
	}
}
