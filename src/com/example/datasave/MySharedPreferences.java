package com.example.datasave;



import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MySharedPreferences {
	public static void OnlyWife(Context context, boolean ischeck) {
		SharedPreferences sp = context.getSharedPreferences("ONLYWIFE", 0);
		sp.edit().putBoolean("wifeonly", ischeck).commit();
	}
	public static boolean ReadWife(Context context) {
		SharedPreferences sp = context.getSharedPreferences("ONLYWIFE", 0);
		boolean ischeck = sp.getBoolean("wifeonly", false);
		return ischeck;
	}
	
	public static void wcheck(Context context, String key, boolean value, String filename) {
		SharedPreferences sp = context.getSharedPreferences(filename, 0);
		sp.edit().putBoolean(key, value).commit();
	}
	public static boolean rcheck(Context context, String key, String filename) {
		SharedPreferences sp = context.getSharedPreferences(filename, 0);
		boolean ischeck = sp.getBoolean(key, true);
		return ischeck;
	}
	
	public static void WritePrice(Context context, String key, String value, String filename) {
		SharedPreferences sp = context.getSharedPreferences(filename, 0);
		sp.edit().putString(key, value).commit();
	}
	public static String ReaderPrice(Context context, String key, String filename) {
		SharedPreferences sp = context.getSharedPreferences(filename, 0);
		String str = sp.getString(key, "100");
		return str;
	}
	
	public static void WriteAdmin(Context context, String username, String password) {
		SharedPreferences sp = context.getSharedPreferences("ADMIN", 0);
		Editor edit = sp.edit();
		edit.putString("username", username);
		edit.putString("password", password);
		edit.commit();
	}
	public static void ClearAdmin(Context context) {
		SharedPreferences sp = context.getSharedPreferences("ADMIN", 0);
		Editor edit = sp.edit();
		edit.putString("password", "");
		edit.commit();
	}
	public static Admin ReadAdmin(Context context) {
		SharedPreferences sp = context.getSharedPreferences("ADMIN", 0);
		String user = sp.getString("username", "");
		String psw = sp.getString("password", "");
		return new Admin(user, psw);
	}
	
}
