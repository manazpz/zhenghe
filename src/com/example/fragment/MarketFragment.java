package com.example.fragment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.datasave.Admin;
import com.example.datasave.MyData;
import com.example.datasave.MySharedPreferences;
import com.example.datasave.contsData;
import com.example.fragment.Socket.AnScoket;
import com.example.fragment.Socket.SocketCall;
import com.example.fragment.Socket.SocketCallbyte;
import com.example.fragment.Socket.structScoket;
import com.example.hs.R;
import com.example.hs.R.layout;
import com.example.jsData.Struct;
import com.example.jsData.userData;
import com.example.utils.Myutils;
import com.smorra.asyncsocket.TcpClient;

import android.R.integer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class MarketFragment extends Fragment {

	private View layout;
	private LayoutInflater inflater;
	private int pageNum;
	private structScoket janScoket;
	private ArrayList<byte[]> bb = new ArrayList<>();

	public MarketFragment(int position) {
		this.pageNum = position;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			this.inflater = inflater;
			layout = layout = inflater.inflate(R.layout.item_market, container, false);
			// 初始化静态UI
			initUI();
		}
		bb.clear();
		initData();
		return layout;
	}

	private void initUI() {
	}

	private void initData() {
		String str = contsData.hhost.get(contsData.sername + "h");
		String[] sername = str.split("\\:");
		janScoket = new structScoket(getActivity(), sername[0], Integer.parseInt(sername[1]), new SocketCallbyte() {

			@Override
			public void writeing(Boolean flag) {
			}

			@Override
			public void reading(byte[] result, TcpClient tcpClient) {
				gethq(result);
			}
		});
		Log.e("asd", contsData.codelist.get(1).getCode());
		janScoket.setLoginstr("uclient|" + contsData.codelist.get(0).getCode() + "|" + 0);
		try {
			janScoket.SocketOnline();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void gethq(byte[] result) {
		try {
			String tradingDay = new String(result, 0, 8, "UTF-8");
			String instrumentID = new String(result, 9, 31, "UTF-8");
			String exchangeID = new String(result, 40, 9, "GBK");
			String exchangeInstID = new String(result, 49, 31, "UTF-8");
			double lastPrice = Myutils.ArryToDouble(result, 80);
			double preSettlementPrice = Myutils.ArryToDouble(result, 88);
			double preClosePrice = Myutils.ArryToDouble(result, 96);
			double preOpenInterest = Myutils.ArryToDouble(result, 104);
			double openPrice = Myutils.ArryToDouble(result, 112);
			double highestPrice = Myutils.ArryToDouble(result, 120);
			double lowestPrice = Myutils.ArryToDouble(result, 128);
			int volume = Myutils.bytesToInt(result, 136);
			double turnover = Myutils.ArryToDouble(result, 140);
			double openInterest = Myutils.ArryToDouble(result, 148);
			double closePrice = Myutils.ArryToDouble(result, 156);
			double settlementPrice = Myutils.ArryToDouble(result, 164);
			double upperLimitPrice = Myutils.ArryToDouble(result, 172);
			double lowerLimitPrice = Myutils.ArryToDouble(result, 180);
			double preDelta	 = Myutils.ArryToDouble(result, 188);
			double currDelta	 = Myutils.ArryToDouble(result, 196);
			
			String updateTime = new String(result, 204, 9, "UTF-8");
			int updateMillisec = Myutils.bytesToInt(result, 213);
			
			double bidPrice1	 = Myutils.ArryToDouble(result, 217);
			int bidVolume1 = Myutils.bytesToInt(result, 225);
			double askPrice1	 = Myutils.ArryToDouble(result, 229);
			int askVolume1 = Myutils.bytesToInt(result, 237);
			
			double bidPrice2	 = Myutils.ArryToDouble(result, 241);
			int bidVolume2 = Myutils.bytesToInt(result, 249);
			double askPrice2	 = Myutils.ArryToDouble(result, 253);
			int askVolume2 = Myutils.bytesToInt(result, 261);
			
			double bidPrice3	 = Myutils.ArryToDouble(result, 265);
			int bidVolume3 = Myutils.bytesToInt(result, 273);
			double askPrice3	 = Myutils.ArryToDouble(result, 277);
			int askVolume3 = Myutils.bytesToInt(result, 285);
			
			double bidPrice4 = Myutils.ArryToDouble(result, 289);
			int bidVolume4 = Myutils.bytesToInt(result, 297);
			double askPrice4 = Myutils.ArryToDouble(result, 301);
			int askVolume4 = Myutils.bytesToInt(result, 309);
			
			double bidPrice5	 = Myutils.ArryToDouble(result, 313);
			int bidVolume5 = Myutils.bytesToInt(result, 321);
			double askPrice5 = Myutils.ArryToDouble(result, 325);
			int askVolume5 = Myutils.bytesToInt(result, 333);
			double averagePrice	 = Myutils.ArryToDouble(result, 337);
			
			contsData.struct = new Struct(tradingDay.trim(), instrumentID, 
					exchangeID.trim(), exchangeInstID, lastPrice, preSettlementPrice, 
					preClosePrice, preOpenInterest, openPrice, highestPrice, 
					lowestPrice, volume, turnover, openInterest, closePrice, 
					settlementPrice, upperLimitPrice, lowerLimitPrice, preDelta, 
					currDelta, updateTime, updateMillisec, bidPrice1, bidVolume1, 
					askPrice1, askVolume1, bidPrice2, bidVolume2, askPrice2, 
					askVolume2, bidPrice3, bidVolume3, askPrice3, askVolume3, bidPrice4, 
					bidVolume4, askPrice4, askVolume4, bidPrice5, bidVolume5, 
					askPrice5, askVolume5, averagePrice);
			//Log.e("zzz", contsData.struct.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
