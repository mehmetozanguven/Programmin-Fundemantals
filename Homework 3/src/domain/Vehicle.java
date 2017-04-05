import java.util.*;
public class Vehicle {
	private Double upperLimitWeight;
	private String uniqueID;
	private String departureDate;
	private TransportationType vehicleType;
	private ArrayList<Cargo> listOfCargo = new ArrayList<Cargo>();
	
	public Vehicle(){
		
	}
	
	public void addCargoToTheVehicle(Cargo cargo){

			listOfCargo.add(cargo);
				
	}

	public Double getUpperLimitWeight() {
		return upperLimitWeight;
	}
	
	public void setUpperLimitWeight(Double upperLimitWeight) {
		this.upperLimitWeight = upperLimitWeight;
	}
	
	public String getUniqueID() {
		return uniqueID;
	}
	
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	
	public String getDepartureDate() {
		return departureDate;
	}
	
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	
	public TransportationType getVehicleType() {
		return vehicleType;
	}
	
	public void setVehicleType(String vehicleType) {
		TransportationType type = TransportationType.valueOf(vehicleType);
		this.vehicleType = type;
	}
	
	public ArrayList<Cargo> getListOfCargo() {
		return listOfCargo;
	}
	
	public void setListOfCargo(ArrayList<Cargo> listOfCargo) {
		this.listOfCargo = listOfCargo;
	}
}
