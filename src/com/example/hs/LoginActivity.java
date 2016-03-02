package com.example.hs;

import com.xinbo.utils.RegexValidateUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements OnClickListener{

	private Button btn_login;
	private EditText edt_username;
	private EditText edt_psw;
	private boolean hasUserName;
	private boolean hasPsw;
	private String username;
	private String psw;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initUI();
		initData();
	}

	private void initData() {
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
			startActivity(new Intent(this, MainActivity.class));
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
		if (!RegexValidateUtil.checkCharacter(username) && RegexValidateUtil.checkEmail(username)
				&& RegexValidateUtil.checkMobileNumber(username)) {
			edt_username.setError("用户名不合法");
			return;
		}
			if (!RegexValidateUtil.checkCharacter(psw) && RegexValidateUtil.checkEmail(psw)
					&& RegexValidateUtil.checkMobileNumber(psw)) {
				edt_psw.setError("请输入正确密码");
				return;
			
		}
		
	}
}
