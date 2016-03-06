package com.example.hs;

import java.util.ArrayList;
import java.util.List;
import com.example.bing_dictionary.PickerView;
import com.example.bing_dictionary.PickerView.onSelectListener;
import com.example.datasave.MyData;
import com.example.datasave.contsData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ChoiceBankActivity extends Activity {

	private PickerView bank_choice;
	private List<String> data = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choice_bank);
		MyData.getInstance().addActivity(this);
		initUI();
		
	}

	private void initUI() {
		bank_choice = (PickerView) findViewById(R.id.bank_choice);
		for (int i = 7; i < contsData.bankxx.length; i++) {
			String[] split = contsData.bankxx[i].split(",");
			data.add(split[1]);
		}
		bank_choice.setData(data);
		bank_choice.setflag(true);
		bank_choice.setOnSelectListener(new onSelectListener(){

			@Override
			public void onSelect(String text){
				//数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra("result", text);
                //设置返回数据
                setResult(1, intent);
                //关闭Activity
                finish();
			}
		});
	}

	
}
