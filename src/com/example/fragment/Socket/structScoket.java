package com.example.fragment.Socket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import com.smorra.asyncsocket.TcpClient;
import com.smorra.asyncsocket.TcpClientCallback;

import android.content.Context;
import android.util.Log;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

public class structScoket {
	private String host;
	private int port;
	private Context context;
	private String readStr;
	private MyThread myThread;
	private String loginstr;
	private SocketCallbyte sc;
	
	public final static int leng = 348;
	private byte[] buffer = new byte[1024*1000];
	private static int wpos = 0; //写入位置指针
    private static int rpos = 0;//开始读取们置指针
    private static byte[] vdata = new byte[leng];//每个读取区大小

	public structScoket(Context context, String host, int port, SocketCallbyte sc) {
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

	public void SocketOnline() throws IOException {
		new TcpClient(new TcpClientCallback() {

			@Override
			public void onWritten(final TcpClient tcpClient) {
				sc.writeing(true, tcpClient);
			}

			@Override
			public void onRead(TcpClient tcpClient, byte[] readBytes) {
				for (int i = 0; i < readBytes.length; i++) {
					buffer[wpos] = readBytes[i];
					wpos++;
				}
				
				if ((wpos-rpos) >= leng) {
					while (true) {
						for (int j = 0; j < leng; j++) {
							vdata[j] = buffer[rpos];
							rpos++;
						}
						sc.reading(vdata, tcpClient);
						if ((wpos - rpos)<leng) {
							int k = 0;
							for (int i = rpos; i < wpos; i++) {
								buffer[k] = buffer[i];
								k++;
							}
							rpos = 0;
							wpos = k;
							break;
						}
					}
				}
			}

			@Override
			public void onDisconnected(TcpClient tcpClient) {
				com.example.bing_dictionary.Toast
						.makeText(context, "网络断开，请查看网络是否连接？", com.example.bing_dictionary.Toast.LENGTH_LONG).show();
			}

			@Override
			public void onConnectFailed(TcpClient tcpClient) {
				com.example.bing_dictionary.Toast
						.makeText(context, "连接失败,请重连？", com.example.bing_dictionary.Toast.LENGTH_LONG).show();
			}

			@Override
			public void onConnect(TcpClient tcpClient) 
			{
				new MyThread(loginstr, tcpClient).start();
			}
		}, host, port);

	}
}
