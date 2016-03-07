package com.example.jsData;

public class upbanben {
	private String banbenid;
	private String banbenweb;
	private String wenaddress;
	public upbanben(String banbenid, String banbenweb, String wenaddress) {
		super();
		this.banbenid = banbenid;
		this.banbenweb = banbenweb;
		this.wenaddress = wenaddress;
	}
	public String getBanbenid() {
		return banbenid;
	}
	public void setBanbenid(String banbenid) {
		this.banbenid = banbenid;
	}
	public String getBanbenweb() {
		return banbenweb;
	}
	public void setBanbenweb(String banbenweb) {
		this.banbenweb = banbenweb;
	}
	public String getWenaddress() {
		return wenaddress;
	}
	public void setWenaddress(String wenaddress) {
		this.wenaddress = wenaddress;
	}
	
	
}
