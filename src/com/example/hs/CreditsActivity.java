package com.example.hs;

import com.example.datasave.MyData;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CreditsActivity extends Activity implements OnClickListener{


	private EditText et_bink;
	private String result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credits);
		MyData.getInstance().addActivity(this);
		init();
	}

	private void init() {
		et_bink = (EditText) findViewById(R.id.et_bink);
		findViewById(R.id.rl_back).setOnClickListener(this);
		findViewById(R.id.et_bink).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_back:
			finish();
			break;
		case R.id.et_bink:
			startActivityForResult(new Intent(CreditsActivity.this, ChoiceBankActivity.class), 1);
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case 1:
			result = data.getExtras().getString("result");
			et_bink.setText(result);
			Editable text = et_bink.getText();
			et_bink.setSelection(text.length());
			Log.e("7777", ""+result);
			break;

		default:
			break;
		}
	}

}
