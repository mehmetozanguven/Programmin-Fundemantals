package presentation;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.table.DefaultTableModel;

import domain.*;
import fileAccessLayer.*;
import static javax.swing.GroupLayout.Alignment.*;

public class SwingPresentation {
	private JFrame mainFrame,invalidInputFrame;
	private JPanel invalidInputPanel;
	private JButton orderHousesButton, addHouseButton, removeHouseButton, searchButton, writeToFile;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel ;
	private JTable table;
	private JTextArea textAreaForInvalidInput;
	private ListenerForButton lForButtonOrderHouses;
	private ListenerForButton2 lForButtonAddHouse;
	private ListenerForButton3 lForButtonRemoveHouse;
	private ListenerForButton4 lForButtonSearch;
	private ListenerForButton5 lForButtonWrite;
	private EstateAgent copyEstateAgent;
	private ArrayList<House> originalHouseList;
	private DataAccessLayer data;
	
	public SwingPresentation(EstateAgent estagent, DataAccessLayer dal){
		
		mainFrame = new JFrame("Estate Agent");
		//keep first original house list, because generally we set different arraylist..
		originalHouseList = new ArrayList<House>(estagent.getHouseList());
		
		init(estagent);
		data = dal;
		
    }
	
	public EstateAgent getCopyEstateAgent(){
		
		return copyEstateAgent;
		
	}
	
