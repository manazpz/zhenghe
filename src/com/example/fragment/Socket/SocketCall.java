package com.example.fragment.Socket;

import com.smorra.asyncsocket.TcpClient;

public interface SocketCall {
	public void reading(String result, TcpClient tcpClient);
	public void writeing(Boolean flag);
}
