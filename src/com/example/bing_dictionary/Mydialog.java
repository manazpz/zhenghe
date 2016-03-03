package com.example.bing_dictionary;

import java.lang.reflect.Field;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

public class Mydialog {
	public static void Dialog_ON_OFF(DialogInterface dialog, Boolean flag) {
		try {
			Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
			field.setAccessible(true);
			// 将mShowing变量设为false，表示对话框已关闭
			field.set(dialog, flag);
			dialog.dismiss();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
