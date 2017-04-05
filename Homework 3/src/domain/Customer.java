package domain;

import java.util.*;
public class Customer {
	private String name;
	private String nationalID;
	private String adress;
	private Double phoneNum;
	private ArrayList<Cargo> customerCargoList = new ArrayList<Cargo>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNationalID() {
		return nationalID;
	}
	
	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}
	
	public String getAdress() {
		return adress;
	}
	
	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Double getPhoneNum() {
		return phoneNum;
	}
	
	public void setPhoneNum(Double phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public ArrayList<Cargo> getCostumerCargoList() {
		return customerCargoList;
	}
	
	public void addCargoToCustomer(Cargo cargo){
		customerCargoList.add(cargo);
	}
}
