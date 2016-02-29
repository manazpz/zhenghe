package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.hs.R;
import com.example.hs.R.layout;

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

	public HoldFragment() {
		// Required empty public constructor
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
		ListView mdeal = (ListView) layout.findViewById(R.id.lv_deal);
		DealAdapter dealAdapter = new DealAdapter();
		mdeal.setAdapter(dealAdapter);
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
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
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