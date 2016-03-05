package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.hs.R;
import com.example.hs.R.layout;
import com.example.jsData.userData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HoldFragment extends Fragment {
	private LayoutInflater inflater;
	private View layout;
	private userData data;

	public HoldFragment(userData data) {
		this.data = data;
	}
	public HoldFragment() {
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			this.inflater = inflater;
			layout = inflater.inflate(R.layout.fragment_hold, container, false);
			initUI();
		}
		return layout;
	}

	private void initUI() {
		ListView mdeal = (ListView) layout.findViewById(R.id.lv_hold);
		OptionAdapter optionAdapter = new OptionAdapter();
		mdeal.setAdapter(optionAdapter);
	}

}


class OptionAdapter extends BaseAdapter {
	
	@Override
	public int getCount() {
		return 5;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewOption holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
			holder = new ViewOption();
			convertView.setTag(holder);
		}
		holder = (ViewOption) convertView.getTag();
		return convertView;
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
	
}