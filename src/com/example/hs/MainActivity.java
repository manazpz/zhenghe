package com.example.hs;

import com.example.datasave.MyData;
import com.example.fragment.DealFragment;
import com.example.fragment.HoldFragment;
import com.example.fragment.HomeFragment;
import com.example.fragment.SetFragment;
import com.example.jsData.userData;
import com.xinbo.utils.TextViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private FragmentTabHost mTabHost;
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);//将xml视图转换为view视图
		MyData.getInstance().addActivity(this);
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        inflater = getLayoutInflater();
        addTab("HOME", R.drawable.icon_home, HomeFragment.class);
		addTab("持仓", R.drawable.icon_option, HoldFragment.class);
		addTab("成交", R.drawable.icon_deal, DealFragment.class);
		addTab("设置", R.drawable.icon_setting, SetFragment.class);
		Intent intent = getIntent();
		userData data = (userData)intent.getSerializableExtra("userxx");
		new HomeFragment(data);
		new HoldFragment(data);
		new SetFragment(data);
		new DealFragment(data);
	}

	private void addTab(String title, int drawableRes, Class fragmentClass) {
		View tabItem1 = inflater.inflate(R.layout.tab_item, null);
		TextView textView = (TextView) tabItem1.findViewById(R.id.item_name);
		textView.setText(title);
		TextViewUtils.setTextDrawable(this, drawableRes, textView);
		mTabHost.addTab(mTabHost.newTabSpec(title).setIndicator(tabItem1), fragmentClass, null);
	}
}
