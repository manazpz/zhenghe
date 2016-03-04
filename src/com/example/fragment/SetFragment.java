package com.example.fragment;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.bing_dictionary.Mydialog;
import com.example.bing_dictionary.Toast;
import com.example.datasave.MyData;
import com.example.datasave.MySharedPreferences;
import com.example.hs.ChangePassword_Activity;
import com.example.hs.CreditsActivity;
import com.example.hs.LoginActivity;
import com.example.hs.R;
import com.example.hs.R.layout;
import com.example.zxing.CreatEwm;
import com.google.gson.FieldNamingStrategy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import butterknife.OnCheckedChanged;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class SetFragment extends Fragment implements OnClickListener {
	private LayoutInflater inflater;
	private View layout;
	private RelativeLayout password_on_off;
	private CheckBox password_choice;
	private TextView mprice;
	private String str;
	private EditText mpw;

	public SetFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			this.inflater = inflater;
			layout = inflater.inflate(R.layout.fragment_set, container, false);
			// 初始化静态UI
			initUI();
			initEwm();
		}
		return layout;
	}

	private void initEwm() {
		ImageView mewm = (ImageView) layout.findViewById(R.id.iv_ewm);
		new CreatEwm().createQRImage("http://www.baidu.com", mewm);
	}

	private void initUI() {
		password_on_off = (RelativeLayout) layout.findViewById(R.id.layout_remember_password);
		password_on_off.setOnClickListener(this);
		layout.findViewById(R.id.relativeLayout4).setOnClickListener(this);
		layout.findViewById(R.id.relativeLayout5).setOnClickListener(this);
		layout.findViewById(R.id.relativeLayout2).setOnClickListener(this);
		layout.findViewById(R.id.layout_capital).setOnClickListener(this);
		layout.findViewById(R.id.relativeLayout3).setOnClickListener(this);
		layout.findViewById(R.id.cancel).setOnClickListener(this);
		password_choice = (CheckBox) layout.findViewById(R.id.check_choice);
		mprice = (TextView) layout.findViewById(R.id.price);
		boolean flag = MySharedPreferences.rcheck(getContext(), "check", "CHECK");
		password_choice.setChecked(flag);
		checkListener();
		String price = MySharedPreferences.ReaderPrice(getContext(), "price", "PRICE");
		if (price.length() == 0) {
			MySharedPreferences.WritePrice(getContext(), "price", 100+"", "PRICE");
			mprice.setText(100+"元");
		}else {
			mprice.setText(price+"元");
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_remember_password:
			boolean flag = password_choice.isChecked();
			check_btnb(flag, "check", "CHECK");
			break;
		case R.id.relativeLayout4:
			getActivity().finish();
			break;
		case R.id.relativeLayout5:
			Uri uri = Uri.parse("http://www.baidu.com");
			Intent it = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(it);
			break;
		case R.id.layout_capital:
			dialogPrice();
			break;
		case R.id.relativeLayout2:
			aDialog();
			break;
		case R.id.cancel:
			MyData.getInstance().exit();
			break;
		case R.id.relativeLayout3:
			startActivity(new Intent(getContext(), ChangePassword_Activity.class));
			break;

		default:
			break;
		}
	}

	public void checkListener() {
		password_choice.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				check_btnc(isChecked, "check", "CHECK");
			}
		});
	}

	private void check_btnb(Boolean flag, String key, String filename) {
		if (flag) {
			MySharedPreferences.ClearAdmin(getContext());
			MySharedPreferences.wcheck(getContext(), key, false, filename);
			password_choice.setChecked(false);
		} else {
			MySharedPreferences.wcheck(getContext(), key, true, filename);
			password_choice.setChecked(true);
		}
	}

	private void check_btnc(Boolean flag, String key, String filename) {
		if (!flag) {
			MySharedPreferences.ClearAdmin(getContext());
			MySharedPreferences.wcheck(getContext(), key, false, filename);
		} else {
			MySharedPreferences.wcheck(getContext(), key, true, filename);
			startActivity(new Intent(getContext(), LoginActivity.class));
			getActivity().finish();
		}
	}

	protected void dialogPrice() {
		final EditText editText = new EditText(getContext());
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setTitle("资金修改单位").setIcon(android.R.drawable.ic_dialog_info).setView(editText)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						str = editText.getText().toString();
						Pattern p = Pattern.compile("[0-9]*");
						Matcher m = p.matcher(str);
						if ("".equals(str)) {
							Mydialog.Dialog_ON_OFF(dialog, true);
						} else {
							if (m.matches()) {
								mprice.setText(str+"元");
								MySharedPreferences.WritePrice(getContext(), "price", str, "PRICE");
								Mydialog.Dialog_ON_OFF(dialog, true);
							} else {
								editText.setError("请输入数字！");
								Mydialog.Dialog_ON_OFF(dialog, false);
							}
						}
					}
				}).show();
	}
	
	public  void aDialog() {
		View view = inflater.inflate(R.layout.dialog, null);
		mpw = (EditText) view.findViewById(R.id.etname);
		new AlertDialog.Builder(getActivity()).setTitle("安全认证").setView(view)
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MyData app = (MyData) getActivity().getApplication();
				if (mpw.getText().toString().trim().equals(app.password)) {
					startActivity(new Intent(getContext(), CreditsActivity.class));
					Mydialog.Dialog_ON_OFF(dialog, true);
				}else {
					Mydialog.Dialog_ON_OFF(dialog, false);
					mpw.setError("密码错误!");
				}
			}
		})
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).show();
	}

}
