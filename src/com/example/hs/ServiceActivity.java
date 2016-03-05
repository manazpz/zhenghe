package com.example.hs;

import java.util.ArrayList;
import java.util.List;
import com.example.bing_dictionary.PickerView;
import com.example.datasave.MyData;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


/**
 * 
 */
public class ServiceActivity extends Activity implements OnClickListener{

	private PickerView service_choice;
	private String [] service = null;
	private Button btn_sure;
	private List<String> data = new ArrayList<String>();
	private final String host = "121.41.15.147";
	private final int port = 5555;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);
		MyData.getInstance().addActivity(this);
		initUI();
		initChoiceservice();
		initData();

	}

	private void initData() {
		
	}

	private void initUI() {
		service_choice = (PickerView) findViewById(R.id.service_choice);
		findViewById(R.id.btn_sure).setOnClickListener(this);;
	}

	private void initChoiceservice() {
		
		Intent intent = getIntent();
		String[] extra = intent.getStringArrayExtra("servicelist");
		for (int i = 0; i < extra.length; i++) {
			Log.e("text", ""+extra[i]);
		}
		
		for (int i = 0; i < extra.length; i++) {
			String[] split = extra[i].split("\\,");
			if(i>=3){
				data.add(split[0]);
			}
			Log.e("3333", ""+split[0]);
		}
		service_choice.setData(data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_sure:
			startActivity(new Intent(ServiceActivity.this, MainActivity.class));
			break;

		default:
			break;
		}
	}
	

}
