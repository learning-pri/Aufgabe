package com.eko.task.dao;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EkoCspBO  {

	
	private String cellNumber;
	private String name;
	private String dateOfBirth;
	private String panCardNumber;
	private String cspcode;
	
	private String accountType;
	private String activationDate;
	private String status;
	private String balance;
	
	private String distributor;
	private String planName;
	private String email;
	private String shopAddress;
	private String city;
	private String state;
	private String pincode;
	private String circel;
	private String blankCheque;
	
	public String getAccountType() {
		return accountType;
	}
	public String getActivationDate() {
		return activationDate;
	}
	public String getStatus() {
		return status;
	}
	public String getBalance() {
		return balance;
	}
	public String getAreaManager() {
		return areaManager;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public void setAreaManager(String areaManager) {
		this.areaManager = areaManager;
	}
	private String areaManager;
	
	
	
	public String getCspcode() {
		return cspcode;
	}
	public void setCspcode(String cspcode) {
		this.cspcode = cspcode;
	}
	public String getCellNumber() {
		return cellNumber;
	}
	public String getName() {
		return name;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public String getPanCardNumber() {
		return panCardNumber;
	}
	
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}
	public String getDistributor() {
		return distributor;
	}
	public String getPlanName() {
		return planName;
	}
	public String getEmail() {
		return email;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPincode() {
		return pincode;
	}
	public String getCircel() {
		return circel;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public void setCircel(String circel) {
		this.circel = circel;
	}
	public String getBlankCheque() {
		return blankCheque;
	}
	public void setBlankCheque(String blankCheque) {
		this.blankCheque = blankCheque;
	}

}
