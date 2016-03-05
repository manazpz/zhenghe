package com.example.fragment.Socket;

import java.io.IOException;

import com.smorra.asyncsocket.TcpClient;

public class CloseThread extends Thread {
	private TcpClient tcpClient;
	
	public CloseThread(TcpClient tcpClient) {
		super();
		this.tcpClient = tcpClient;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			tcpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
