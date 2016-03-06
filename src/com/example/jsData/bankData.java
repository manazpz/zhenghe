package com.example.jsData;

import java.io.Serializable;

public class bankData implements Serializable{
	private String cardid;
	private String cardname;
	private String name;
	private String phone;
	private String poundage;
	public bankData(String cardid, String cardname, String name, String phone, String poundage) {
		super();
		this.cardid = cardid;
		this.cardname = cardname;
		this.name = name;
		this.phone = phone;
		this.poundage = poundage;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPoundage() {
		return poundage;
	}
	public void setPoundage(String poundage) {
		this.poundage = poundage;
	}
	
	
	
}
