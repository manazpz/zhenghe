package com.example.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import com.example.datasave.Admin;
import com.example.datasave.MD5;
import com.example.datasave.MyData;
import com.example.datasave.MySharedPreferences;
import com.example.datasave.contsData;
import com.example.fragment.Socket.AnScoket;
import com.example.fragment.Socket.CloseThread;
import com.example.fragment.Socket.SocketCall;
import com.example.hs.MainActivity;
import com.example.hs.R;
import com.example.hs.ServiceActivity;
import com.example.hs.R.layout;
import com.example.jsData.AnnouncementData;
import com.example.jsData.cjData;
import com.example.jsData.upbanben;
import com.example.jsData.userData;
import com.smorra.asyncsocket.TcpClient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class DealFragment extends Fragment {
	private LayoutInflater inflater;
	private View layout;
	private AnScoket janScoket;
	private ArrayList<cjData> cjlist = new ArrayList<cjData>();
	private DealAdapter dealAdapter;
	private userData userdata;
	private String bql="";

	public DealFragment() {
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			this.inflater = inflater;
			layout = inflater.inflate(R.layout.fragment_deal, container, false);
			initUI();
		}
		cjlist.clear();
		initData();
		return layout;
	}

	private void initData() {
		String str = contsData.jhost.get(contsData.sername + "j");
		String[] sername = str.split("\\:");
		janScoket = new AnScoket(getActivity(), sername[0], Integer.parseInt(sername[1]), new SocketCall() {

			@Override
			public void writeing(Boolean flag) {
			}

			@Override
			public void reading(String result, TcpClient tcpClient) {
					getresult(result, tcpClient);
			}
		});
		MyData app = (MyData) getActivity().getApplication();
		userdata = app.userdata;
		janScoket.setLoginstr("<ucjlist|" + userdata.getDid() + "|"
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
			//数据连接出错，可参考
			if (">".equals(result.substring(result.length()-1))) {
				if (!"".equals(bql)) {
					result = bql + result;
					bql = "";
				}
				String[] split = result.split(">");
				for (int i = 0; i < split.length; i++) {
					String text1 = new String(Base64.decode(split[i] + ">", Base64.DEFAULT));
					String[] str = text1.split("\\|");
					switch (str[0]) {
					case "cjlist":
						if (!"0".equals(str[1])) {
							cjlist.add(new cjData(str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], 
									str[9], str[10], str[11], str[12], str[13], str[14], str[15], str[16]));
							dealAdapter.notifyDataSetChanged();
						}
						break;
						
					default:
						new CloseThread(tcpClient).start();
						break;
					}
				}
			}else {
				bql += result;
			}
		}
	}
	
	private void initUI() {
		ListView mdeal = (ListView) layout.findViewById(R.id.lv_deal);
		dealAdapter = new DealAdapter(cjlist);
		mdeal.setAdapter(dealAdapter);
		layout.findViewById(R.id.tv_updata).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cjlist.clear();
				initData();
			}
		});
	}

}

class DealAdapter extends BaseAdapter {
	private ArrayList<cjData> list;
	
	public DealAdapter(ArrayList<cjData> list) {
		this.list = list;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Viewdeal holder;
		View view;
		if (convertView == null) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
			holder = new Viewdeal();
			view.setTag(holder);
			holder.realname = (TextView) view.findViewById(R.id.tv_name);
			holder.starttime = (TextView) view.findViewById(R.id.tv_starttime);
			holder.endttime = (TextView) view.findViewById(R.id.tv_endtime);
			holder.tz = (TextView) view.findViewById(R.id.tv_investment);
			holder.zt = (TextView) view.findViewById(R.id.tv_condition);
			holder.result = (TextView) view.findViewById(R.id.tv_result);
			holder.sy = (TextView) view.findViewById(R.id.tv_receipts);
		}else {
			view = convertView;
			holder = (Viewdeal) convertView.getTag();
		}
		holder.realname.setText(list.get(position).getHycode());
		double d1 = Double.parseDouble(list.get(position).getTzprice());
		double d2 = Double.parseDouble(list.get(position).getYlpricate());
		if ("1".equals(list.get(position).getM_up_down())) {
			holder.tz.setTextColor(Color.RED);
			holder.tz.setText("投资：▲涨 ￥"+list.get(position).getTzprice());
			if ( Double.parseDouble(list.get(position).getStartprice())-Double.parseDouble(list.get(position).getEndprice()) > 0) {
				holder.zt.setTextColor(Color.RED);
				holder.zt.setText("状态：▲涨");
				holder.result.setText("结果：损失");
			}else {
				holder.zt.setTextColor(Color.GREEN);
				holder.zt.setText("状态：▼跌");
				holder.result.setText("结果：赢利");
				holder.sy.setText("收益："+d1+d2);
			}
		}else {
			holder.tz.setTextColor(Color.GREEN);
			holder.tz.setText("投资：▼跌 ￥"+list.get(position).getTzprice());
			if ( Double.parseDouble(list.get(position).getStartprice())-Double.parseDouble(list.get(position).getEndprice()) < 0) {
				holder.zt.setTextColor(Color.GREEN);
				holder.zt.setText("状态：▼跌");
				holder.result.setText("结果：损失");
			}else {
				holder.zt.setTextColor(Color.RED);
				holder.zt.setText("状态：▲涨");
				holder.result.setText("结果：赢利");
				holder.sy.setText("收益："+d1+d2);
			}
		}
		holder.starttime.setText("开始："+list.get(position).getStartTime()+"/"+list.get(position).getStartprice());
		holder.endttime.setText("结束："+list.get(position).getEndTime()+"/"+list.get(position).getEndprice());
		return view;
	}
	
	@Override
	public Object getItem(int position) {
		return null;
	}
	@Override
	public long getItemId(int position) {
		return 0;
	}
	
}
class Viewdeal {
	public TextView realname;
	public TextView starttime;
	public TextView endttime;
	public TextView tz;
	public TextView zt;
	public TextView result;
	public TextView sy;
}

