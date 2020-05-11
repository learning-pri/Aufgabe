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
@Table(name = "nefttransaction")
//@NamedQueries(value = {
//		@NamedQuery(name="getIciciNeftPosting", query= "select IFSCCode,RecipientsAccNo,TxAmount,TxStatus,TxTime,EkoTrxnId,file.filename,neft.FileCreationTime "
//				+ "from nefttransaction neft left join systemcreatedfiledetail file on neft.TrxnFileID=file.fileid "
//				+ "where neft.txstatus not in (1,3,4,7) "
//				+ "and date(Txtime)>='2015-11-25' and Txtime<='2016-06-20 18:00:00' "
//				+ "and destination=1 "
//				+ "and mode=0 "
//				+ "and TxAmount>1 order by TxTime desc") })
public class NeftTransaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TrxnId")
	private int trxnId;

	private int ekotrxnid;
	private int destination;
	private int mode;
	private int txStatus;
	private String ifscCode;
	private BigDecimal txAmount;
	private Date txtime;
	
	
//	@OneToMany(cascade=CascadeType.ALL,mappedBy="trxnFileID")
//	private Set<SystemCreatedFileDetail> employees = new HashSet<SystemCreatedFileDetail>();

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

	@Override
	public String toString() {
		return "NeftTransaction [trxnId=" + trxnId + ", ekotrxnid=" + ekotrxnid + ", destination=" + destination
				+ ", mode=" + mode + ", txStatus=" + txStatus + ", ifscCode=" + ifscCode + ", txAmount=" + txAmount
				+ ", txtime=" + txtime + "]";
	}
}