	public void createMainFrame(){
		
		GroupLayout layout = new GroupLayout(mainFrame.getContentPane());
        mainFrame.getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
        		.addComponent(scrollPane)
        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(orderHousesButton)
        				.addComponent(addHouseButton)
        				.addComponent(removeHouseButton)
        				.addComponent(searchButton)
        				.addComponent(writeToFile))
        		
        		);
       layout.linkSize(SwingConstants.HORIZONTAL, orderHousesButton, addHouseButton, removeHouseButton, searchButton, writeToFile);

        layout.setVerticalGroup(layout.createSequentialGroup()
        		.addGroup(layout.createParallelGroup(BASELINE)
        				.addComponent(scrollPane)
        				.addGroup(layout.createSequentialGroup()
        						.addGroup(layout.createParallelGroup(LEADING)
        								.addComponent(orderHousesButton)
        								)
        						.addGroup(layout.createParallelGroup(LEADING)
        								.addComponent(addHouseButton)
        								)
        						.addGroup(layout.createParallelGroup(LEADING)
        								.addComponent(removeHouseButton)
        								)
        						.addGroup(layout.createParallelGroup(LEADING)
        								.addComponent(searchButton)
        								)
        						.addGroup(layout.createParallelGroup(LEADING)
        								.addComponent(writeToFile)
        								)
        						)
        				)
        		);
        
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void createInvalidInputFrame(String invalidAttribute){
		
		invalidInputFrame = new JFrame("Error");
		invalidInputPanel = new JPanel();
		textAreaForInvalidInput = new JTextArea(invalidAttribute + " that you entered is invalid.\n" +
												"Please back to the main window");
		textAreaForInvalidInput.setSize(300, 300);
		textAreaForInvalidInput.setLineWrap(true);
		textAreaForInvalidInput.setWrapStyleWord(true);
		
		invalidInputPanel.add(textAreaForInvalidInput);
		invalidInputFrame.add(invalidInputPanel);
		invalidInputFrame.pack();
		invalidInputFrame.setVisible(true);
		
	}
	
	public void cleanMainFrame_AddNewPanel(JPanel panel){
		
		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().repaint();//before add anything first clean all components
		mainFrame.add(panel);//then add
		mainFrame.validate();
		
	}
	
	public void init(EstateAgent estagent){
		
		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().repaint();
		//for action listeners..
		lForButtonOrderHouses = new ListenerForButton();
		lForButtonAddHouse = new ListenerForButton2();
		lForButtonRemoveHouse = new ListenerForButton3();
		lForButtonSearch = new ListenerForButton4();
		lForButtonWrite = new ListenerForButton5();
		

		tableModel =  new DefaultTableModel();
		tableModel.addColumn("id");
		tableModel.addColumn("price");
		tableModel.addColumn("size");
		tableModel.addColumn("rooms");
		tableModel.addColumn("bathrooms.");
		tableModel.addColumn("airconditioner");
		
		for(int i=0;i<estagent.getHouseList().size();i++){
			
			Object[] data = { estagent.getHouseList().get(i).getId(),
					estagent.getHouseList().get(i).getPrice(),
					estagent.getHouseList().get(i).getSize(),
					estagent.getHouseList().get(i).getNumberOfRooms(),
					estagent.getHouseList().get(i).getNumberOfBathrooms(),
					estagent.getHouseList().get(i).GetHasAnAirConditioner(),
			};
			
			tableModel.insertRow(i, data);
		}
		
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		
		
		orderHousesButton = new JButton("Order Houses");
		orderHousesButton.addActionListener(lForButtonOrderHouses);
		
		addHouseButton = new JButton("Add House");
		addHouseButton.addActionListener(lForButtonAddHouse);
		
		removeHouseButton = new JButton("Remove House");
		removeHouseButton.addActionListener(lForButtonRemoveHouse);
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(lForButtonSearch);
		
		writeToFile = new JButton("Write to the File");
		writeToFile.addActionListener(lForButtonWrite);
		
		copyEstateAgent = estagent;//copy of estateAgent for using somewhere
		createMainFrame();
	}
	
	
	
	//Inner class' start here..
	
	private class ListenerForButton implements ActionListener{//listener for Button Order Houses
		private JPanel orderPanel;
		private JLabel orderLabel;
		private JComboBox comboBox;
		private JButton confirmButtonForOrder;
		
		public ListenerForButton() {
			//user choose one of them
			String[] ascendingOrDescending = {"Ascending", "Descending"};
			
			orderPanel = new JPanel();
			orderLabel = new JLabel("Choose one of them:");
			
			comboBox = new JComboBox(ascendingOrDescending);
		
			confirmButtonForOrder = new JButton("Okey");
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == orderHousesButton ){//when orderHousesButton click
				createSearchPanel();
				confirmButtonForOrder.addActionListener(lForButtonOrderHouses);

			}
			
			if(e.getSource() == confirmButtonForOrder){ //when orderHousesButton click confirmButton
				//orderHouseList() method returns appropriate arrayList according to user choice
				ArrayList<House> orderedList = copyEstateAgent.orderHouseList(comboBox.getSelectedItem().toString());
				copyEstateAgent.setHouseList(orderedList);//according to choose we set list ascending or descending
				
				init(copyEstateAgent);//then init method use that list and create always same thing but elements are different
				
			}
			copyEstateAgent.setHouseList(originalHouseList);//we have to set again original list, because everything works on this list
		}
		
		public void createSearchPanel(){
			
			orderPanel.add(orderLabel);
			orderPanel.add(comboBox);
			orderPanel.add(confirmButtonForOrder);
			orderPanel.setSize(500, 500);
			
			cleanMainFrame_AddNewPanel(orderPanel);

		}
		
	}//end ListenerForButton class
	
	private class ListenerForButton2 implements ActionListener{//listener for Button Add House
		private JPanel addPanel;
		private JLabel  priceLabel, sizeLabel, roomsLabel, bathroomsLabel, airConditionerPanel;
		private JComboBox airConditionerComboBox;
		private JTextField  priceTextField, sizeTextField, roomsTextField, bathroomsTextField;
		private JButton confirmButton;

		public ListenerForButton2() {
			//user choose one of them
			String[] chooseForAirConditioner = {"yes", "no"};
			addPanel = new JPanel();
			
			priceLabel = new JLabel("Write price");
			sizeLabel = new JLabel("Write size");
			roomsLabel = new JLabel("Write how many rooms");
			bathroomsLabel = new JLabel("Write how many bahrooms");
			airConditionerPanel = new JLabel("Does House have air Conditioner?");
			
			airConditionerComboBox = new JComboBox(chooseForAirConditioner);
			
			priceTextField = new JTextField("", 20);
			sizeTextField = new JTextField("", 20);
			roomsTextField = new JTextField("", 20);
			bathroomsTextField = new JTextField("", 20);
			
			confirmButton = new JButton("Okey");

		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == addHouseButton){//when add button click
				createAddPanel();
				confirmButton.addActionListener(lForButtonAddHouse);//also add action listener to confirmButton
			}
			
			if(e.getSource() == confirmButton){//when user confirm house information
				//first check whether input is valid or not, if invalid method return true, otherwise false
				if(checkNumberFormatException(priceTextField.getText().trim(), sizeTextField.getText().trim(), bathroomsTextField.getText().trim(), roomsTextField.getText().trim())){
					createInvalidInputFrame("House attribute(s)");
				}
				else{//if input is invalid
					createNewHouseAndAddToList();//we create House object
					init(copyEstateAgent);//then show new House list
				}
			}
		}
		
		public void createAddPanel(){
			
			addPanel.setLayout(new GridLayout(10,1));
			
			addPanel.add(priceLabel);
			addPanel.add(priceTextField);
			addPanel.add(sizeLabel);
			addPanel.add(sizeTextField);
			addPanel.add(roomsLabel);
			addPanel.add(roomsTextField);
			addPanel.add(bathroomsLabel);
			addPanel.add(bathroomsTextField);
			addPanel.add(airConditionerPanel);
			addPanel.add(airConditionerComboBox);
			addPanel.add(confirmButton);
			addPanel.setSize(500, 500);
			
			cleanMainFrame_AddNewPanel(addPanel);
			
		}
		
		public ArrayList<String> addAll_IDs_ToList(){
	
			ArrayList<String> listOfIDsOfHouses = new ArrayList<String>();

			for(House eachHouse : originalHouseList){
				listOfIDsOfHouses.add(eachHouse.getId());
			}
			
			return listOfIDsOfHouses;
		}
		
		public String createAutomaticallyID(){
			ArrayList<String> listIDs = addAll_IDs_ToList();
			
			int id = 1;
			while(true){
				if(listIDs.contains(String.valueOf(id)))//for find unique ID
					id++;
				else{
					return String.valueOf(id);
				}
			}
		}
		
		public Boolean checkNumberFormatException(String price_str, String size_str, String bathroom_str, String room_str){
			//this method return true if there is any exception, otherwise return false
			boolean result = false;
			
			try{
				Double price = Double.parseDouble(price_str);
				Double size = Double.parseDouble(size_str);
				Integer bahtroom = Integer.parseInt(bathroom_str);
				Integer room = Integer.parseInt(room_str);
			}catch(NumberFormatException e){
				result = true;
			}
			
			return result;
		}
		
		public void createNewHouseAndAddToList(){
			//we are creating House object
			House newHouse = new House();
			String id = createAutomaticallyID().trim();
			newHouse.setId(id);
			newHouse.setPrice(Double.parseDouble(priceTextField.getText().trim()));
			newHouse.setNumberOfBathrooms(Integer.parseInt(bathroomsTextField.getText().trim()));
			newHouse.setAirConditionerFeature(airConditionerComboBox.getSelectedItem().toString());
			newHouse.setNumberOfRooms(Integer.parseInt(roomsTextField.getText().trim()));
			newHouse.setSize(Double.parseDouble(sizeTextField.getText().trim()));
			
			originalHouseList.add(newHouse);//first adding newHouse to arrayList
			copyEstateAgent.setHouseList(originalHouseList);//then set new arrayList in copyEstateAgent

		}
		
	}//end ListenerForButton2 class
	
	private class ListenerForButton3 implements ActionListener{//listener for Button Remove House
		private JButton confirmButton;
		private House removeHouse;
		private String id;
		private JTextField idTextField;
		private JPanel removePanel;
		private JLabel label;		
		
		public ListenerForButton3() {
			
			removePanel = new JPanel();
			idTextField = new JTextField("", 20);
			confirmButton = new JButton("Okey");
			label = new JLabel("Enter the house id that you want to remove");
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == removeHouseButton){//when removeHouseButton click
				createRemovePanel();//create its panel 
				confirmButton.addActionListener(lForButtonRemoveHouse);//also add action listener to confirmButton
			}
			
			if(e.getSource() == confirmButton){//when confirmButton click
				takeID_ValueFromUser();
			}
		}
		
		public void takeID_ValueFromUser(){
			id = idTextField.getText().trim();//take value from textField
			removeHouse = copyEstateAgent.findHouseWithId(id);//then if we have house that id, return that house, if not return null			
			
			if(checkHouseWithId()){//if house is not null, return true
				originalHouseList.remove(removeHouse);//first remove house originalList
				copyEstateAgent.setHouseList(originalHouseList);//then set arrayList in copyEstateAgent

				init(copyEstateAgent);//show new List
				
			}
			else{//if House is null (id input is invalid), then show Error frame
				
				createInvalidInputFrame("ID");

			}
		}
		
		public void createRemovePanel(){

			removePanel.add(label);
			removePanel.add(idTextField);
			removePanel.add(confirmButton);
			removePanel.setSize(500, 500);
			
			cleanMainFrame_AddNewPanel(removePanel);
			
		}
		
		public Boolean checkHouseWithId(){//this method return true if house object is not null, otherwise return false;
			if(removeHouse == null){
				return false;
			}
			else{
				return true;
			}
		}
		
	}//end ListenerForButton3 class
	
	private class ListenerForButton4 implements ActionListener{//listener for Button Search House
		private JPanel searchPanel, byPricePanel, bySizePanel, byRoomsPanel;
		private JLabel searchLabel, MaxPriceLabel, MinPriceLabel, MaxSizeLabel, MinSizeLabel, roomLabel;
		private JComboBox comboBox;
		private JButton confirmButtonForSearchPanel, confirmButtonForPricePanel, confirmButtonForSizePanel, confirmButtonForRoomPanel;
		private JTextField maxPriceAndSizeTextField, minPriceAndSizeTextField, roomNumberTextField;
		
		public ListenerForButton4() {
			//user choose one of them
			String[] searchList = {"By Price", "By Number of Rooms", "By Size"};
			
			searchPanel = new JPanel();
			byPricePanel = new JPanel();
			bySizePanel = new JPanel();
			byRoomsPanel = new JPanel();
			
			searchLabel = new JLabel("Choose one of them");
			MaxPriceLabel = new JLabel("Write max price");
			MinPriceLabel = new JLabel("Write min price");
			MaxSizeLabel = new JLabel("Write max size");
			MinSizeLabel = new JLabel("Write min size");
			roomLabel = new JLabel("Write number of rooms");
			
			comboBox = new JComboBox(searchList);
			
			confirmButtonForSearchPanel = new JButton("Okey");
			confirmButtonForPricePanel = new JButton("Okey");
			confirmButtonForSizePanel = new JButton("Okey");
			confirmButtonForRoomPanel = new JButton("Okey");
			
			maxPriceAndSizeTextField = new JTextField("", 20);
			minPriceAndSizeTextField = new JTextField("", 20);
			roomNumberTextField = new JTextField("", 20);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == searchButton){//when searchButton is clicked
				createSearchPanel();
				confirmButtonForSearchPanel.addActionListener(lForButtonSearch);//also add action listener to confirmButtonForSearchPanel
			}
			
			if(e.getSource() == confirmButtonForSearchPanel){// when confirmButtonForSearchPanel is clicked
				createPanelByUserChoice();
			}
			
			if(e.getSource() == confirmButtonForPricePanel){//when confirmButtonForPricePanel is clicked
				takePriceValueFromUser();
			}
			
			if(e.getSource() == confirmButtonForRoomPanel){//when confirmButtonForRoomPanel is clicked
				takeRoomValueFromUser();
			}
			
			if(e.getSource() == confirmButtonForSizePanel){//when confirmButtonForSizePanel is clicked
				takeSizeValueFromUser();
			}
			copyEstateAgent.setHouseList(originalHouseList);//after that we have to return originalList
			
		}//end actionPerformed()
		
		public Boolean checkNumberFormatException(String minSize_str, String maxSize_str, String minPrice_str, String maxPrice_str, String numOfRooms_str){
			//this method return true if there is any exception
			boolean result = false;
			
			try{
				Double minSize = Double.parseDouble(minSize_str);
				Double maxSize = Double.parseDouble(maxSize_str);
				Double minPrice = Double.parseDouble(minPrice_str);
				Double maxPrice = Double.parseDouble(maxPrice_str);
				Integer numOfRooms = Integer.parseInt(numOfRooms_str);
			}catch(NumberFormatException e){
				result = true;
			}
			
			return result;
		}
		
		public void createSearchPanel(){
			
			searchPanel.add(searchLabel);
			searchPanel.add(comboBox);
			searchPanel.add(confirmButtonForSearchPanel);
			searchPanel.setSize(500, 500);
			
			cleanMainFrame_AddNewPanel(searchPanel);
			
		}
		
		public void createByPricePanel(){
			
			byPricePanel.setLayout(new GridLayout(5, 1));
			byPricePanel.add(MinPriceLabel);
			byPricePanel.add(minPriceAndSizeTextField);
			byPricePanel.add(MaxPriceLabel);
			byPricePanel.add(maxPriceAndSizeTextField);
			byPricePanel.add(confirmButtonForPricePanel);
			byPricePanel.setSize(300, 300);
			
			cleanMainFrame_AddNewPanel(byPricePanel);
			
		}
		
		public void createBySizePanel(){
			
			bySizePanel.setLayout(new GridLayout(5,1));
			bySizePanel.add(MinSizeLabel);
			bySizePanel.add(minPriceAndSizeTextField);
			bySizePanel.add(MaxSizeLabel);
			bySizePanel.add(maxPriceAndSizeTextField);
			bySizePanel.add(confirmButtonForSizePanel);
			bySizePanel.setSize(300, 300);
			
			cleanMainFrame_AddNewPanel(bySizePanel);
			
		}
		
		public void createByRoomPanel(){
			
			byRoomsPanel.setLayout(new GridLayout(3, 1));
			byRoomsPanel.add(roomLabel);
			byRoomsPanel.add(roomNumberTextField);
			byRoomsPanel.add(confirmButtonForRoomPanel);
			byRoomsPanel.setSize(300, 300);
			
			cleanMainFrame_AddNewPanel(byRoomsPanel);
			
		}
		
		public void createPanelByUserChoice(){
			
			if(comboBox.getSelectedItem().toString().equals("By Price")){//if user chooses "By Price"
				createByPricePanel();//then create byPrice Panel's
				confirmButtonForPricePanel.addActionListener(lForButtonSearch);//also add action listener to confirmButtonForPricePanel
			}
			
			else if(comboBox.getSelectedItem().toString().equals("By Size")){//if user chooses "By Size"
				createBySizePanel();//then create bySize Panel's
				confirmButtonForSizePanel.addActionListener(lForButtonSearch);//also add action listener to confirmButtonForSizePanel
			}
			
			else if(comboBox.getSelectedItem().equals("By Number of Rooms")){//if user chooses "By Number of Rooms"
				createByRoomPanel();//then create byRooms Panel's
				confirmButtonForRoomPanel.addActionListener(lForButtonSearch);//also add action listener to confirmButtonForRoomPanel
			}
		}
		
		public void takePriceValueFromUser(){
			String minPrice_str = minPriceAndSizeTextField.getText().trim();//take value from textField
			String maxPrice_str = maxPriceAndSizeTextField.getText().trim();//take value from textField
			
			if(checkNumberFormatException("0.0", "0.0", minPrice_str, maxPrice_str, "0")){//if input is invalid, method returns true
				createInvalidInputFrame("Price attribute(s)");
			}
			else{
				Double minPrice = Double.parseDouble(minPrice_str);//convert double
				Double maxPrice = Double.parseDouble(maxPrice_str);//convert double
				//the method searchByPrice() returns appropriate arrayList (using parameters)
				copyEstateAgent.setHouseList(copyEstateAgent.searchByPrice(minPrice, maxPrice));//set specially list in copyEstateAgent
				init(copyEstateAgent);//then show that specially List
			}
		}
		
		public void takeRoomValueFromUser(){
			String numOfRooms_str = roomNumberTextField.getText().trim();//take value from textField
			if(checkNumberFormatException("0.0", "0.0", "0.0", "0.0", numOfRooms_str)){//if input is invalid, method returns true
				createInvalidInputFrame("Number of rooms");
			}
			else{
				Integer numOfRooms = Integer.parseInt(numOfRooms_str);//convert integer
				//the metod searchByNumberOfRooms() returns appropriate arrayList(using parameters)
				copyEstateAgent.setHouseList(copyEstateAgent.searchByNumberOfRooms(numOfRooms));//set specially list in copyEstateAgent
				init(copyEstateAgent);//then show that specially list
			}
		}
		
		public void takeSizeValueFromUser(){
			String minSize_str = minPriceAndSizeTextField.getText().trim();//take value from textField
			String maxSize_str = maxPriceAndSizeTextField.getText().trim();//take value from textField
			if(checkNumberFormatException(minSize_str, maxSize_str, "0.0", "0.0", "0")){//if input is invalid, method returns true
				createInvalidInputFrame("Size attribute(s)");
			}
			else{
				Double minSize = Double.parseDouble(minSize_str);//convert double
				Double maxSize = Double.parseDouble(maxSize_str);//conver double
				//the metod searchBySize() returns appropriate arrayList(using parameters)
				copyEstateAgent.setHouseList(copyEstateAgent.searchBySize(minSize, maxSize));//set specially list in copyEstateAgent
				init(copyEstateAgent);//then show that specially list
			}
		}
		
	}//end ListenerForButton4 class
	
	private class ListenerForButton5 implements ActionListener{//listener for Button Write to File
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == writeToFile){
				try {
					data.writeHouseInfo("housing_updated.txt", copyEstateAgent);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
	}//end ListenerForButton5 class
	
}
