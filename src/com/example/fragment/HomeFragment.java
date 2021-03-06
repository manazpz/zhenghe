package com.example.fragment;

import android.R.color;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.astuetz.PagerSlidingTabStrip;
import com.example.bing_dictionary.Mydialog;
import com.example.datasave.Admin;
import com.example.datasave.MyData;
import com.example.datasave.MySharedPreferences;
import com.example.datasave.contsData;
import com.example.fragment.Socket.AnScoket;
import com.example.fragment.Socket.CloseThread;
import com.example.fragment.Socket.SocketCall;
import com.example.hs.CreditsActivity;
import com.example.hs.R;
import com.example.hs.R.drawable;
import com.example.jsData.cjData;
import com.example.jsData.codeData;
import com.example.jsData.userData;
import com.smorra.asyncsocket.TcpClient;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HomeFragment extends Fragment implements OnClickListener {

	private View layout;
	private LayoutInflater inflater;
	private ViewPager vp_type;
	private int BANNER_COUNT = 3;
	private boolean isDrag;
	private RelativeLayout layout_setprice;
	private Button btn_add;
	private Button btn_minus;
	private Button btn_timeminus;
	private Button btn_timeadd;
	private TextView tv_addprice;
	private long price = 100;
	private int timechange = 0;
	private String strprice;
	private String time[] = { "1小时", "半小时", "15分钟", "5分钟", "1分钟", "30秒" };
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
	private userData userdata;
	private RelativeLayout mtimerl;
	private ImageView mstate;
	private TextView mjishu;
	private RelativeLayout layout_show;
	private TextView current_price;
	private TextView hold;
	private TextView profitorlose;
	private TextView balance;
	private AnScoket janScoket;
	private String bql="";
	private ArrayList<cjData> cjlistinfo = new ArrayList<cjData>();
	
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

	private void initWagerData(int choice) {
		
		String str = contsData.jhost.get(contsData.sername + "j");
		String[] sername = str.split("\\:");
		janScoket = new AnScoket(getActivity(), sername[0], Integer.parseInt(sername[1]), new SocketCall() {

			@Override
			public void writeing(Boolean flag) {
			}

			@Override
			public void reading(String result, TcpClient tcpClient) {
				getresult(result, tcpClient);
			}
		});
		janScoket.setLoginstr("<uorder|" + userdata.getDid() + "|"+ userdata.getUsername() + "|"+ app.getCode() +"|"+ price + "|"+ timenum.get(tv_time.getText().toString())/1000 + "|"+ choice +">");
		try {
			janScoket.SocketOnline();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	protected void getresult(String result, TcpClient tcpClient) {

		if (result.length() > 0) {
			//数据连接出错，可参考
			if (">".equals(result.substring(result.length()-1))) {
				if (!"".equals(bql)) {
					result = bql + result;
					bql = "";
				}
				String[] split = result.split(">");
				for (int i = 0; i < split.length; i++) {
					String text1 = new String(Base64.decode(split[i] + ">", Base64.DEFAULT));
					Log.e("tttt", text1);
					String[] str = text1.split("\\|");
					
					switch (str[0]) {
					case "uorder":
							if("0".equals(str[1])){
								tishiDialog(str[2]);
							}else if("1".equals(str[1])){
								Log.e("rrre", "5541");
								tishi1Dialog();
						}
						break;
					default:
						new CloseThread(tcpClient).start();
						break;
					}
				}
			}
		}
	}


	private void initData() {
		timenum.put(time[0], 3600000);
		timenum.put(time[1], 1800000);
		timenum.put(time[2], 900000);
		timenum.put(time[3], 300000);
		timenum.put(time[4], 60000);
		timenum.put(time[5], 30000);
		
		app = (MyData) getActivity().getApplication();
		userdata = app.userdata;
		current_price.setText("当前："+userdata.getPrice());
		hold.setText("持仓："+userdata.getInvested()+"元");
		profitorlose.setText("盈亏："+userdata.getDefeatorvictory()+"元");
		balance.setText("余额："+userdata.getExpendablefund()+"元");
		
	}

	private void initUI(View layout) {
		initVP(layout);
		initchoice(layout);
		inittime(layout);
	}

	private void inittime(View layout) {
		countimg = (ImageView) layout.findViewById(R.id.iv_time);
		higt = (RelativeLayout) layout.findViewById(R.id.relativeLayout1);
		higt.setOnClickListener(this);
		low = (RelativeLayout) layout.findViewById(R.id.relativeLayout2);
		low.setOnClickListener(this);
		animation = AnimationUtils.loadAnimation(getContext(), R.anim.count_down_exit);
		layout_show = (RelativeLayout) layout.findViewById(R.id.layout_show);
		layout_show.setOnClickListener(this);
		tv_profit = (TextView) layout.findViewById(R.id.tv_profit);
		tv_showmsg = (TextView) layout.findViewById(R.id.tv_showmsg);
		img_result = (ImageView) layout.findViewById(R.id.img_result);
		mtimerl = (RelativeLayout) layout.findViewById(R.id.rl_timerl);
		mstate = (ImageView) layout.findViewById(R.id.iv_state);
		mjishu = (TextView) layout.findViewById(R.id.tv_jishu);
		current_price = (TextView) layout.findViewById(R.id.tv_current_price);
		hold = (TextView) layout.findViewById(R.id.tv_hold);
		profitorlose = (TextView) layout.findViewById(R.id.tv_profitorlose);
		balance = (TextView) layout.findViewById(R.id.tv_balance);
		tv_msg = (TextView) layout.findViewById(R.id.tv_msg);
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
		vp_type.setCurrentItem(0);
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
			switch (position) {
			case 1:
				return new MarketFragment();
			case 2:
				return new AnnouncementFragment();
			default:
				break;
			}
			return new ChartFragment();
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
						strprice = editText.getText().toString();
						Pattern p = Pattern.compile("[0-9]*");
						Matcher m = p.matcher(strprice);
						if ("".equals(strprice)) {
							Mydialog.Dialog_ON_OFF(dialog, true);
						} else {
							if (m.matches()) {
								String[] split = userdata.getExpendablefund().split("\\.");
								if (Long.valueOf(strprice).longValue() > Long.valueOf(split[0]).longValue()) {
									yDialog();
								}else {
									tv_addprice.setText(strprice+"元");
									price = Long.parseLong(strprice);
								}
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
					if(countnum == timenum.get("1分钟")){
						mediaPlayer = MediaPlayer.create(getContext(), R.raw.oneminute);
						mediaPlayer.start();
					}
					if(countnum == timenum.get("30秒")){
						mediaPlayer = MediaPlayer.create(getContext(), R.raw.thirtyseconds);
						mediaPlayer.start();
					}
					if (flag == 1) {
						mtimerl.setVisibility(View.VISIBLE);
						mstate.setImageResource(R.drawable.downdeadtime);
					}else {
						mtimerl.setVisibility(View.VISIBLE);
						mstate.setImageResource(R.drawable.updeadtime);
					}
					initWagerData(flag);
				}else {
					initWagerData(flag);
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
	
	public  void yDialog() {
		new AlertDialog.Builder(getActivity()).setTitle("提示").setMessage("余额不足")
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		}).show();
	}
	public  void maxDialog() {
		new AlertDialog.Builder(getActivity()).setTitle("提示").setMessage("投资金额不能大于1000元")
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		}).show();
	}
	public  void tishiDialog(String msg) {
		new AlertDialog.Builder(getActivity()).setTitle("下注失败").setMessage(msg)
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		}).show();
	}
	public  void tishi1Dialog() {
		new AlertDialog.Builder(getActivity()).setTitle("下注成功")
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
			long u = Long.parseLong(MySharedPreferences.ReaderPrice(getContext(), "price", "PRICE"));
			add(u);
			break;
		case R.id.btn_minus:
			long d = Long.parseLong(MySharedPreferences.ReaderPrice(getContext(), "price", "PRICE"));
			reduce(d);
			break;
		case R.id.btn_timeminus:
			timeadd();
			break;
		case R.id.btn_timeadd:
			
			timereduce();
			break;
		case R.id.relativeLayout1:
			if (price > 1000) {
				maxDialog();
			}else {
				hlDialog(1);
				
			}
			break;
		case R.id.relativeLayout2:
			if (price > 1000) {
				maxDialog();
			}else {
				hlDialog(0);
				
			}
			
			break;
		case R.id.layout_show:
			layoutshow();
			break;

		default:
			break;
		}
	}

	private void layoutshow() {
		layout_show.setVisibility(View.GONE);
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
			String[] split = userdata.getExpendablefund().split("\\.");
			long pricemax = Long.valueOf(split[0]).longValue();
			if (price > pricemax) {
				yDialog();
				price  = pricemax;
				tv_addprice.setText(pricemax / 1000 + "," + pricemax%1000 + "元");
			}else {
				tv_addprice.setText(price / 1000 + "," + price%1000 + "元");
			}
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
					cjlistinfo.clear();
					mtimerl.setVisibility(View.GONE);
					layout_show.setVisibility(View.VISIBLE);
					
					countnum = timenum.get(tv_time.getText().toString());
				}else {
					handlern.sendEmptyMessageDelayed(0, 100);
				}
				if (countnum == 6000) {
					handler.removeMessages(1);
					handler.sendEmptyMessageDelayed(0, 1000);
				}
			}else if (msg.what == 2) {
				getcountnum();
				if (countnum <= 0) {
					handlern.removeMessages(2);
					handlern.sendEmptyMessageDelayed(1, 100);
					countnum = timenum.get(tv_time.getText().toString());
				}else {
					handlern.sendEmptyMessageDelayed(2, 100);
				}
			}
		};
	};
	private TextView tv_msg;
	private MyData app;
	private TextView tv_profit;
	private TextView tv_showmsg;
	private ImageView img_result;
	
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
