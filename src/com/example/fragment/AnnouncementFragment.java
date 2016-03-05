package com.example.fragment;

import com.example.hs.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

	public AnnouncementFragment(int position) {
		this.pageNum = position;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layout = inflater.inflate(R.layout.item_announcement, container, false);
		list_announcement = (ListView) layout.findViewById(R.id.list_announcement);
		adapter = new announcementAdapter();
		list_announcement.setAdapter(adapter);
		return layout;
	}
	
	class announcementAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 0;
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
			View inflate = LayoutInflater.from(getContext()).inflate(R.layout.announcement_item, null);
			tv_tital = (TextView) inflate.findViewById(R.id.tv_tital);
			tv_message = (TextView) inflate.findViewById(R.id.tv_message);
			tv_time = (TextView) inflate.findViewById(R.id.tv_time);
			
			return inflate;
		}
		
	}

}
