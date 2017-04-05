package domain;

public class House {

	private String id;
	private Double price;
	private Double size;
	private int numberOfRooms;
	private int numberOfBathrooms;
	private String hasAnAirConditioner;
	
	public House(){
		
	}
	
	public String getId() {
	
		return id;
	
	}
	
	public void setId(String id) {
	
		this.id = id;
	
	}
	
	public Double getPrice() {
	
		return price;
	
	}
	
	public void setPrice(Double price) {
	
		this.price = price;
	
	}
	
	public Double getSize() {
	
		return size;
	
	}
	
	public void setSize(Double size) {
		
		this.size = size;
	
	}
	
	public int getNumberOfRooms() {
		
		return numberOfRooms;
	
	}
	
	public void setNumberOfRooms(int numberOfRooms) {
		
		this.numberOfRooms = numberOfRooms;
	
	}
	
	public int getNumberOfBathrooms() {
	
		return numberOfBathrooms;
	
	}
	
	public void setNumberOfBathrooms(int numberOfBathrooms) {
	
		this.numberOfBathrooms = numberOfBathrooms;
	
	}
	
	public void setAirConditionerFeature(String hasAnAirConditioner) {

		if(hasAnAirConditioner.equalsIgnoreCase("yes")){
			this.hasAnAirConditioner="Yes";
		}
		
		else if(hasAnAirConditioner.equalsIgnoreCase("no")){
			this.hasAnAirConditioner="No";
		}
		
	}
	
	public String GetHasAnAirConditioner() {
	
		return this.hasAnAirConditioner;
		
	}
	
	public String toString(){
		
		if(hasAnAirConditioner == "No")
			
			return ("House id is " + id + " its price is " + price +
					" number of rooms " + numberOfRooms + " number of bathrooms " +
					numberOfBathrooms + " and it has no Air Conditioner");
		
		else{
			
			return ("House id is " + id + " its price is " + price +
					" number of rooms " + numberOfRooms + " number of bathrooms " +
					numberOfBathrooms + " and it has Air Conditioner");
			
		}
	}
	
}