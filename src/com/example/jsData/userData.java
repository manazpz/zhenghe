package com.example.jsData;

import java.io.Serializable;

public class userData implements Serializable{
	private String did;
	private String username;
	private String password;
	private String account;//
	private String price; //用户资金
	private String lastprice;//上次结算后的资金
	private String limit; //信用额度
	private String invested; //占用资金
	private String expendablefund;//可用资金
	private String defeatorvictory;//平仓亏盈
	private String desirableprice ;//可取资金
	private String Landingcode;//登录唯一码
	
	public userData(String did, String username, String password, String account, String price, String lastprice, String limit,
			String invested, String expendablefund, String defeatorvictory, String desirableprice, String landingcode) {
		super();
		this.did = did;
		this.username = username;
		this.password = password;
		this.account = account;
		this.price = price;
		this.lastprice = lastprice;
		this.limit = limit;
		this.invested = invested;
		this.expendablefund = expendablefund;
		this.defeatorvictory = defeatorvictory;
		this.desirableprice = desirableprice;
		this.Landingcode = landingcode;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLastprice() {
		return lastprice;
	}

	public void setLastprice(String lastprice) {
		this.lastprice = lastprice;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getInvested() {
		return invested;
	}

	public void setInvested(String invested) {
		this.invested = invested;
	}

	public String getExpendablefund() {
		return expendablefund;
	}

	public void setExpendablefund(String expendablefund) {
		this.expendablefund = expendablefund;
	}

	public String getDefeatorvictory() {
		return defeatorvictory;
	}

	public void setDefeatorvictory(String defeatorvictory) {
		this.defeatorvictory = defeatorvictory;
	}

	public String getDesirableprice() {
		return desirableprice;
	}

	public void setDesirableprice(String desirableprice) {
		this.desirableprice = desirableprice;
	}

	public String getLandingcode() {
		return Landingcode;
	}

	public void setLandingcode(String landingcode) {
		Landingcode = landingcode;
	}

	
	
}
