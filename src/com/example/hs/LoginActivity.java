package com.example.hs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.example.fragment.Socket.AnScoket;
import com.example.fragment.Socket.SocketCall;
import com.smorra.asyncsocket.TcpClient;
import com.smorra.asyncsocket.TcpClientCallback;
import com.xinbo.utils.RegexValidateUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{

	private Button btn_login;
	private EditText edt_username;
	private EditText edt_psw;
	private boolean hasUserName;
	private boolean hasPsw;
	private String username;
	private String psw;
	private AnScoket anScoket;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initUI();
	}

	private void initData() {
		anScoket = new AnScoket(LoginActivity.this, "121.41.15.147", 5555, new SocketCall() {
			
			@Override
			public void reading(String result) {
				String[] s = result.split("\\|");
				Log.e("asd", s[s.length-1]);
				if ("密码错误".equals(s[s.length-1]) || "账号错误".equals(s[s.length-1])) {
					com.example.bing_dictionary.Toast.makeText(
							LoginActivity.this, s[s.length-1],
							com.example.bing_dictionary.Toast.LENGTH_LONG).show();
				}else {
					startActivity(new Intent(LoginActivity.this, MainActivity.class));
				}
				
			}
		});
		anScoket.setLoginstr("ulogin|111|"+username+"|"+psw);
		try {
			anScoket.SocketOnline();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void initUI() {
		btn_login = (Button) findViewById(R.id.btn_login);
		edt_username = (EditText) findViewById(R.id.edt_username);
		edt_psw = (EditText) findViewById(R.id.edt_psw);
		btn_login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			login();
			break;

		default:
			break;
		}
	}

	private void login() {
		username = edt_username.getText().toString();
		psw = edt_psw.getText().toString();
		// 判断是否输入
		if ("".equals(username)) {
			edt_username.setError("请输入用户名");
			return;
		}
		if ("".equals(psw)) {
			edt_psw.setError("请输入正确密码");
			return;
		}
		// 校验用户名是否合法
		// 用户名规则：6-16 [a-zA-Z0-9._$%^&#@!]
		initData();
		
	}
}
