package com.example.hs;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.datasave.Admin;
import com.example.datasave.MD5;
import com.example.datasave.MyData;
import com.example.datasave.MySharedPreferences;
import com.example.datasave.contsData;
import com.example.fragment.Socket.AnScoket;
import com.example.fragment.Socket.SocketCall;
import com.example.jsData.bankData;
import com.example.jsData.userData;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CreditsActivity extends Activity implements OnClickListener{

	private AnScoket janScoket;
	private EditText et_bink;
	private String result;
	private userData userdata;
	private EditText binkzh;
	private EditText binkname;
	private bankData bankdata;
	private EditText name;
	private EditText phone;
	private EditText txprice;
	private TextView bl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credits);
		MyData.getInstance().addActivity(this);
		init();
		initData();
	}

	private void initData() {
		String str = contsData.jhost.get(contsData.sername + "j");
		String[] sername = str.split("\\:");
		janScoket = new AnScoket(CreditsActivity.this, sername[0], Integer.parseInt(sername[1]), new SocketCall() {

			@Override
			public void writeing(Boolean flag) {
			}

			@Override
			public void reading(String result) {
				if (result.length() > 0) {
					String text = new String(Base64.decode(result, Base64.DEFAULT));
					contsData.bankxx = text.split("\\|");
					bankdata = new bankData(contsData.bankxx[1], contsData.bankxx[2], contsData.bankxx[3], contsData.bankxx[5], contsData.bankxx[6]);
					binkzh.setText(bankdata.getCardname());
					name.setText(bankdata.getName());
					phone.setText(bankdata.getPhone());
					bl.setText("手续费："+bankdata.getPoundage()+"%");
					for (int i = 7; i < contsData.bankxx.length; i++) {
						String[] split = contsData.bankxx[i].split(",");
						if (bankdata.getCardid().equals(split[0])) {
							binkname.setText(split[1]);
						}
					}
				}
			}
		});
		MyData app = (MyData) getApplication();
		userdata = app.userdata;
		janScoket.setLoginstr("<ubankinfo|" + userdata.getDid() + "|"
				+ userdata.getUsername() + ">");
		try {
			janScoket.SocketOnline();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void init() {
		et_bink = (EditText) findViewById(R.id.et_bink);
		findViewById(R.id.rl_back).setOnClickListener(this);
		et_bink.setOnClickListener(this);
		binkname = (EditText) findViewById(R.id.et_bink);
		binkzh = (EditText) findViewById(R.id.et_binkuser);
		name = (EditText) findViewById(R.id.et_name);
		phone = (EditText) findViewById(R.id.et_phone);
		txprice = (EditText) findViewById(R.id.et_price);
		bl = (TextView) findViewById(R.id.textView5);
		findViewById(R.id.bt_revise).setOnClickListener(this);
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
		case R.id.bt_revise:
			if ("".equals(txprice.getText().toString())) {
				txprice.setError("请填写金额!");
			}else {
				Pattern p = Pattern.compile("[0-9]*");
				Matcher m = p.matcher(txprice.getText());
				if (m.matches()) {
					txDialog();
				}else {
					txprice.setError("请填写提现数字金额!");
				}
			}
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
			break;

		default:
			break;
		}
	}
	
	public  void txDialog() {
		View view = getLayoutInflater().inflate(R.layout.dialog_bank, null);
		TextView bankname = (TextView) view.findViewById(R.id.tv_bankname);
		TextView bankzh = (TextView) view.findViewById(R.id.tv_bankzh);
		TextView yhname = (TextView) view.findViewById(R.id.tv_yhname);
		TextView lxphone = (TextView) view.findViewById(R.id.tv_lxphone);
		TextView txpric = (TextView) view.findViewById(R.id.tv_txprice);
		bankname.setText("开户银行："+binkname.getText());
		bankzh.setText("银行账号：："+binkzh.getText());
		yhname.setText("开户姓名："+name.getText());
		lxphone.setText("联系电话："+phone.getText());
		double a1 = Double.parseDouble(txprice.getText().toString());
		double a2 = Double.parseDouble(bankdata.getPoundage());
		final double price = a1 - a1*a2/100;
		txpric.setText("提现金额："+price+"元");
		new AlertDialog.Builder(this).setTitle("提现").setView(view)
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				txrequest(price);
			}
		})
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).show();
	}
	
	public void txrequest(double price) {
		String str = contsData.jhost.get(contsData.sername + "j");
		String[] sername = str.split("\\:");
		janScoket = new AnScoket(CreditsActivity.this, sername[0], Integer.parseInt(sername[1]), new SocketCall() {

			@Override
			public void writeing(Boolean flag) {
			}

			@Override
			public void reading(String result) {
				if (result.length() > 0) {
					String text = new String(Base64.decode(result, Base64.DEFAULT));
					String[] split = text.split("\\|");
					successDialog(split[2]);
				}
			}
		});
		janScoket.setLoginstr("<uuseroutmoney|" + userdata.getDid() + "|"
				+ userdata.getUsername() + "|"+ price +">");
		try {
			janScoket.SocketOnline();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void successDialog(String str) {
		new AlertDialog.Builder(this).setTitle(str).setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		}).show();
	}

}
