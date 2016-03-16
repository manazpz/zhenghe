package com.example.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.datasave.MyData;
import com.example.datasave.contsData;
import com.example.fragment.Socket.SocketCallbyte;
import com.example.fragment.Socket.structScoket;
import com.example.hs.R;
import com.example.hs.R.layout;
import com.example.jsData.HuoQuHq;
import com.example.jsData.Struct;
import com.smorra.asyncsocket.TcpClient;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.entity.OHLCEntity;
import cn.limc.androidcharts.entity.StickEntity;
import cn.limc.androidcharts.view.MAChart;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ChartFragment extends Fragment {

	private int pageNum;
	private View item_chart;
	private View layout;
	private List<OHLCEntity> ohlc;
	private MAChart machart;
	private structScoket janScoket;
	private HuoQuHq hqhq = new HuoQuHq();
	private LayoutInflater inflater;
	private int code;
	private Struct struct;

	public ChartFragment(int position) {
		this.pageNum = position;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			this.inflater = inflater;
			layout = inflater.inflate(R.layout.item_chart, container, false);
			// 初始化静态UI
			initOHLC();
		}
//		initData();
		return layout;
	}
	
//	private void initData() {
//		String str = contsData.hhost.get(contsData.sername + "h");
//		String[] sername = str.split("\\:");
//		janScoket = new structScoket(getActivity(), sername[0], Integer.parseInt(sername[1]), new SocketCallbyte() {
//
//			@Override
//			public void writeing(Boolean flag) {
//			}
//
//			@Override
//			public void reading(byte[] result, TcpClient tcpClient) {
//				String string = hqhq.gethq(result).toString();
//			}
//		});
//		MyData app = (MyData)getActivity().getApplication();
//		code = app.code;
//		janScoket.setLoginstr("uclient|" + contsData.codelist1.get(code).getCode() + "|" + 0);
//		try {
//			janScoket.SocketOnline();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}


