package cn.kc.graduation.rss;

import cn.kc.graduation.rss.client.SendMsg;

public class ClientTest {
	public static void main(String[] args) {
		SendMsg sendMsg = new SendMsg();
		Thread sendThread = new Thread(sendMsg);
		sendThread.start();
	}
}
