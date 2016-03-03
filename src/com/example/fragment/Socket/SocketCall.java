package com.example.fragment.Socket;

import com.smorra.asyncsocket.TcpClient;

public interface SocketCall {
	public void reading(String result);
	public void writeing(Boolean flag);
}
