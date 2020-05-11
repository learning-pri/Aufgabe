package com.eko.task.dao;

public class IfsccodeBO {
	
	
	private String bankName;
	private String ifsccode;
	private String branchName;
	private String city;
	private String district;
	private String state;
	
	public String getBankName() {
		return bankName;
	}
	public String getIfsccode() {
		return ifsccode;
	}
	public String getBranchName() {
		return branchName;
	}
	public String getCity() {
		return city;
	}
	public String getDistrict() {
		return district;
	}
	public String getState() {
		return state;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public void setState(String state) {
		this.state = state;
	}
}
