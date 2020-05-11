package com.eko.task.dao;

public class AccountStatementViewBO {

	private String transactionId;
	private String status;
	private String amount;
	private String transactionTime;
	private String referenceNumber;
	private String pipe;
	private String customerId;
	private String accountId;
	private String jiraId;
	private String refundTransactionId;
	private String runningBalance;
	private String transactionTypeId;
	private String channel;
	private String merchantCell;
	private String merchantName;
	private AccountStatementMetadata metadata;
	
	public String getChannel() {
		return channel;
	}

	public String getMerchantCell() {
		return merchantCell;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void setMerchantCell(String merchantCell) {
		this.merchantCell = merchantCell;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public AccountStatementMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(AccountStatementMetadata metadata) {
		this.metadata = metadata;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getStatus() {
		return status;
	}

	public String getAmount() {
		return amount;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public String getPipe() {
		return pipe;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getJiraId() {
		return jiraId;
	}

	public String getRefundTransactionId() {
		return refundTransactionId;
	}

	public String getRunningBalance() {
		return runningBalance;
	}

	public String getTransactionTypeId() {
		return transactionTypeId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public void setPipe(String pipe) {
		this.pipe = pipe;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}

	public void setRefundTransactionId(String refundTransactionId) {
		this.refundTransactionId = refundTransactionId;
	}

	public void setRunningBalance(String runningBalance) {
		this.runningBalance = runningBalance;
	}

	public void setTransactionTypeId(String transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}
}
