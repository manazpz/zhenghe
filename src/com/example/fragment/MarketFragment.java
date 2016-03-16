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
import com.example.fragment.Socket.CloseThread;
import com.example.fragment.Socket.SocketCall;
import com.example.fragment.Socket.SocketCallbyte;
import com.example.fragment.Socket.structScoket;
import com.example.hs.R;
import com.example.hs.R.layout;
import com.example.jsData.HuoQuHq;
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
	private structScoket janScoket;
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
	private HuoQuHq hqhq = new HuoQuHq();
	private int code =0;
	private MyData app;
	public MarketFragment() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			this.inflater = inflater;
			layout = layout = inflater.inflate(R.layout.item_market, container, false);
			// 初始化静态UI
			initUI();
			initData("USDJPY");
		}
		return layout;
	}
	
	public void updatalist(String hycode) {
		initData(hycode);
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

	private void initData(String hycode) {
		String str = contsData.hhost.get(contsData.sername + "h");
		String[] sername = str.split("\\:");
		janScoket = new structScoket(getActivity(), sername[0], Integer.parseInt(sername[1]), new SocketCallbyte() {

			@Override
			public void writeing(Boolean flag) {
			}

			@Override
			public void reading(byte[] result, TcpClient tcpClient) {
				if (result.length>0 ) {
					sxUI(hqhq.gethq(result));
				}
			}
		});
		janScoket.setLoginstr("uclient|" + hycode + "|" + 0);
		try {
			janScoket.SocketOnline();
		} catch (IOException e) {
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
}
