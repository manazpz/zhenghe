package com.example.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.datasave.Admin;
import com.example.datasave.MD5;
import com.example.datasave.MyData;
import com.example.datasave.MySharedPreferences;
import com.example.datasave.contsData;
import com.example.fragment.Socket.AnScoket;
import com.example.fragment.Socket.CloseThread;
import com.example.fragment.Socket.SocketCall;
import com.example.hs.R;
import com.example.hs.R.layout;
import com.example.jsData.cjData;
import com.example.jsData.userData;
import com.smorra.asyncsocket.TcpClient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HoldFragment extends Fragment {
	private LayoutInflater inflater;
	private View layout;
	private AnScoket janScoket;
	private ArrayList<cjData> cclist = new ArrayList<cjData>();
	private OptionAdapter optionAdapter;
	private userData userdata;
	public HoldFragment() {
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			this.inflater = inflater;
			layout = inflater.inflate(R.layout.fragment_hold, container, false);
			initUI();
		}
		cclist.clear();
		initData();
		return layout;
	}

	private void initUI() {
		ListView mdeal = (ListView) layout.findViewById(R.id.lv_hold);
		optionAdapter = new OptionAdapter(cclist);
		mdeal.setAdapter(optionAdapter);
	}
	
	private void initData() {
		String str = contsData.jhost.get(contsData.sername + "j");
		String[] sername = str.split("\\:");
		janScoket = new AnScoket(getActivity(), sername[0], Integer.parseInt(sername[1]), new SocketCall() {

			@Override
			public void writeing(Boolean flag) {
				cclist.clear();
			}

			@Override
			public void reading(String result, TcpClient tcpClient) {
				getresult(result, tcpClient);
			}
		});
		MyData app = (MyData) getActivity().getApplication();
		userdata = app.userdata;
		Admin admin = MySharedPreferences.ReadAdmin(getActivity());
		janScoket.setLoginstr("<ulclist|" + userdata.getDid() + "|"
				+ userdata.getUsername() + ">");
		try {
			janScoket.SocketOnline();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void getresult(String result, TcpClient tcpClient) {
		if (result.length() > 0) {
			String[] split = result.split(">");
			for (int i = 0; i < split.length; i++) {
				String text1 = new String(Base64.decode(split[i] + ">", Base64.DEFAULT));
				String[] str = text1.split("\\|");
				switch (str[0]) {
				case "lclist":
					if (!"0".equals(str[1])) {
						cclist.add(new cjData(str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], 
								str[9], str[10], str[11], str[12], str[13], str[14], str[15], str[16]));
						optionAdapter.notifyDataSetChanged();
					}
					break;
				
				default:
					new CloseThread(tcpClient).start();
					break;
				}
			}
		}

	}

}



class OptionAdapter extends BaseAdapter {
	private ArrayList<cjData> list;
	public OptionAdapter(ArrayList<cjData> list) {
		this.list = list;
	}
	@Override
	public int getCount() {
		return list.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewOption holder;
		View view;
		if (convertView == null) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
			holder = new ViewOption();
			view.setTag(holder);
			holder.ccname = (TextView) view.findViewById(R.id.tv_name);
			holder.ccstarttime = (TextView) view.findViewById(R.id.tv_starttime);
			holder.ccendrttime = (TextView) view.findViewById(R.id.tv_endtime);
			holder.ccinvestment = (TextView) view.findViewById(R.id.tv_investment);
			holder.ccrepay = (TextView) view.findViewById(R.id.tv_repay);
			holder.ccperiod = (TextView) view.findViewById(R.id.tv_period);
		}else {
			view = convertView;
			holder = (ViewOption) convertView.getTag();
		}
		holder.ccname.setText(list.get(position).getHycode());
		holder.ccstarttime.setText("开始："+list.get(position).getStartTime()+"/"+list.get(position).getStartprice());
		holder.ccendrttime.setText("到期："+list.get(position).getEndTime()+"/"+"未结算");
		if ("1".equals(list.get(position).getM_up_down())) {
			holder.ccinvestment.setTextColor(Color.RED);
			holder.ccinvestment.setText("投资：▲涨 ￥"+list.get(position).getTzprice());
		}else {
			holder.ccinvestment.setTextColor(Color.GREEN);
			holder.ccinvestment.setText("投资：▼跌 ￥"+list.get(position).getTzprice());
		}
		holder.ccrepay.setText("回报：￥"+Double.parseDouble(list.get(position).getTzprice())+Double.parseDouble(list.get(position).getYlpricate()));
		String[] sj1 = list.get(position).getStartTime().split(" ");
		String[] sjint1 = sj1[1].split(":");
		String[] sj2 = list.get(position).getEndTime().split(" ");
		String[] sjint2 = sj2[1].split(":");
		holder.ccperiod.setText("周期："+datetime(sjint1[0]+sjint1[1]+sjint1[2], sjint2[0]+sjint2[1]+sjint2[2])+"分钟");
		return view;
	}
	
	public long datetime(String time1, String time2) {
		Calendar c = Calendar.getInstance();  
		Calendar cc = Calendar.getInstance();  
		try {
			c.setTime(new SimpleDateFormat("HHmmss").parse(time1));
			cc.setTime(new SimpleDateFormat("HHmmss").parse(time2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long timeInMillis1 = c.getTimeInMillis(); 
		long timeInMillis2 = cc.getTimeInMillis(); 
		return (timeInMillis2 - timeInMillis1)/1000/60;
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

class ViewOption {
	public TextView ccname;
	public TextView ccinvestment;
	public TextView ccstarttime;
	public TextView ccendrttime;
	public TextView ccrepay;
	public TextView ccperiod;
}