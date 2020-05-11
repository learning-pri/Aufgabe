package com.eko.task.dao;

public class EkoTransaction {
	
	private int transactionId;
	private String originationAddress;
	private String originationID;
	private String customerId;
	private String customerCellNumber;

	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getOriginationAddress() {
		return originationAddress;
	}
	public String getOriginationID() {
		return originationID;
	}
	public String getCustomerId() {
		return customerId;
	}
	public String getCustomerCellNumber() {
		return customerCellNumber;
	}
	public void setOriginationAddress(String originationAddress) {
		this.originationAddress = originationAddress;
	}
	public void setOriginationID(String originationID) {
		this.originationID = originationID;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public void setCustomerCellNumber(String customerCellNumber) {
		this.customerCellNumber = customerCellNumber;
	}
	@Override
	public String toString() {
		return "EkoTransaction [transactionId=" + transactionId + ", originationAddress=" + originationAddress
				+ ", originationID=" + originationID + ", customerId=" + customerId + ", customerCellNumber="
				+ customerCellNumber + "]";
	}
	
}

