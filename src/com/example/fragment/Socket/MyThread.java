package com.example.fragment.Socket;

import java.io.IOException;

import com.smorra.asyncsocket.TcpClient;

public class MyThread extends Thread {
	private String loginstr;
	private TcpClient tcpClient;
	
	
	public MyThread(String loginstr, TcpClient tcpClient) {
		super();
		this.loginstr = loginstr;
		this.tcpClient = tcpClient;
	}


	@Override
	public void run() {
		super.run();
		try {
			tcpClient.write(loginstr.getBytes("UTF-8"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
