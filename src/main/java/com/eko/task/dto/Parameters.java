package com.eko.task.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class Parameters {

	String bankName;

	String name;

	String gstin;

	String tid;
	
	@JsonProperty("tracking_number")
	String trackingNumber;

	String panNumber;

	String email;

	String mobileNumber;

	Double amount;
	
	@JsonProperty("user_code")
	String userCode;

	@JsonProperty("ifsc_code")
	private String ifscCode;

	private String jira;
	
	public String getTrackingNumber() {
		return trackingNumber;
	}
	
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getJira() {
		return jira;
	}

	public void setJira(String jira) {
		this.jira = jira;
	}

	public String getUserCode() {
		return userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

//	public String getIfsc_code() {
//		return ifsc_code;
//	}
//
//	public void setIfsc_code(String ifsc_code) {
//		this.ifsc_code = ifsc_code;
//	}

	public String getBankName() {

		return bankName;
	}

	public void setBankName(String bankName) {

		this.bankName = bankName;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getGstin() {

		return gstin;
	}

	public void setGstin(String gstin) {

		this.gstin = gstin;
	}

	public String getTid() {

		return tid;
	}

	public void setTid(String tid) {

		this.tid = tid;
	}

	public String getPanNumber() {

		return panNumber;
	}

	public void setPanNumber(String panNumber) {

		this.panNumber = panNumber;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getMobileNumber() {

		return mobileNumber;
	}

	@JsonProperty("mobile_number")
	public void setMobileNumber(String mobileNumber) {

		this.mobileNumber = mobileNumber;
	}

	public Double getAmount() {

		return amount;
	}

	public void setAmount(Double amount) {

		this.amount = amount;
	}

}
