package com.example.fragment;

import com.example.hs.R;
import com.example.hs.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class MarketFragment extends Fragment {

	private View layout;
	private View item_market;
	private int pageNum;

	public MarketFragment(int position) {
		this.pageNum = position;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layout = inflater.inflate(R.layout.item_market, container, false);
		return layout;
	}

}
