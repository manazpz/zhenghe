package com.example.jsData;

import java.io.Serializable;

public class AnnouncementData implements Serializable{
	private String time;
	private String tital;
	private String massage;
	
	
	public AnnouncementData(String time, String tital, String massage) {
		super();
		this.time = time;
		this.tital = tital;
		this.massage = massage;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTital() {
		return tital;
	}
	public void setTital(String tital) {
		this.tital = tital;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
}
