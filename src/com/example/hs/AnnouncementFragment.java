package com.example.hs;

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

	private View layout;
	private View item_announcement;
	private int pageNum;

	public AnnouncementFragment(int position) {
		this.pageNum = position;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layout = inflater.inflate(R.layout.item_announcement, container, false);
		return layout;
	}

}
