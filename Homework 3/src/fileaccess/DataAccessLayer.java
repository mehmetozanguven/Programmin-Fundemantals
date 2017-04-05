package fileaccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import domain.*;

public class DataAccessLayer {

	private Scanner file;
	public DataAccessLayer(){
		
	}
	public void readFile(Company company){
		try{
			
			file = new Scanner(new File("vehicles.dat"));
			while(file.hasNextLine()){
				
				String line = file.nextLine();
				Vehicle newVehicle = new Vehicle();
				StringTokenizer stringTokenizer = new StringTokenizer(line, ", ");
				
			while(stringTokenizer.hasMoreTokens()){
				newVehicle.setUniqueID(stringTokenizer.nextToken());
				newVehicle.setDepartureDate(stringTokenizer.nextToken());
				//String x = newVehicle.getDepartureDate();
				newVehicle.setVehicleType(stringTokenizer.nextToken());
				Double limit = Double.parseDouble(stringTokenizer.nextToken());
				newVehicle.setUpperLimitWeight(limit);
				company.addVehicleToTheVehicleList(newVehicle);
				
			}//end second while blok
				
				
			}//end while blok
		}//end try blok
		
		catch(Exception e){
			
			e.getMessage();
		}//end catch blok
	}//end of readFile
	
	public void writeFile(Company company, String orderDate){
		Double price = 0.0;
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(new FileOutputStream("CurrentDate_cargoesInfo.dat"));
			for(Cargo eachCargo : company.getTotalCargos()){
				if(eachCargo.getOrderDate().equals(orderDate)){
					price = price + eachCargo.getPrice();
					outputStream.write(eachCargo.getUniqueID() + ", " + eachCargo.getWeight() + ", " + eachCargo.getPrice()
					+ ", " + eachCargo.getOrderDate() + ", " + eachCargo.getSender().getNationalID() + ", " + 
					eachCargo.getReceiver().getNationalID()+
					", " + eachCargo.getPayCargoCost().getNationalID() +"\n");
				}
				
			}//end for blok
			outputStream.write("Total price = "+ price);
			
		}//end try blok 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}//end catch blok
	}//end of writeFile
	
}
