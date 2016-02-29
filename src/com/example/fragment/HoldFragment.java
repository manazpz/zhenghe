package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.hs.R;
import com.example.hs.R.layout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HoldFragment extends Fragment {

	public HoldFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_hold, container, false);
	}

}
