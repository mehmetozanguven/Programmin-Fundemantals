
package domain;

import fileaccess.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Company {
	private static ArrayList<Cargo> totalCargos = new ArrayList<Cargo>();
	private ArrayList<Vehicle> copyListVehicle = new ArrayList<Vehicle>();
	private static ArrayList<Vehicle> totalVehicles = new ArrayList<Vehicle>();
	private static ArrayList<SenderCustomer> listOfSenders = new ArrayList<SenderCustomer>();	
	

	public void addVehicleToTheVehicleList2(Vehicle vehicle){
		copyListVehicle.add(vehicle);
	}
	
	public Company(){
		
	}
	
	public ArrayList<Cargo> getTotalCargos() {
		return totalCargos;
	}

	public ArrayList<Vehicle> getTotalVehicles() {
		return totalVehicles;
	}
	
	public void addVehicleToTheVehicleList(Vehicle vehicle) {
		totalVehicles.add(vehicle);
	}
	
	public void addCargoToTheList(Cargo cargo){
		totalCargos.add(cargo);
	}
	
	public ArrayList<SenderCustomer> getListOfSenders() {
		return listOfSenders;
	}
	
	public void addCustomerToTheListOfSenders(SenderCustomer senderCustomer) {
		
		listOfSenders.add(senderCustomer);
	
	}
	
	public Double calculatedPrice(Vehicle vehicle, Cargo cargo){
		Double price = 0.0;
		
		if(vehicle.getVehicleType() == TransportationType.RAIL)
			
			price = cargo.getWeight() * 2;
		
		else if(vehicle.getVehicleType() == TransportationType.ROAD)
			
			price = cargo.getWeight()*3.5;
		
		else{
			
			price = cargo.getWeight()*5;
		}
		
		return price;
	}
	
	public ArrayList<Vehicle> listAppropiateVehicles(String date, Double weight, String type){
		
		ArrayList<Vehicle> appropriateVehicles = new ArrayList<Vehicle>();
		
		StringTokenizer dateLine;
		StringTokenizer parametersDateLine;
		
		for(Vehicle eachVehicle : totalVehicles){//starting first for loop
			
			dateLine = new StringTokenizer(eachVehicle.getDepartureDate(), "/");
			String stringFormOfDay = dateLine.nextToken();
			String stringFormOfMonth = dateLine.nextToken();
			String stringFormOfYear = dateLine.nextToken();
			
			Integer day = Integer.parseInt(stringFormOfDay);
			Integer month = Integer.parseInt(stringFormOfMonth);
			Integer year = Integer.parseInt(stringFormOfYear);
			
			parametersDateLine = new StringTokenizer(date,"/");
			String stringFormOfParametersDay = parametersDateLine.nextToken();
			String stringFormOfParametersMonth = parametersDateLine.nextToken();
			String stringFormOfParametersYear = parametersDateLine.nextToken();
			
			Integer parametersDay = Integer.parseInt(stringFormOfParametersDay);
			Integer parametersMonth = Integer.parseInt(stringFormOfParametersMonth);
			Integer parametersYear = Integer.parseInt(stringFormOfParametersYear);
			
			if(parametersDay<=day && parametersMonth<=month && parametersYear <= year && eachVehicle.getVehicleType().toString().equals(type)){
				
				appropriateVehicles.add(eachVehicle);
			
			}
			
					for(Vehicle vehicle: appropriateVehicles){//starting second for loop
						
						double CountWeight = 0.0;
						
						for(Cargo cargo:vehicle.getListOfCargo()){
							
							CountWeight += cargo.getWeight();
						
						}
						
						if(CountWeight+weight> vehicle.getUpperLimitWeight()){
							
							appropriateVehicles.remove(vehicle);
						}
						
					}//end second for loop

		}//end first loop
		
		return appropriateVehicles;
		
	}//end method
	
	
	public Boolean isCustomerExists(String name){
		boolean correct = true;
		int check = 0;
		for(SenderCustomer eachSender : listOfSenders){
			
			if(eachSender.getName().equals(name))
				
				correct = true;
				check++;
		}
			if(check == 0 ){
				
				correct = false;
			}
		
		
		return correct;
		
	}
}//end class