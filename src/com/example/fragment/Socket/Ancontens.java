package com.example.fragment.Socket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.smorra.asyncsocket.TcpClient;
import com.smorra.asyncsocket.TcpClientCallback;

import android.content.Context;
import android.util.Log;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

public class Ancontens {
	private String host;
	private int port;
	private Context context;
	private String readStr;
	private MyThread myThread;
	private String loginstr;
	private SocketCall sc;

	public Ancontens(Context context, String host, int port, SocketCall sc) {
		super();
		this.host = host;
		this.port = port;
		this.context = context;
		this.sc = sc;
	}
	

	public String getReadStr() {
		return readStr;
	}



	public MyThread getMyThread() {
		return myThread;
	}



	public void setLoginstr(String loginstr) {
		this.loginstr = loginstr;
	}



	public void SocketOnline() throws IOException{
		new TcpClient(new TcpClientCallback() {
			
			@Override
			public void onWritten(final TcpClient tcpClient) {
				sc.writeing(true);
			}

			@Override
			public void onRead(TcpClient tcpClient, byte[] readBytes) {
				try {
					readStr = new String(readBytes, "utf-8");
					sc.reading(readStr);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onDisconnected(TcpClient tcpClient) {
				com.example.bing_dictionary.Toast.makeText(
						context, "网络断开，请查看网络是否连接？",
						com.example.bing_dictionary.Toast.LENGTH_LONG).show();
			}

			@Override
			public void onConnectFailed(TcpClient tcpClient) {
				com.example.bing_dictionary.Toast.makeText(
						context, "连接失败,请重连？",
						com.example.bing_dictionary.Toast.LENGTH_LONG).show();
			}

			@Override
			public void onConnect(TcpClient tcpClient) {
			    new MyThread(loginstr, tcpClient).start();
			}
		}, host, port);

	}
}


