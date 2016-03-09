package com.example.fragment.Socket;

import com.smorra.asyncsocket.TcpClient;

public interface SocketCallbyte {
	public void reading(byte[] b, TcpClient tcpClient);
	public void writeing(Boolean flag);
}
