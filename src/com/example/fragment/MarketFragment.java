package com.example.fragment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
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
import android.widget.TextView;
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
	private TextView title;
	private TextView execute;
	private TextView buyprice;
	private TextView sellprice;
	private TextView openprice;
	private TextView newprice;
	private TextView updownprice;
	private TextView hprice;
	private TextView lowprice;
	private TextView rangeprice;

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
		title = (TextView) layout.findViewById(R.id.tv_tital);
		execute = (TextView) layout.findViewById(R.id.tv_execute);
		buyprice = (TextView) layout.findViewById(R.id.tv_buyprice);
		sellprice = (TextView) layout.findViewById(R.id.tv_sellprice);
		openprice = (TextView) layout.findViewById(R.id.tv_openprice);
		newprice = (TextView) layout.findViewById(R.id.tv_newpricenum);
		updownprice = (TextView) layout.findViewById(R.id.tv_updownprice);
		hprice = (TextView) layout.findViewById(R.id.tv_topprice);
		lowprice = (TextView) layout.findViewById(R.id.tv_lowprice);
		rangeprice = (TextView) layout.findViewById(R.id.tv_rangeprice);
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
		janScoket.setLoginstr("uclient|" + contsData.codelist.get(0).getCode() + "|" + 0);
		try {
			janScoket.SocketOnline();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sxUI(Struct struct) {
		title.setText(new String(struct.getExchangeID()).trim()+"("+new String(struct.getInstrumentID(), 0, 6)+")");
		execute.setText(struct.getLastPrice()+"");
		newprice.setText(struct.getLastPrice()+"");
		lowprice.setText(struct.getLowestPrice()+"");
		hprice.setText(struct.getHighestPrice()+"");
		DecimalFormat bl6 = new DecimalFormat("000.000000");
		openprice.setText(bl6.format(struct.getOpenPrice())+"");
		double price1 = struct.getLastPrice()-struct.getOpenPrice();
		DecimalFormat bl5 = new DecimalFormat("0.000000");
		updownprice.setText(bl5.format(price1)+"");
		double price2 = price1/struct.getOpenPrice()*100;
		DecimalFormat bl2 = new DecimalFormat("0.00");
		rangeprice.setText(bl2.format(price2)+"%");
		buyprice.setText(struct.getBidPrice1()+"");
		sellprice.setText(struct.getAskPrice1()+"");
	}

	public void gethq(byte[] result) {
		try {
			char[] tradingDay = Myutils.bytesTochar(result, 0, 8);
			char[] instrumentID = Myutils.bytesTochar(result, 9, 31);
			String exchangeID = new String(result, 40, 9, "GBK");
			char[] exchangeInstID = Myutils.bytesTochar(result, 49, 31);
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
			double preDelta = Myutils.ArryToDouble(result, 188);
			double currDelta = Myutils.ArryToDouble(result, 196);

			char[] updateTime = Myutils.bytesTochar(result, 204, 12);
			int updateMillisec = Myutils.bytesToInt(result, 216);
////////////////////////////////////////////////////////////////////////////////
			double bidPrice1 = Myutils.ArryToDouble(result, 220);
			int bidVolume1 = Myutils.bytesToInt(result, 228);
			double askPrice1 = Myutils.ArryToDouble(result, 232);
			int askVolume1 = Myutils.bytesToInt(result, 240);

			double bidPrice2 = Myutils.ArryToDouble(result, 244);
			int bidVolume2 = Myutils.bytesToInt(result, 252);
			double askPrice2 = Myutils.ArryToDouble(result, 256);
			int askVolume2 = Myutils.bytesToInt(result, 264);

			double bidPrice3 = Myutils.ArryToDouble(result, 268);
			int bidVolume3 = Myutils.bytesToInt(result, 276);
			double askPrice3 = Myutils.ArryToDouble(result, 280);
			int askVolume3 = Myutils.bytesToInt(result, 288);

			double bidPrice4 = Myutils.ArryToDouble(result, 292);
			int bidVolume4 = Myutils.bytesToInt(result, 300);
			double askPrice4 = Myutils.ArryToDouble(result, 304);
			int askVolume4 = Myutils.bytesToInt(result, 312);

			double bidPrice5 = Myutils.ArryToDouble(result, 316);
			int bidVolume5 = Myutils.bytesToInt(result, 324);
			double askPrice5 = Myutils.ArryToDouble(result, 328);
			int askVolume5 = Myutils.bytesToInt(result, 336);
			double averagePrice = Myutils.ArryToDouble(result, 340);
			contsData.struct = new Struct(tradingDay, instrumentID, exchangeID, exchangeInstID, 
					lastPrice, preSettlementPrice, preClosePrice, preOpenInterest, openPrice, 
					highestPrice, lowestPrice, volume, turnover, openInterest, closePrice, 
					settlementPrice, upperLimitPrice, lowerLimitPrice, preDelta, currDelta, updateTime, 
					updateMillisec, bidPrice1, bidVolume1, askPrice1, askVolume1, bidPrice2, bidVolume2, 
					askPrice2, askVolume2, bidPrice3, bidVolume3, askPrice3, askVolume3, 
					bidPrice4, bidVolume4, askPrice4, askVolume4, bidPrice5, bidVolume5, 
					askPrice5, askVolume5, averagePrice);
			sxUI(contsData.struct);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
