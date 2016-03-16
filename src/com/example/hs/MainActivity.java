package com.example.hs;

import java.io.IOException;
import java.util.ArrayList;

import com.example.datasave.MyData;
import com.example.datasave.contsData;
import com.example.fragment.DealFragment;
import com.example.fragment.HoldFragment;
import com.example.fragment.HomeFragment;
import com.example.fragment.MarketFragment;
import com.example.fragment.SetFragment;
import com.example.fragment.Socket.AnScoket;
import com.example.fragment.Socket.CloseThread;
import com.example.fragment.Socket.SocketCall;
import com.example.jsData.codeData;
import com.example.jsData.userData;
import com.smorra.asyncsocket.TcpClient;
import com.xinbo.utils.TextViewUtils;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements OnItemClickListener{

	private FragmentTabHost mTabHost;
	private LayoutInflater inflater;
	private DrawerLayout drawerLayout;
	private View mMenuDrawer;
	private AnScoket janScoket;
	private AnScoket hyanScoket;
	private userData userdata;
	private ListView mListView;
	private Fragment mFragment = null;
	private ActionBar mActionBar;
	private ActionBarDrawerToggle mDrawerToggle;
	private Toolbar toolbar;
	private TextView tv_msg;
	private MyAdapter adapter;
	private MyData app;
	private String bql;
	private MarketFragment mf = new MarketFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);//将xml视图转换为view视图
		MyData.getInstance().addActivity(this);
		contsData.codelist1.clear();
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
		drawerLayout.setDrawerListener(new DemoDrawerListener());
		mMenuDrawer = findViewById(R.id.menuDrawer);
		initActionbar();
		initDrawerMenu();
		initDrawerToggle();
		initData();
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        inflater = getLayoutInflater();
        addTab("HOME", R.drawable.icon_home, HomeFragment.class);
		addTab("持仓", R.drawable.icon_option, HoldFragment.class);
		addTab("成交", R.drawable.icon_deal, DealFragment.class);
		addTab("设置", R.drawable.icon_setting, SetFragment.class);
	}
	
	private void initDrawerMenu() {
		mListView = (ListView) findViewById(R.id.codelist);
		mListView.setOnItemClickListener(this);
		drawerLayout.setDrawerTitle(GravityCompat.START, getString(R.string.drawer_title));
		adapter = new MyAdapter();
		mListView.setAdapter(adapter);
	}
	
	private void initActionbar() {
		mActionBar = getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setTitle("正合");
		//设置背景颜色
		Drawable bg = getResources().getDrawable(R.drawable.bghangqing);
		mActionBar.setBackgroundDrawable(bg);
	}
	

	private void addTab(String title, int drawableRes, Class fragmentClass) {
		View tabItem1 = inflater.inflate(R.layout.tab_item, null);
		TextView textView = (TextView) tabItem1.findViewById(R.id.item_name);
		textView.setText(title);
		TextViewUtils.setTextDrawable(this, drawableRes, textView);
		mTabHost.addTab(mTabHost.newTabSpec(title).setIndicator(tabItem1), fragmentClass, null);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		app.setCode(contsData.codelist1.get(position).getCode());
		mf.updatalist(contsData.codelist1.get(position).getCode());
		// 关闭侧滑菜单布局
		drawerLayout.closeDrawer(mMenuDrawer);
	}
	
	class MyAdapter extends BaseAdapter{


		@Override
		public int getCount() {
			return contsData.codelist1.size();
		}

		@Override
		public Object getItem(int position) {
			
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View inflate = getLayoutInflater().inflate(R.layout.item_draw, null);
			tv_msg = (TextView) inflate.findViewById(R.id.tv_msg);
			tv_msg.setText(contsData.codelist1.get(position).getCode());
			
			return inflate;
		}
		
	}
	
	private void initData() {
		String str = contsData.jhost.get(contsData.sername + "j");
		String[] sername = str.split("\\:");
		janScoket = new AnScoket(this, sername[0], Integer.parseInt(sername[1]), new SocketCall() {

			@Override
			public void writeing(Boolean flag) {
			}

			@Override
			public void reading(String result, TcpClient tcpClient) {
				getresult(result, tcpClient);
			}
		});
		app = (MyData)getApplication();
		userdata = app.userdata;
		janScoket.setLoginstr("<ugetcodelist|" + userdata.getDid() + "|"+ userdata.getUsername() +">");
		try {
			janScoket.SocketOnline();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	protected void getresult(String result, TcpClient tcpClient) {
		if (result.length() > 0) {
			String[] split = result.split(">");
			String text = new String(Base64.decode(result, Base64.DEFAULT));
			String[] str = text.split("\\|");
			if ("ugetcodelist".equals(str[0])) {
				for (int i = 1; i < str.length; i++) {
					String[] hym = str[i].split(",");
					Log.e("dsds", hym[0]);
					Log.e("dsds", hym[1]);
					contsData.codelist1.add(new codeData(hym[0], hym[1]));
				}
				adapter.notifyDataSetChanged();
				Log.e("ccccc", ""+contsData.codelist1.size());
			}else {
				new CloseThread(tcpClient).start();
			}
		}
	}
	//添加菜单
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			
			if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	        }
			int id = item.getItemId();
			switch (id) {
			case R.id.action_settings:
				return true;

		}
			
			return super.onOptionsItemSelected(item);
		}
	
	/** ----------------DrawerToggle begin------------------------ */
	
	private void initDrawerToggle() {
	mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
	}
	 @Override
	protected void onPostCreate(Bundle savedInstanceState) {
	    super.onPostCreate(savedInstanceState);
	    	//控制图标变动
	    mDrawerToggle.syncState();
	}

	@Override
   public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	
	private class DemoDrawerListener implements DrawerLayout.DrawerListener {
		@Override
		public void onDrawerOpened(View drawerView) {
		mDrawerToggle.onDrawerOpened(drawerView);
//		mActionBar.setTitle("打开");
		
		}

		@Override
		public void onDrawerClosed(View drawerView) {
		mDrawerToggle.onDrawerClosed(drawerView);
//		mActionBar.setTitle("关闭");
		}

		@Override
		public void onDrawerSlide(View drawerView, float slideOffset) {
		mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
		}

		@Override
		public void onDrawerStateChanged(int newState) {
			mDrawerToggle.onDrawerStateChanged(newState);
		}
	
}
/** ----------------DrawerToggle end------------------------ */
	
	
	
	public class Sendmsg {

		private String mcode;
		
		public String getmsg() {
			return mcode;
		}

		public void setmsg(String code) {
			this.mcode = code;
		}

	}
}
