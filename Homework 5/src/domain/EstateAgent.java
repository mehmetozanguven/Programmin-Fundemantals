package domain;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import fileAccessLayer.*;

public class EstateAgent {
	private ArrayList<House> houseList;
	private DataAccessLayer dal;
	
	public EstateAgent() throws FileNotFoundException{

		houseList = new ArrayList<House>();
		dal = new DataAccessLayer(this);
		dal.readHouseInfo("housing.txt");
		
	}
	
	public DataAccessLayer getDal(){
		
		return dal;
	
	}
	
	public House findHouseWithId(String id){
		
		House check = null;
		
		for(House eachHouse : houseList){

			if(eachHouse.getId().equals(id)){
				
				check = eachHouse;
				break;
			
			}
		}
		
		return check;
	}
	
	public ArrayList<House> getHouseList(){
		
		ArrayList<House> copyHouseList = new ArrayList<House>(houseList);
		
		return copyHouseList;
		
	}
	
	public void setHouseList(ArrayList<House> houseList) {
		
		this.houseList = houseList;
		
	}

	public void addHouse(House house){
		
		houseList.add(house);
		
	}
	
	public void removeHouse(House house){
		
		houseList.remove(house);
	
	}
	
	public ArrayList<House> searchByPrice(Double minPrice, Double maxPrice){
		
		ArrayList<House> houseListByPrice = new ArrayList<House>();
		
		for(House eachHouse:houseList){
			if(minPrice <= eachHouse.getPrice() && eachHouse.getPrice() <= maxPrice){
				houseListByPrice.add(eachHouse);
			}
		}
		
		return houseListByPrice;
	}
	
	public ArrayList<House> searchByNumberOfRooms(int numOfRooms){
		
		ArrayList<House> houseListByNumOfRooms = new ArrayList<House>();

		for(House eachHouse:houseList){
			if(eachHouse.getNumberOfRooms() == numOfRooms){
				houseListByNumOfRooms.add(eachHouse);
			}
		}
		
		return houseListByNumOfRooms;
	}
	
	public ArrayList<House> searchBySize(Double minSize, Double maxSize){
		
		ArrayList<House> houseListBySize = new ArrayList<House>();
		
		for(House eachHouse:houseList){
			if(minSize <= eachHouse.getSize() && eachHouse.getSize() <= maxSize){
				houseListBySize.add(eachHouse);
			}
		}
		
		return houseListBySize;
		
	}
	
	public ArrayList<House> orderHouseList(String direction){
		
		ArrayList<House> copyHouseList = new ArrayList<House>(houseList);
		ArrayList<House> orderedHouseList = new ArrayList<House>();
		
		if(direction.equalsIgnoreCase("Ascending")){
			
			setListWithAscending(copyHouseList, orderedHouseList);
		
		}
		else if(direction.equalsIgnoreCase("Descending")){
			
			setListWithDescending(copyHouseList, orderedHouseList);
			
		}
		return orderedHouseList;
		
	}
	
	public void setListWithAscending(ArrayList<House> copyHouseList, ArrayList<House> orderedHouseList){
		int i = 0;

		while(i<houseList.size()){
			House cheapest = copyHouseList.get(0);
			for(House eachHouse:copyHouseList){
				if ( eachHouse.getPrice()< cheapest.getPrice()){
					cheapest = eachHouse;
				}
			}
			
			copyHouseList.remove(cheapest);
			orderedHouseList.add(cheapest);
			i++;
			}
	}
	
	public void setListWithDescending(ArrayList<House> copyHouseList, ArrayList<House> orderedHouseList){
		int i = 0;
		
		while(i<houseList.size()){
			House highest = copyHouseList.get(0);
			for(House eachHouse:copyHouseList){
				if ( eachHouse.getPrice()> highest.getPrice()){
					highest = eachHouse;
				}
			}
		
		copyHouseList.remove(highest);
		orderedHouseList.add(highest);
		i++;
		
		}
	}
	

	
}
