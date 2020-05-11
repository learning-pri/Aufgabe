package com.eko.task.dao;

public class BankDownBO {
	
	private String bankName;
	private String stoppedAt;
	private String stoppedTill;
	
	public String getStoppedAt() {
		return stoppedAt;
	}
	public void setStoppedAt(String stoppedAt) {
		this.stoppedAt = stoppedAt;
	}
	public String getBankName() {
		return bankName;
	}
	public String getStoppedTill() {
		return stoppedTill;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public void setStoppedTill(String stoppedTill) {
		this.stoppedTill = stoppedTill;
	}
	
	
	

}
