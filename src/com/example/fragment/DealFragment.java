package com.example.fragment;

import android.content.Intent;
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
import com.example.fragment.Socket.SocketCall;
import com.example.hs.MainActivity;
import com.example.hs.R;
import com.example.hs.ServiceActivity;
import com.example.hs.R.layout;
import com.example.jsData.cjData;
import com.example.jsData.userData;

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
public class DealFragment extends Fragment {
	private LayoutInflater inflater;
	private View layout;
	private AnScoket janScoket;
	private ArrayList<String> resultdata = new ArrayList<String>();
	private ArrayList<cjData> cjlist = new ArrayList<cjData>();
	private DealAdapter dealAdapter;

	public DealFragment() {
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			this.inflater = inflater;
			layout = inflater.inflate(R.layout.fragment_deal, container, false);
			initData();
			initUI();
		}
		return layout;
	}

	private void initData() {
		String str = contsData.jhost.get(contsData.sername + "j");
		String[] sername = str.split("\\:");
		janScoket = new AnScoket(getActivity(), sername[0], Integer.parseInt(sername[1]), new SocketCall() {

			@Override
			public void writeing(Boolean flag) {
				resultdata.clear();
			}

			@Override
			public void reading(String result) {
				if (result.length() > 0) {
					resultdata.add(result);
					setdata();
					dealAdapter.notifyDataSetChanged();
				}
			}
		});
		Admin admin = MySharedPreferences.ReadAdmin(getActivity());
		janScoket.setLoginstr("<ucjlist|" + admin.getUsername() + "|"
				+ MD5.getmd5(admin.getUsername(), admin.getPassword()) + ">");
		try {
			janScoket.SocketOnline();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setdata() {
		for (int i = 0; i < resultdata.size(); i++) {
			String text = new String(Base64.decode(resultdata.get(i), Base64.DEFAULT));
			Log.e("sd", text);
			String[] str = text.split("\\|");
			cjlist.add(new cjData(str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], 
					str[9], str[10], str[11], str[12], str[13], str[14], str[15], str[16]));
		}
		
	}


	private void initUI() {
		ListView mdeal = (ListView) layout.findViewById(R.id.lv_deal);
		dealAdapter = new DealAdapter(cjlist);
		mdeal.setAdapter(dealAdapter);
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
		holder.realname.setText(list.get(position).getRealname());
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

