package com.example.hs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import com.example.datasave.Admin;
import com.example.datasave.MyData;
import com.example.datasave.MySharedPreferences;
import com.example.fragment.Socket.AnScoket;
import com.example.fragment.Socket.SocketCall;
import com.smorra.asyncsocket.TcpClient;
import com.smorra.asyncsocket.TcpClientCallback;
import com.xinbo.utils.RegexValidateUtil;
import android.app.Activity;
import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
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
	private final String host = "121.41.15.147";
	private final int port = 5555;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		MyData.getInstance().addActivity(this);
		initUI();
	}
	

	private void initData() {
		anScoket = new AnScoket(LoginActivity.this, host, port, new SocketCall() {
			
			@Override
			public void reading(String result) {
				String[] s = result.split("\\|");
				if ("密码错误".equals(s[s.length-1]) || "账号错误".equals(s[s.length-1])) {
					com.example.bing_dictionary.Toast.makeText(
							LoginActivity.this, s[s.length-1],
							com.example.bing_dictionary.Toast.LENGTH_LONG).show();
				}else {
					MyData app = (MyData) getApplication();
					app.password = psw;
					Intent intent = new Intent(LoginActivity.this, ServiceActivity.class);
					intent.putExtra("servicelist", s);
					startActivity(intent);
					finish();
				}
				
			}

			@Override
			public void writeing(Boolean flag) {
				if (flag) {
					MySharedPreferences.WriteAdmin(LoginActivity.this, username, psw);
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
		Admin admin = MySharedPreferences.ReadAdmin(LoginActivity.this);
		edt_username.setText(admin.getUsername());
		edt_psw.setText(admin.getPassword());
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
