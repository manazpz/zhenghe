package com.example.datasave;


import com.example.jsData.AnnouncementData;
import com.example.jsData.codeData;
import com.example.jsData.userData;


import java.util.LinkedList;
import java.util.List;
import android.app.Activity;

import android.app.Application;

public class MyData extends Application {
	public String password;
	public userData userdata;
	private String code = "";
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	public AnnouncementData Announcementdata;
	private List<Activity> mList = new LinkedList<Activity>();
	private static MyData instance;

	public MyData() {
	}
	
	
	public synchronized static MyData getInstance() {
		if (null == instance) {
			instance = new MyData();
		}
		return instance;
	}

	// add Activity
	public void addActivity(Activity activity) {
		mList.add(activity);
	}

	public void exit() {
		try {
			for (Activity activity : mList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory(); 
		System.gc();
		}
}

