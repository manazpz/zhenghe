package com.example.jsData;

public class codeData {
	private String code;
	private String codemsg;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodemsg() {
		return codemsg;
	}

	public void setCodemsg(String codemsg) {
		this.codemsg = codemsg;
	}

	public codeData(String code, String codemsg) {
		super();
		this.code = code;
		this.codemsg = codemsg;
	}
}
