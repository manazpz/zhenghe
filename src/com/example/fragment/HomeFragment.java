package com.example.fragment;

import android.R.color;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activeandroid.util.Log;
import com.astuetz.PagerSlidingTabStrip;
import com.example.bing_dictionary.Mydialog;
import com.example.datasave.MyData;
import com.example.datasave.MySharedPreferences;
import com.example.datasave.contsData;
import com.example.hs.CreditsActivity;
import com.example.hs.R;
import com.example.hs.R.drawable;
import com.example.hs.Util;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.bmob.v3.c.V;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HomeFragment extends Fragment implements OnClickListener {

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
	private long price = 100;
	private int timechange = 0;
	private String str;
	private String time[] = { "30秒", "1分钟", "5分钟", "15分钟", "半小时", "1小时" };
	private HashMap<String, Integer> timenum = new HashMap<String, Integer>();
	private TextView tv_time;
	private PagerSlidingTabStrip tabs;
	private ImageView countimg;
	private RelativeLayout higt;
	private RelativeLayout low;
	private int count;
	private int countnum;
	private Animation animation;
	private TextView mMoney;
	private TextView mTitle1;
	private TextView mTitle2;
	private MediaPlayer mediaPlayer;

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			this.inflater = inflater;
			layout = inflater.inflate(R.layout.fragment_home, container, false);
			// 初始化静态UI
			initUI(layout);
			initData();
			
		}
		return layout;
	}

	private void initData() {
		timenum.put(time[0], 30000);
		timenum.put(time[1], 60000);
		timenum.put(time[2], 300000);
		timenum.put(time[3], 900000);
		timenum.put(time[4], 1800000);
		timenum.put(time[5], 3600000);
		
	}

	private void initUI(View layout) {
		initVP(layout);
		initchoice(layout);
		inittime(layout);
		setSlideMenu();
	}

	private void inittime(View layout) {
		countimg = (ImageView) layout.findViewById(R.id.iv_time);
		higt = (RelativeLayout) layout.findViewById(R.id.relativeLayout1);
		higt.setOnClickListener(this);
		low = (RelativeLayout) layout.findViewById(R.id.relativeLayout2);
		low.setOnClickListener(this);
		animation = AnimationUtils.loadAnimation(getContext(), R.anim.count_down_exit);
		
		mtimerl = (RelativeLayout) layout.findViewById(R.id.rl_timerl);
		mstate = (ImageView) layout.findViewById(R.id.iv_state);
		mjishu = (TextView) layout.findViewById(R.id.tv_jishu);
	}

	private void setSlideMenu() {
		// 包含TextView的LinearLayout
		LinearLayout menuLinerLayout = (LinearLayout) layout.findViewById(R.id.linearLayoutMenu);
		menuLinerLayout.setOrientation(LinearLayout.HORIZONTAL);
		// 参数设置
		LinearLayout.LayoutParams menuLinerLayoutParames = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		menuLinerLayoutParames.gravity = Gravity.CENTER_HORIZONTAL;
		// 添加TextView控件
		for (int i = 0; i < 4; i++) {
			TextView tvMenu = new Button(getContext());
			tvMenu.setLayoutParams(new LayoutParams(30, 30));
			tvMenu.setPadding(30, 0, 30, 0);
			tvMenu.setBackgroundColor(Color.DKGRAY);
			tvMenu.setDrawingCacheBackgroundColor(color.holo_red_light);
			tvMenu.setText(Util.TITLESALL[i]);
			tvMenu.setTextColor(Color.WHITE);
			tvMenu.setGravity(Gravity.CENTER_HORIZONTAL);
			menuLinerLayout.addView(tvMenu, menuLinerLayoutParames);
		}

	}

	private void initchoice(View layout) {
		layout_setprice = (RelativeLayout) layout.findViewById(R.id.layout_setprice);
		btn_add = (Button) layout.findViewById(R.id.btn_add);
		btn_minus = (Button) layout.findViewById(R.id.btn_minus);
		btn_timeminus = (Button) layout.findViewById(R.id.btn_timeminus);
		btn_timeadd = (Button) layout.findViewById(R.id.btn_timeadd);
		tv_addprice = (TextView) layout.findViewById(R.id.tv_addprice);
		tv_time = (TextView) layout.findViewById(R.id.tv_time);
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
		public CharSequence getPageTitle(int position) {
			Log.e("getPageTitle", position + "");
			return null;
		}

		@Override
		public Fragment getItem(int position) {
			position %= 3;
			switch (position) {
			case 1:
				return new ChartFragment(position);
			case 2:
				return new MarketFragment(position);
			default:
				break;
			}
			return new AnnouncementFragment(position);
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
		final EditText editText = new EditText(getContext());
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setTitle("投资金额").setIcon(android.R.drawable.ic_dialog_info).setView(editText)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						str = editText.getText().toString();
						Pattern p = Pattern.compile("[0-9]*");
						Matcher m = p.matcher(str);
						if ("".equals(str)) {
							Mydialog.Dialog_ON_OFF(dialog, true);
						} else {
							if (m.matches()) {
								tv_addprice.setText(str);
								price = Long.parseLong(str);
								Mydialog.Dialog_ON_OFF(dialog, true);
							} else {
								editText.setError("请输入数字！");
								Mydialog.Dialog_ON_OFF(dialog, false);
							}
						}
					}
				}).show();
	}
	
	public  void hlDialog(final int flag) {
		View view = inflater.inflate(R.layout.dialog_order, null);
		mMoney = (TextView) view.findViewById(R.id.tv_money);
		mTitle1 = (TextView) view.findViewById(R.id.tv_title1);
		mTitle2 = (TextView) view.findViewById(R.id.tv_title2);
		if (flag == 1) {
			mMoney.setText("投资金额："+tv_addprice.getText().toString());
			mTitle1.setText(tv_time.getText().toString()+"之后与现在的价格相比");
			mTitle2.setText("更高而进行投资");
		}else {
			mMoney.setText("投资金额："+tv_addprice.getText().toString());
			mTitle1.setText(tv_time.getText().toString()+"之后与现在的价格相比");
			mTitle2.setText("更底而进行投资");
		}
		new AlertDialog.Builder(getActivity()).setTitle("投资确认").setView(view)
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			

			@Override
			public void onClick(DialogInterface dialog, int which) {
				countnum = timenum.get(tv_time.getText().toString()).intValue();
				count = 5;
				if (countnum < timenum.get("5分钟")) {
					higt.setClickable(false);
					low.setClickable(false);
					handlern.removeMessages(1);
					handlern.sendEmptyMessageDelayed(0, 100);
					mediaPlayer = MediaPlayer.create(getContext(), R.raw.oneminute);
					mediaPlayer.start();
					if (flag == 1) {
						mtimerl.setVisibility(View.VISIBLE);
						mstate.setImageResource(R.drawable.downdeadtime);
					}else {
						mtimerl.setVisibility(View.VISIBLE);
						mstate.setImageResource(R.drawable.updeadtime);
					}
				}else {
					handlern.removeMessages(1);
					handlern.sendEmptyMessageDelayed(2, 100);
				}
			}
		})
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
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
			long u = Long.parseLong(MySharedPreferences.ReaderPrice(getContext(), "price", "PRICE"));
			add(u);
			break;
		case R.id.btn_minus:
			long d = Long.parseLong(MySharedPreferences.ReaderPrice(getContext(), "price", "PRICE"));
			reduce(d);
			break;
		case R.id.btn_timeminus:
			timereduce();
			break;
		case R.id.btn_timeadd:
			timeadd();
			break;
		case R.id.relativeLayout1:
			hlDialog(1);
			break;
		case R.id.relativeLayout2:
			hlDialog(0);
			break;

		default:
			break;
		}
	}

	private void timereduce() {
		timechange--;
		if (timechange < 0) {
			timechange = 0;
		}
		tv_time.setText(time[timechange]);
	}

	private void timeadd() {
		timechange++;
		if (timechange >= 6) {
			timechange = 5;
		}
		tv_time.setText(time[timechange]);
	}

	private void reduce(long i) {
		if (price - i <= 0) {
			new AlertDialog.Builder(getContext()).setIcon(android.R.drawable.btn_star).setTitle("投注金额不能为0")
					.setPositiveButton("确定", null).show();
		}else {
			price -= i;
		}

		tv_addprice.setText("" + price + "元");
	}

	private void add(long i) {
		price += i;
		if (price >= 1000) {
			tv_addprice.setText(price / 1000 + "," + price%1000 + "元");
		}else {
			tv_addprice.setText("" + price + "元");
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
					countimg.setImageResource(contsData.img[getCount()]);
					
					if (count <= 0) {
						handler.removeMessages(0);
						handler.sendEmptyMessageDelayed(1, 1000);
						countimg.setVisibility(View.GONE);
					}else {
						countimg.setVisibility(View.VISIBLE);
						handler.sendEmptyMessageDelayed(0, 1000);
					}
					small();
			}
		};
	};
	
	Handler handlern = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				mjishu.setText(getcountnum()/1000+","+getcountnum()%1000/100);
				if (countnum <= 0) {
					handlern.removeMessages(0);
					handlern.sendEmptyMessageDelayed(1, 100);
					higt.setClickable(true);
					low.setClickable(true);
					mtimerl.setVisibility(View.GONE);
					countnum = timenum.get(tv_time.getText().toString());
				}else {
					handlern.sendEmptyMessageDelayed(0, 100);
				}
				if (countnum == 5000) {
					handler.removeMessages(1);
					handler.sendEmptyMessageDelayed(0, 1000);
					mediaPlayer.stop();
				}
			}else if (msg.what == 2) {
				getcountnum();
				if (countnum <= 0) {
					handlern.removeMessages(2);
					handlern.sendEmptyMessageDelayed(1, 100);
					countnum = timenum.get(tv_time.getText().toString());
				}else {
					android.util.Log.e("sdf", countnum+"sf");
					handlern.sendEmptyMessageDelayed(2, 100);
				}
			}
		};
	};
	private RelativeLayout mtimerl;
	private ImageView mstate;
	private TextView mjishu;
	
	private int getCount() {
		count--;
		if (count < 0) {
			count = 0;
		}
		return count;
	}
	
	private int getcountnum() {
		countnum -= 50;
		if (countnum < 0) {
			countnum = 0;
		}
		return countnum;
	}
	
	public void small() {
		animation.reset();
		countimg.startAnimation(animation);
	}

}
