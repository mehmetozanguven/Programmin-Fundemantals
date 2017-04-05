package fileAccessLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import domain.*;

public class DataAccessLayer {
	private EstateAgent estateAgent;
	private Scanner file;
	
	public DataAccessLayer(EstateAgent estateAgent){
		this.estateAgent = estateAgent;
	}
	
	public void readHouseInfo(String fileName) throws FileNotFoundException{
		file = null;
		try{
			file = new Scanner(new File(fileName));
			
			int skipFirstLine = 0;
			
			while(file.hasNextLine()){
				
				if(skipFirstLine != 0){
					
					String line = file.nextLine();
					StringTokenizer stringTokenizer = new StringTokenizer(line, ",\n");
					
					while(stringTokenizer.hasMoreTokens()){
						House house = new House();
						house.setId(stringTokenizer.nextToken());
						
						Double price = Double.parseDouble(stringTokenizer.nextToken());
						house.setPrice(price);
						
						Double size = Double.parseDouble(stringTokenizer.nextToken());
						house.setSize(size);
						
						int numOfRooms = Integer.parseInt(stringTokenizer.nextToken());
						house.setNumberOfRooms(numOfRooms);
						
						int numOfBathrooms = Integer.parseInt(stringTokenizer.nextToken());
						house.setNumberOfBathrooms(numOfBathrooms);
						
						house.setAirConditionerFeature(stringTokenizer.nextToken());
						estateAgent.addHouse(house);
						
					}
					
				}
				else{
					String line = file.nextLine();
					skipFirstLine++;
				}
			}
		}catch(FileNotFoundException e){
			e.getMessage();
			System.exit(0);
		}
		
			
	}//end method
		
	
	public void writeHouseInfo(String fileName, EstateAgent estateAgent) throws FileNotFoundException{
		PrintWriter outputStream = null;
		try{
			outputStream = new PrintWriter(new FileOutputStream("housing_updated.txt"));
			for(House eachHouse : estateAgent.getHouseList()){
				outputStream.println(eachHouse.getId() + "," + eachHouse.getPrice() + "," + eachHouse.getSize() + "," + 
			eachHouse.getNumberOfRooms() + "," + eachHouse.getNumberOfBathrooms() + "," + eachHouse.GetHasAnAirConditioner());
			}
			outputStream.close();
		}catch(FileNotFoundException e){
			e.getMessage();
			System.exit(0);
		}
	}//end method
	
}//end class
