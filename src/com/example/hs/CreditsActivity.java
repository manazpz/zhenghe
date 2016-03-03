package com.example.hs;

import com.example.datasave.MyData;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CreditsActivity extends Activity implements OnClickListener{


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credits);
		MyData.getInstance().addActivity(this);
		init();
	}

	private void init() {
		findViewById(R.id.rl_back).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_back:
			finish();
			break;

		default:
			break;
		}
	}

}
