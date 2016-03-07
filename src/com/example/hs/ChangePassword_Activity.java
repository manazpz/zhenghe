package com.example.hs;

import java.io.IOException;

import com.a.a.is;
import com.example.datasave.MyData;
import com.example.datasave.contsData;
import com.example.fragment.Socket.AnScoket;
import com.example.fragment.Socket.CloseThread;
import com.example.fragment.Socket.SocketCall;
import com.example.jsData.userData;
import com.smorra.asyncsocket.TcpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword_Activity extends Activity implements OnClickListener {

	private AnScoket anScoket;
	private EditText dt_newpw;
	private EditText et_againpw;
	private Button bt_revise;
	private String newpw;
	private String againpw;
	private userData userdata;
	private AnScoket janScoket;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password_);
		MyData.getInstance().addActivity(this);
		initUI();
	}

	private void initUI() {
		dt_newpw = (EditText) findViewById(R.id.dt_newpw);
		et_againpw = (EditText) findViewById(R.id.et_againpw);
		findViewById(R.id.bt_revise).setOnClickListener(this);
		;
		findViewById(R.id.tv_back).setOnClickListener(this);
	}

	private void initData() {
		String str1 = contsData.jhost.get(contsData.sername + "j");
		String[] changepsw = str1.split("\\:");

		anScoket = new AnScoket(this, changepsw[0], Integer.parseInt(changepsw[1]), new SocketCall() {
			@Override
			public void writeing(Boolean flag) {

			}

			@Override
			public void reading(String result, TcpClient tcpClient) {
				if (result.length() > 0) {
					String text = new String(Base64.decode(result, Base64.DEFAULT));
					contsData.newpsw = text.split("\\|");
				}
			}
		});
		MyData app = (MyData) getApplication();
		userdata = app.userdata;
		anScoket.setLoginstr("<urepwd|" + userdata.getUsername() + "|" + userdata.getPassword() + ">");
		try {
			anScoket.SocketOnline();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_back:
			finish();
			break;
		case R.id.bt_revise:
			changepsw();
			break;

		default:
			break;
		}
	}

	private void changepsw() {
		newpw = dt_newpw.getText().toString();
		againpw = et_againpw.getText().toString();
		// 判断是否输入
		if ("".equals(newpw)) {
			dt_newpw.setError("密码不能为空");
			return;
		}
		if ("".equals(againpw)) {
			et_againpw.setError("密码不能为空");
			return;
		}

		if (newpw.equals(againpw)) {
			initData();
			newpswrequest();
			Log.e("333333", "asasa");
		} else {
			Toast.makeText(this, "输入密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
		}
	}

	private void newpswrequest() {
		String str = contsData.jhost.get(contsData.sername + "j");
		String[] sername = str.split("\\:");
		janScoket = new AnScoket(this, sername[0], Integer.parseInt(sername[1]), new SocketCall() {

			@Override
			public void writeing(Boolean flag) {
			}

			@Override
			public void reading(String result, TcpClient tcpClient) {
				if (result.length() > 0) {
					String text = new String(Base64.decode(result, Base64.DEFAULT));
					String[] split = text.split("\\|");
					successDialog(split[2]);
					Log.e("babab", split[2]);
				}
			}
		});
		janScoket.setLoginstr("<urepwd|1|修改密码结果>");
		try {
			janScoket.SocketOnline();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void successDialog(String str) {
		new AlertDialog.Builder(this).setTitle(str).setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		}).show();
	}
}
