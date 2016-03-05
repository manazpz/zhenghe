package com.example.hs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.bing_dictionary.PickerView;
import com.example.bing_dictionary.PickerView.onSelectListener;
import com.example.bing_dictionary.Toast;
import com.example.datasave.Admin;
import com.example.datasave.MD5;
import com.example.datasave.MyData;
import com.example.datasave.MySharedPreferences;
import com.example.datasave.contsData;
import com.example.fragment.Socket.AnScoket;
import com.example.fragment.Socket.SocketCall;
import com.example.jsData.userData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 */
public class ServiceActivity extends Activity implements OnClickListener {

	private PickerView service_choice;
	private String[] service = null;
	private Button btn_sure;
	private AnScoket janScoket;
	private userData userdata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);
		MyData.getInstance().addActivity(this);
		initUI();
		initChoiceservice();
	}

	private void initData(String text) {
		String str = contsData.jhost.get(text + "j");
		String[] sername = str.split("\\:");
		janScoket = new AnScoket(ServiceActivity.this, sername[0], Integer.parseInt(sername[1]), new SocketCall() {

			@Override
			public void writeing(Boolean flag) {
			}

			@Override
			public void reading(String result) {
				if (result.length() > 0) {
					String text = new String(Base64.decode(result, Base64.DEFAULT));
					String[] str = text.split("\\|");
					addData(str);
					MyData app = (MyData) getApplication();
					app.userdata = userdata;
					startActivity(new Intent(ServiceActivity.this, MainActivity.class));
				}
			}
		});
		Admin admin = MySharedPreferences.ReadAdmin(ServiceActivity.this);
		janScoket.setLoginstr("<ugetuserinfo|" + admin.getUsername() + "|"
				+ MD5.getmd5(admin.getUsername(), admin.getPassword()) + ">");
		try {
			janScoket.SocketOnline();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initUI() {
		service_choice = (PickerView) findViewById(R.id.service_choice);
		findViewById(R.id.btn_sure).setOnClickListener(this);
		service_choice.setOnSelectListener(new onSelectListener() {

			@Override
			public void onSelect(String text) {
				initData(text);
			}
		});
	}

	public void addData(String[] str) {
		userdata = new userData(str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9], str[10],
				str[11], str[12]);
	}

	private void initChoiceservice() {

		Intent intent = getIntent();
		String[] extra = intent.getStringArrayExtra("servicelist");
		for (int i = 0; i < extra.length; i++) {
			String[] split = extra[i].split("\\,");
			if (i >= 3) {
				contsData.data.add(split[0]);
				contsData.jhost.put(split[0] + "j", split[1] + ":" + split[2]);
				contsData.hhost.put(split[0] + "h", split[3] + ":" + split[4]);
			}
		}
		service_choice.setData(contsData.data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_sure:
			service_choice.gettext();
			break;

		default:
			break;
		}
	}

}
