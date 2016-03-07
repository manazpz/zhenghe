package com.example.fragment;

import java.io.IOException;
import java.util.ArrayList;
import com.example.datasave.MyData;
import com.example.datasave.contsData;
import com.example.fragment.Socket.Ancontens;
import com.example.fragment.Socket.SocketCall;
import com.example.hs.R;
import com.example.jsData.AnnouncementData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
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
public class AnnouncementFragment extends Fragment {
	private int pageNum;
	private View layout;
	private ListView list_announcement;
	private announcementAdapter adapter;
	private TextView tv_tital;
	private TextView tv_message;
	private TextView tv_time;
	private Ancontens janScoket;
	private Ancontens anScoket;
	private String[] strs;
	private LayoutInflater inflater;
	private ArrayList<AnnouncementData> list = new ArrayList<AnnouncementData>();
	private AnnouncementData data;

	public AnnouncementFragment(int position) {
		this.pageNum = position;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			this.inflater = inflater;
			layout = inflater.inflate(R.layout.item_announcement, container, false);
			contsData.gonggao.clear();
			initUI();
			initData();
			
		}
		return layout;
	}

	private void initData() {
		
		String str1 = contsData.jhost.get(contsData.sername+"j");
		String[] announcement = str1.split("\\:");
		anScoket = new Ancontens(getContext(), announcement[0], Integer.parseInt(announcement[1]), new SocketCall() {

			@Override
			public void writeing(Boolean flag) {
			}
			@Override
			public void reading(String result) {
				if (result.length() > 0) {
					contsData.gonggao.add(result);
					Log.e("asd", contsData.gonggao.size()+"");
					base64();
					adapter.notifyDataSetChanged();
				}
			}
		});
		anScoket.setLoginstr("<uggao|0|0>");
		try {
			anScoket.SocketOnline();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void base64() {
		if(contsData.gonggao.size()==4){
				String[] str = contsData.gonggao.get(0).split(">");
				for (int i = 0; i < str.length; i++) {
					if(i<=1){
						String text = new String(Base64.decode(str[i]+">", Base64.DEFAULT));
						String[] splitmessages = text.split("\\|");
						list.add(new AnnouncementData(splitmessages[2], splitmessages[3], splitmessages[4]));
						MyData app = (MyData)getActivity().getApplication();
						app.Announcementdata = data;
					}
				}
		}
	}

	private void initUI() {
		list_announcement = (ListView) layout.findViewById(R.id.list_announcement);
		adapter = new announcementAdapter();
		list_announcement.setAdapter(adapter);
	}
	
	
	class announcementAdapter extends BaseAdapter{


		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent ) {
			ViewOption holder;
			View view;
			if (convertView == null) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_item, null);
			holder = new ViewOption();
			view.setTag(holder);
			holder.tv_tital = (TextView) view.findViewById(R.id.tv_tital);
			holder.tv_message = (TextView) view.findViewById(R.id.tv_message);
			holder.tv_time = (TextView) view.findViewById(R.id.tv_time);
//			for (int j = 0; j < splitmessages.length; j++) {
//				Log.e("bbbbb", splitmessages[j]);
//				int b = 1;
//				b++;
//				if(splitmessages[j].toString().equals(b)){
//					tv_time.setText(splitmessages[j+1]);
//					tv_tital.setText(splitmessages[j+2]);
//					tv_message.setText(splitmessages[j+3]);
//				}
//			}
			}else {
				view = convertView;
				holder = (ViewOption) convertView.getTag();
			}
			holder.tv_time.setText(list.get(position).getTime());
			holder.tv_tital.setText(list.get(position).getTital());
			holder.tv_message.setText(list.get(position).getMassage());
			return view;
			
			
		}
		
	}
	class ViewOption {
		public TextView tv_time;
		public TextView tv_tital;
		public TextView tv_message;
	}

}