//			@Override
//			public void writeing(Boolean flag) {
//			}
//
//			@Override
//			public void reading(byte[] result, TcpClient tcpClient) {
//				struct = hqhq.gethq(result);
//				initMAChart(struct.getLastPrice());
//			}
//		});
//		janScoket.setLoginstr("uclient|" + contsData.codelist.get(0).getCode() + "|" + 0);
//		try {
//			janScoket.SocketOnline();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	
	
	
	private void initMAChart(Double price) {

		machart = (MAChart) layout.findViewById(R.id.machart);
		List<LineEntity> lines = new ArrayList<LineEntity>();

		// 计算日均线
		LineEntity MA1 = new LineEntity();
		MA1.setTitle("MA1");
		MA1.setLineColor(Color.WHITE);
		MA1.setLineData(initMA(5));
		lines.add(MA1);

		List<String> ytitle = new ArrayList<String>();
		ytitle.add((price-0.02)+"");
		ytitle.add((price-0.01)+"");
		ytitle.add(price+"");
		ytitle.add((price+0.01)+"");
		ytitle.add((price+0.02)+"");

		machart.setAxisXColor(Color.LTGRAY);
		machart.setAxisYColor(Color.LTGRAY);
		machart.setBorderColor(Color.LTGRAY);
		machart.setAxisMarginTop(10);
		machart.setAxisMarginLeft(20);
		machart.setAxisYTitles(ytitle);
		machart.setLongtitudeFontSize(10);
		machart.setLongtitudeFontColor(Color.WHITE);
		machart.setLatitudeColor(Color.GRAY);
		machart.setLatitudeFontColor(Color.WHITE);
		machart.setLongitudeColor(Color.GRAY);
		machart.setMaxValue(280);
		machart.setMinValue(240);
		machart.setMaxPointNum(36);
		machart.setDisplayAxisYTitle(true);
		machart.setDisplayLatitude(true);
		machart.setDisplayLongitude(true);

		// 为chart1增加均线
		machart.setLineData(lines);
	}

	private void initOHLC() {
		List<OHLCEntity> ohlc = new ArrayList<OHLCEntity>();

		this.ohlc = new ArrayList<OHLCEntity>();
//		ohlc.add(new OHLCEntity(246, 248, 235, 235, 20110825));
//		ohlc.add(new OHLCEntity(240, 242, 236, 242, 20110824));
//		ohlc.add(new OHLCEntity(236, 240, 235, 240, 20110823));
//		ohlc.add(new OHLCEntity(232, 236, 231, 236, 20110822));
//		ohlc.add(new OHLCEntity(240, 240, 235, 235, 20110819));
//		ohlc.add(new OHLCEntity(240, 241, 239, 240, 20110818));
//		ohlc.add(new OHLCEntity(242, 243, 240, 240, 20110817));
//		ohlc.add(new OHLCEntity(239, 242, 238, 242, 20110816));
//		ohlc.add(new OHLCEntity(239, 240, 238, 239, 20110815));
//		ohlc.add(new OHLCEntity(230, 238, 230, 238, 20110812));
//		ohlc.add(new OHLCEntity(236, 237, 234, 234, 20110811));
//		ohlc.add(new OHLCEntity(226, 233, 223, 232, 20110810));
//		ohlc.add(new OHLCEntity(239, 241, 229, 232, 20110809));
//		ohlc.add(new OHLCEntity(242, 244, 240, 242, 20110808));
//		ohlc.add(new OHLCEntity(248, 249, 247, 248, 20110805));
//		ohlc.add(new OHLCEntity(245, 248, 245, 247, 20110804));
//		ohlc.add(new OHLCEntity(249, 249, 245, 247, 20110803));
		// ohlc.add(new OHLCEntity(249 ,251 ,248 ,250 ,20110802));
		// ohlc.add(new OHLCEntity(250 ,252 ,248 ,250 ,20110801));
		// ohlc.add(new OHLCEntity(250 ,251 ,248 ,250 ,20110729));
		// ohlc.add(new OHLCEntity(249 ,252 ,248 ,252 ,20110728));
		// ohlc.add(new OHLCEntity(248 ,250 ,247 ,250 ,20110727));
		// ohlc.add(new OHLCEntity(256 ,256 ,248 ,248 ,20110726));
		// ohlc.add(new OHLCEntity(257 ,258 ,256 ,257 ,20110725));
		// ohlc.add(new OHLCEntity(259 ,260 ,256 ,256 ,20110722));
		// ohlc.add(new OHLCEntity(261 ,261 ,257 ,259 ,20110721));
		// ohlc.add(new OHLCEntity(260 ,260 ,259 ,259 ,20110720));
		// ohlc.add(new OHLCEntity(262 ,262 ,260 ,261 ,20110719));
		// ohlc.add(new OHLCEntity(260 ,262 ,259 ,262 ,20110718));
		// ohlc.add(new OHLCEntity(259 ,261 ,258 ,261 ,20110715));
		// ohlc.add(new OHLCEntity(255 ,259 ,255 ,259 ,20110714));
		// ohlc.add(new OHLCEntity(258 ,258 ,255 ,255 ,20110713));
		// ohlc.add(new OHLCEntity(258 ,260 ,258 ,260 ,20110712));
		// ohlc.add(new OHLCEntity(259 ,260 ,258 ,259 ,20110711));
		// ohlc.add(new OHLCEntity(261 ,262 ,259 ,259 ,20110708));
		// ohlc.add(new OHLCEntity(261 ,261 ,258 ,261 ,20110707));
		// ohlc.add(new OHLCEntity(261 ,261 ,259 ,261 ,20110706));
		// ohlc.add(new OHLCEntity(257 ,261 ,257 ,261 ,20110705));
		// ohlc.add(new OHLCEntity(256 ,257 ,255 ,255 ,20110704));
		// ohlc.add(new OHLCEntity(253 ,257 ,253 ,256 ,20110701));
		// ohlc.add(new OHLCEntity(255 ,255 ,252 ,252 ,20110630));
		// ohlc.add(new OHLCEntity(256 ,256 ,253 ,255 ,20110629));
		// ohlc.add(new OHLCEntity(254 ,256 ,254 ,255 ,20110628));
		// ohlc.add(new OHLCEntity(247 ,256 ,247 ,254 ,20110627));
		// ohlc.add(new OHLCEntity(244 ,249 ,243 ,248 ,20110624));
		// ohlc.add(new OHLCEntity(244 ,245 ,243 ,244 ,20110623));
		// ohlc.add(new OHLCEntity(242 ,244 ,241 ,244 ,20110622));
		// ohlc.add(new OHLCEntity(243 ,243 ,241 ,242 ,20110621));
		// ohlc.add(new OHLCEntity(246 ,247 ,244 ,244 ,20110620));
		// ohlc.add(new OHLCEntity(248 ,249 ,246 ,246 ,20110617));
		// ohlc.add(new OHLCEntity(251 ,253 ,250 ,250 ,20110616));
		// ohlc.add(new OHLCEntity(249 ,253 ,249 ,253 ,20110615));
		// ohlc.add(new OHLCEntity(248 ,250 ,246 ,250 ,20110614));
		// ohlc.add(new OHLCEntity(249 ,250 ,247 ,250 ,20110613));
		// ohlc.add(new OHLCEntity(254 ,254 ,250 ,250 ,20110610));
		// ohlc.add(new OHLCEntity(254 ,255 ,251 ,255 ,20110609));
		// ohlc.add(new OHLCEntity(252 ,254 ,251 ,254 ,20110608));
		// ohlc.add(new OHLCEntity(250 ,253 ,250 ,252 ,20110607));
		// ohlc.add(new OHLCEntity(251 ,252 ,247 ,250 ,20110603));
		// ohlc.add(new OHLCEntity(253 ,254 ,252 ,254 ,20110602));
		// ohlc.add(new OHLCEntity(250 ,254 ,250 ,254 ,20110601));
		// ohlc.add(new OHLCEntity(250 ,252 ,248 ,250 ,20110531));
		// ohlc.add(new OHLCEntity(253 ,254 ,250 ,251 ,20110530));
		// ohlc.add(new OHLCEntity(255 ,256 ,253 ,253 ,20110527));
		// ohlc.add(new OHLCEntity(256 ,257 ,253 ,254 ,20110526));
		// ohlc.add(new OHLCEntity(256 ,257 ,254 ,256 ,20110525));
		// ohlc.add(new OHLCEntity(265 ,265 ,257 ,257 ,20110524));
		// ohlc.add(new OHLCEntity(265 ,266 ,265 ,265 ,20110523));
		// ohlc.add(new OHLCEntity(267 ,268 ,265 ,266 ,20110520));
		// ohlc.add(new OHLCEntity(264 ,267 ,264 ,267 ,20110519));
		// ohlc.add(new OHLCEntity(264 ,266 ,262 ,265 ,20110518));
		// ohlc.add(new OHLCEntity(266 ,267 ,264 ,264 ,20110517));
		// ohlc.add(new OHLCEntity(264 ,267 ,263 ,267 ,20110516));
		// ohlc.add(new OHLCEntity(266 ,267 ,264 ,264 ,20110513));
		// ohlc.add(new OHLCEntity(269 ,269 ,266 ,268 ,20110512));
		// ohlc.add(new OHLCEntity(267 ,269 ,266 ,269 ,20110511));
		// ohlc.add(new OHLCEntity(266 ,268 ,266 ,267 ,20110510));
		// ohlc.add(new OHLCEntity(264 ,268 ,263 ,266 ,20110509));
		// ohlc.add(new OHLCEntity(265 ,268 ,265 ,267 ,20110506));
		// ohlc.add(new OHLCEntity(271 ,271 ,266 ,266 ,20110505));
		// ohlc.add(new OHLCEntity(271 ,273 ,269 ,273 ,20110504));
		// ohlc.add(new OHLCEntity(268 ,271 ,267 ,271 ,20110503));
		// ohlc.add(new OHLCEntity(273 ,275 ,268 ,268 ,20110429));
		// ohlc.add(new OHLCEntity(274 ,276 ,270 ,272 ,20110428));
		// ohlc.add(new OHLCEntity(275 ,277 ,273 ,273 ,20110427));
		// ohlc.add(new OHLCEntity(280 ,280 ,276 ,276 ,20110426));
		// ohlc.add(new OHLCEntity(282 ,283 ,280 ,281 ,20110425));
		// ohlc.add(new OHLCEntity(282 ,283 ,281 ,282 ,20110422));
		// ohlc.add(new OHLCEntity(280 ,281 ,279 ,280 ,20110421));
		// ohlc.add(new OHLCEntity(283 ,283 ,279 ,279 ,20110420));
		// ohlc.add(new OHLCEntity(284 ,286 ,283 ,285 ,20110419));
		// ohlc.add(new OHLCEntity(283 ,286 ,282 ,285 ,20110418));
		// ohlc.add(new OHLCEntity(285 ,285 ,283 ,284 ,20110415));
		// ohlc.add(new OHLCEntity(280 ,285 ,279 ,285 ,20110414));
		// ohlc.add(new OHLCEntity(281 ,283 ,280 ,282 ,20110413));
		// ohlc.add(new OHLCEntity(283 ,286 ,282 ,282 ,20110412));
		// ohlc.add(new OHLCEntity(280 ,283 ,279 ,283 ,20110411));
		// ohlc.add(new OHLCEntity(280 ,281 ,279 ,280 ,20110408));
		// ohlc.add(new OHLCEntity(276 ,280 ,276 ,280 ,20110407));
		// ohlc.add(new OHLCEntity(273 ,276 ,272 ,276 ,20110406));
		// ohlc.add(new OHLCEntity(275 ,276 ,271 ,272 ,20110404));
		// ohlc.add(new OHLCEntity(275 ,276 ,273 ,275 ,20110401));

		for (int i = ohlc.size(); i > 0; i--) {
			this.ohlc.add(ohlc.get(i - 1));
		}
	}

	private List<Float> initMA(int days) {

		if (days < 2) {
			return null;
		}

		List<Float> MA5Values = new ArrayList<Float>();

		float sum = 0;
		float avg = 0;
		for (int i = 0; i < this.ohlc.size(); i++) {
			float close = (float) ohlc.get(i).getClose();
			if (i < days) {
				sum = sum + close;
				avg = sum / (i + 1f);
			} else {
				sum = sum + close - (float) ohlc.get(i - days).getClose();
				avg = sum / days;
			}
			MA5Values.add(avg);
		}
		return MA5Values;
	}
}
