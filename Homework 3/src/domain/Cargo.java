
package domain;


public class Cargo {
	private String uniqueID;
	private String orderDate;
	private Double weight;
	private Double price;
	private SenderCustomer sender;
	private ReceiverCustomer receiver;
	private Customer payCargoCost;
	
		
	public Cargo(){

	}

	public String getUniqueID() {
		return uniqueID;
	}
	
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	
	public Double getWeight() {
		return weight;
	}
	
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	public Customer getPayCargoCost() {
		return payCargoCost;
	}
	
	public void setPayCargoCost(Customer payCargoCost) {
		this.payCargoCost = payCargoCost;
	}
	
	public SenderCustomer getSender() {
		return sender;
	}
	
	public void setSender(SenderCustomer sender) {
		this.sender = sender;
	}
	
	public ReceiverCustomer getReceiver() {
		return receiver;
	}
	
	public void setReceiver(ReceiverCustomer receiver) {
		this.receiver = receiver;
	}
}
