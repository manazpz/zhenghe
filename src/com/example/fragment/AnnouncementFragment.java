package com.example.fragment;

import java.io.IOException;
import java.util.ArrayList;
import com.example.datasave.MyData;
import com.example.datasave.contsData;
import com.example.fragment.Socket.AnScoket;
import com.example.fragment.Socket.CloseThread;
import com.example.fragment.Socket.SocketCall;
import com.example.hs.R;
import com.example.jsData.AnnouncementData;
import com.example.jsData.upbanben;
import com.smorra.asyncsocket.TcpClient;

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
	private View layout;
	private ListView list_announcement;
	private announcementAdapter adapter;
	private TextView tv_tital;
	private TextView tv_message;
	private TextView tv_time;
	private AnScoket anScoket;
	private String[] strs;
	private LayoutInflater inflater;
	private ArrayList<AnnouncementData> list = new ArrayList<AnnouncementData>();
	private AnnouncementData data;
	private String bql = "";

	public AnnouncementFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			this.inflater = inflater;
			layout = inflater.inflate(R.layout.item_announcement, container, false);
			initUI();
		}
		list.clear();
		contsData.uplist.clear();
		initData();
		return layout;
	}

	private void initData() {
		String str1 = contsData.jhost.get(contsData.sername + "j");
		String[] announcement = str1.split("\\:");
		anScoket = new AnScoket(getContext(), announcement[0], Integer.parseInt(announcement[1]), new SocketCall() {

			@Override
			public void writeing(Boolean flag) {
			}

			@Override
			public void reading(String result, TcpClient tcpClient) {
				getresult(result, tcpClient);
			}
		});
		anScoket.setLoginstr("<uggao|0|0>");
		try {
			anScoket.SocketOnline();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getresult(String result, TcpClient tcpClient) {
		if (result.length() > 0) {
			if (">".equals(result.substring(result.length() - 1))) {
				if (!"".equals(bql)) {
					result = bql + result;
					bql = "";
				}
				String[] split = result.split(">");
				for (int i = 0; i < split.length; i++) {
					String text1 = new String(Base64.decode(split[i] + ">", Base64.DEFAULT));
					String[] splitmessages1 = text1.split("\\|");
					switch (splitmessages1[0]) {
					case "uggao":
						list.add(new AnnouncementData(splitmessages1[2], splitmessages1[3], splitmessages1[4]));
						adapter.notifyDataSetChanged();
						break;
					case "version":
						contsData.uplist.add(new upbanben(splitmessages1[1], splitmessages1[2], splitmessages1[3]));
						break;

					default:
						new CloseThread(tcpClient).start();
						break;
					}
				}
			} else {
				bql += result;
			}
		}
	}

	private void initUI() {
		list_announcement = (ListView) layout.findViewById(R.id.list_announcement);
		adapter = new announcementAdapter();
		list_announcement.setAdapter(adapter);
		
	}
	
	 
	class announcementAdapter extends BaseAdapter {
		
		@Override
		public boolean isEnabled(int position) {
			return false;
		}

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
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewOption holder;
			View view;
			if (convertView == null) {
				view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_item, null);
				holder = new ViewOption();
				view.setTag(holder);
				holder.tv_tital = (TextView) view.findViewById(R.id.tv_tital);
				holder.tv_message = (TextView) view.findViewById(R.id.tv_message);
				holder.tv_time = (TextView) view.findViewById(R.id.tv_time);
			} else {
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
