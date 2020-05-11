package com.eko.task.dao;

public class AepsBO {

	private String customerId;
	private String merchantCode;
	private String cellnumber;
	private String name;
	private String rblAgentId;
	private String deviceId;
	private String terminalId;
	private String status;
	private String createdAt;
	public String getCustomerId() {
		return customerId;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public String getCellnumber() {
		return cellnumber;
	}
	public String getName() {
		return name;
	}
	public String getRblAgentId() {
		return rblAgentId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public String getStatus() {
		return status;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public void setCellnumber(String cellnumber) {
		this.cellnumber = cellnumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRblAgentId(String rblAgentId) {
		this.rblAgentId = rblAgentId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
}
