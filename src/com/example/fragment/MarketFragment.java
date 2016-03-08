package com.example.fragment;

import java.io.IOException;

import com.example.datasave.Admin;
import com.example.datasave.MyData;
import com.example.datasave.MySharedPreferences;
import com.example.datasave.contsData;
import com.example.fragment.Socket.AnScoket;
import com.example.fragment.Socket.SocketCall;
import com.example.fragment.Socket.structScoket;
import com.example.hs.R;
import com.example.hs.R.layout;
import com.example.jsData.userData;
import com.smorra.asyncsocket.TcpClient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class MarketFragment extends Fragment {

	private View layout;
	private LayoutInflater inflater;
	private int pageNum;
	private structScoket janScoket;
	

	public MarketFragment(int position) {
		this.pageNum = position;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			this.inflater = inflater;
			layout = layout = inflater.inflate(R.layout.item_market, container, false);
			// 初始化静态UI
			
		}
		initData();
		return layout;
	}

	private void initData() {
		String str = contsData.hhost.get(contsData.sername + "h");
		String[] sername = str.split("\\:");
		janScoket = new structScoket(getActivity(), sername[0], Integer.parseInt(sername[1]), new SocketCall() {

			@Override
			public void writeing(Boolean flag) {
			}

			@Override
			public void reading(String result, TcpClient tcpClient) {
			}
		});
		janScoket.setLoginstr("uclient|" + "USDJPY" + "|"
				+ 0);
//		try {
//			janScoket.SocketOnline();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	

}
