package com.eko.task.dao;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

	private int ekoTrxnId;
	private String trackingNumber;
	private String ifscCode;
	private String recipientsAccNo;
	private String cspCell;
	private String depositorCell;
	private BigDecimal txAmount;
	private int destination;
	private int mode;
	private int txStatus;
	private Date txTime;	
	private int refundTrxnId;
	private Date refundTime;
	private String rkbTrackingNumber;
	private String comments;
	
	
	public int getEkotrxnid() {
		return ekoTrxnId;
	}
	public String getTrackingnumber() {
		return trackingNumber;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public String getRecipientsAccNo() {
		return recipientsAccNo;
	}
	public String getCSPCell() {
		return cspCell;
	}
	public String getDepositorCell() {
		return depositorCell;
	}
	public BigDecimal getTxAmount() {
		return txAmount;
	}
	public int getDestination() {
		return destination;
	}
	public int getMode() {
		return mode;
	}
	public int getTxStatus() {
		return txStatus;
	}
	public Date getTxtime() {
		return txTime;
	}
	public int getRefundTrxnId() {
		return refundTrxnId;
	}
	public Date getRefundTime() {
		return refundTime;
	}
	public String getRkbTrackingnumber() {
		return rkbTrackingNumber;
	}
	public String getComments() {
		return comments;
	}
	public void setEkotrxnid(int ekotrxnid) {
		this.ekoTrxnId = ekotrxnid;
	}
	public void setTrackingnumber(String trackingnumber) {
		this.trackingNumber = trackingnumber;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public void setRecipientsAccNo(String recipientsAccNo) {
		recipientsAccNo = recipientsAccNo;
	}
	public void setCSPCell(String cSPCell) {
		cspCell = cSPCell;
	}
	public void setDepositorCell(String depositorCell) {
		depositorCell = depositorCell;
	}
	public void setTxAmount(BigDecimal txAmount) {
		this.txAmount = txAmount;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public void setTxStatus(int txStatus) {
		this.txStatus = txStatus;
	}
	public void setTxtime(Date txtime) {
		this.txTime = txtime;
	}
	public void setRefundTrxnId(int refundTrxnId) {
		refundTrxnId = refundTrxnId;
	}
	public void setRefundTime(Date refundTime) {
		refundTime = refundTime;
	}
	public void setRkbTrackingnumber(String rkbTrackingnumber) {
		rkbTrackingNumber = rkbTrackingnumber;
	}
	public void setComments(String comments) {
		comments = comments;
	}
}
