package com.example.hs;

import com.example.datasave.MyData;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ChangePassword_Activity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password_);
		MyData.getInstance().addActivity(this);
		init();
	}

	private void init() {
		findViewById(R.id.tv_back).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_back:
			finish();
			break;

		default:
			break;
		}
	}

	
}
