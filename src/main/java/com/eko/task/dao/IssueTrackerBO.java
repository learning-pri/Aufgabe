package com.eko.task.dao;

public class IssueTrackerBO {
	
	private String ticketRefNo;
	private String ekoTrxnId;
	private String issueReason;
	private String updatedAt;
	private String tat;
	
	private String jiraStatus;
	private String resolution;
	
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getJiraStatus() {
		return jiraStatus;
	}
	public void setJiraStatus(String jiraStatus) {
		this.jiraStatus = jiraStatus;
	}
	public String getTicketRefNo() {
		return ticketRefNo;
	}
	public String getEkoTrxnId() {
		return ekoTrxnId;
	}
	public String getIssueReason() {
		return issueReason;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public String getTat() {
		return tat;
	}
	public void setTicketRefNo(String ticketRefNo) {
		this.ticketRefNo = ticketRefNo;
	}
	public void setEkoTrxnId(String ekoTrxnId) {
		this.ekoTrxnId = ekoTrxnId;
	}
	public void setIssueReason(String issueReason) {
		this.issueReason = issueReason;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setTat(String tat) {
		this.tat = tat;
	}
}
