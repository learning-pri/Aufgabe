package com.eko.task.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="nefttransaction")
public class NeftTransaction implements Serializable {
	 
	  private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Trxn_Id")
	private int trxnId;
	
	private int ekotrxnid;
	private int destination;
	private int mode;
	private int txStatus;
	private String ifscCode;
	private BigDecimal txAmount;
	private Date txtime;
	
	public int getEkotrxnid() {
		return ekotrxnid;
	}
	public void setEkotrxnid(int ekotrxnid) {
		this.ekotrxnid = ekotrxnid;
	}
	public int getDestination() {
		return destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int getTxStatus() {
		return txStatus;
	}
	public void setTxStatus(int txStatus) {
		this.txStatus = txStatus;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public BigDecimal getTxAmount() {
		return txAmount;
	}
	public void setTxAmount(BigDecimal txAmount) {
		this.txAmount = txAmount;
	}
	public Date getTxtime() {
		return txtime;
	}
	public void setTxtime(Date txtime) {
		this.txtime = txtime;
	}

/*	@Id
	@Column(name = "channelId")
	private int channelId;
	
	@Column(name = "channelname")
	private String channelName;
	
	@Column(name = "imps")
	private int IMPS;
	@Column(name = "neft")
	private int NEFT;
	
	@Column(name = "kyclimit")
	private String KycLimit;
	private String NonKycLimit;
	private int NeftPriority;
	private int ImpsPriority;
	private int  external_neftPriority;
	private int external_impsPriority;
	private double recipientLimit;*/
}
