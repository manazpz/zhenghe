package com.example.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.example.hs.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HomeFragment extends Fragment implements OnClickListener{

	private View layout;
	private LayoutInflater inflater;
	private ViewPager vp_type;
	private int BANNER_COUNT = 3 * 100000;
	private boolean isDrag;
	private RelativeLayout layout_setprice;
	private Button btn_add;
	private Button btn_minus;
	private Button btn_timeminus;
	private Button btn_timeadd;
	private TextView tv_addprice;

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			this.inflater = inflater;
			layout = inflater.inflate(R.layout.fragment_home, container, false);
			// 初始化静态UI
			initUI(layout);
		}
		return layout;
	}

	private void initUI(View layout) {
		initVP(layout);
		initchoice(layout);
	}

	private void initchoice(View layout) {
		layout_setprice = (RelativeLayout) layout.findViewById(R.id.layout_setprice);
		btn_add = (Button) layout.findViewById(R.id.btn_add);
		btn_minus = (Button) layout.findViewById(R.id.btn_minus);
		btn_timeminus = (Button) layout.findViewById(R.id.btn_timeminus);
		btn_timeadd = (Button) layout.findViewById(R.id.btn_timeadd);
		tv_addprice = (TextView) layout.findViewById(R.id.tv_addprice);
		layout_setprice.setOnClickListener(this);
		btn_add.setOnClickListener(this);
		btn_minus.setOnClickListener(this);
		btn_timeminus.setOnClickListener(this);
		btn_timeadd.setOnClickListener(this);
	}

	private void initVP(View layout) {
		vp_type = (ViewPager) layout.findViewById(R.id.vp_type);
		FragmentManager fm = getChildFragmentManager();
		vp_type.setAdapter(new PagerBannerAdapter(fm));
		vp_type.setCurrentItem(BANNER_COUNT / 3);
		vp_type.setOnPageChangeListener(new MyPageChangeListener());
	}
	
	class PagerBannerAdapter extends FragmentPagerAdapter {

		public PagerBannerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			position %= 3;
			switch (position) {
			case 1:
				return new MarketFragment(position);
			case 2:
				return new AnnouncementFragment(position);	
			default:
				break;
			}
			return new ChartFragment(position);
		}

		@Override
		public int getCount() {
			return BANNER_COUNT;
		}

	}
	
	class MyPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			switch (arg0) {
			// 拖拽
			case ViewPager.SCROLL_STATE_DRAGGING:
				isDrag = true;
				break;
			// 空闲
			case ViewPager.SCROLL_STATE_IDLE:
				isDrag = false;
				break;
			// 拖拽松开恢复到空闲
			case ViewPager.SCROLL_STATE_SETTLING:
				isDrag = false;
				break;

			default:
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
		}

	}
	
	protected void dialogprice() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setTitle("投资金额")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setView(new EditText(getContext()))
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		}).show();
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_setprice:
			dialogprice();
			break;
		case R.id.btn_add:
			add();
			break;
		case R.id.btn_minus:
			
			break;
		case R.id.btn_timeminus:
			
			break;
		case R.id.btn_timeadd:
			
			break;
		

		default:
			break;
		}
	}

	private void add() {
		int i =100;
		i++;
		
	}

}
