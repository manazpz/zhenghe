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
public class AnnouncementFragment extends Fragment {
	private int pageNum;
	private View layout;

	public AnnouncementFragment(int position) {
		this.pageNum = position;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layout = inflater.inflate(R.layout.item_announcement, container, false);
		return layout;
	}

}
