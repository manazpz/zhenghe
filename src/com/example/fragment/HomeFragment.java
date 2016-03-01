package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.example.hs.AnnouncementFragment;
import com.example.hs.R;
import com.example.hs.R.layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HomeFragment extends Fragment {

	private View layout;
	private LayoutInflater inflater;
	private ViewPager vp_type;
	private int BANNER_COUNT = 3 * 100000;
	private boolean isDrag;

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

}
