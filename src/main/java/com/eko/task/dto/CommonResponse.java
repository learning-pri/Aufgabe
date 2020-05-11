package com.eko.task.dto;

import javax.xml.bind.annotation.XmlRootElement;

public class CommonResponse {
	
	private String message;
	private int statusId;
	private Object data;
	private String responseText = "Aufgabe is helping to get...";

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public Object getData() {
		return data;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
