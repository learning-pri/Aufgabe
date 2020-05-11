package com.eko.task.dao;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NoArgsConstructor;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class AccountStatementMetadata {
	
	private String tx_name;
	private String batch_id;
	private String fee;
	private String channel;
	private String recipient_mobile;
	private String recipient_name;
	private String ifsc;
	private String account;
	private String utrNumber;
	private String merchant_mobile;
	private String merchant_name;
	
	public String getTx_name() {
		return tx_name;
	}
	public String getBatch_id() {
		return batch_id;
	}
	public String getFee() {
		return fee;
	}
	public String getChannel() {
		return channel;
	}
	public String getRecipient_mobile() {
		return recipient_mobile;
	}
	public String getRecipient_name() {
		return recipient_name;
	}
	public String getIfsc() {
		return ifsc;
	}
	public String getAccount() {
		return account;
	}
	public String getUtrNumber() {
		return utrNumber;
	}
	public void setTx_name(String tx_name) {
		this.tx_name = tx_name;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public void setRecipient_mobile(String recipient_mobile) {
		this.recipient_mobile = recipient_mobile;
	}
	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setUtrNumber(String utrNumber) {
		this.utrNumber = utrNumber;
	}
	public String getMerchant_mobile() {
		return merchant_mobile;
	}
	public String getMerchant_name() {
		return merchant_name;
	}
	public void setMerchant_mobile(String merchant_mobile) {
		this.merchant_mobile = merchant_mobile;
	}
	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}
	
	

}
