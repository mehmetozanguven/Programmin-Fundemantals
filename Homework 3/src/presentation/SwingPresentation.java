package presentation;

import fileaccess.*;
import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
public class SwingPresentation {
	public static void main(String[] args){
		Company newCompany = new Company();
		DataAccessLayer data = new DataAccessLayer();
		data.readFile(newCompany);
		

		
		SwingPresentation s1 = new SwingPresentation();
			
		data.writeFile(newCompany, "19/11/2016");
		
		for(Vehicle e : s1.getNewCompany().getTotalVehicles()){
			System.out.println(e.getDepartureDate());
		}
	}
	
	
	
	private Company newCompany = new Company();
	private Cargo newCargo ;
	private double price;
	
	
	public Company getNewCompany() {
		return newCompany;
	}

	JFrame mainFrame = new JFrame("Menu");	
	JLabel label2 = new JLabel("");
	JLabel vehicles = new JLabel("");
	
	JPanel mainMenu = new JPanel();
	JPanel ifNoAvailableVehicle = new JPanel();
	JPanel pricePanel = new JPanel();
	JPanel customerPanel = new JPanel();
	JPanel receiverPanel = new JPanel();
	JPanel senderPanel = new JPanel();
	JPanel whoPaysCargo = new JPanel();
	JPanel approriateVehicles = new JPanel();
	
	JComboBox whoPays = new JComboBox(whoPaysCargo());
	
	JButton buttonReturn = new JButton("Return the main menu");
	JButton buttonOkey = new JButton("OK");
	JButton buttonAccept = new JButton("accept "); 
	JButton buttonReject = new JButton("reject");
	JButton buttonOkey2 = new JButton("OK");
	JButton buttonForCreateReceiver = new JButton("OK");
	JButton buttonOkAfterReceiver = new JButton("OKEYY");
	JButton theLastButton = new JButton("Confirm");
	JButton okIGotIt = new JButton("OK,I got it..");
	
	JTextField weightTextField = new JTextField("", 20);
	JTextField dateTextField = new JTextField("", 20);
	JTextField senderCustomerNameField = new JTextField("", 20);
	
	JLabel weightLabel = new JLabel("Write the weight");
	JLabel dateLabel = new JLabel("Write the date");
	JLabel vehicleTypeLabel = new JLabel("Select the type of vehicle");
	JLabel okeyLabel = new JLabel("press OK to continue");
	JLabel chooseWhoPays = new JLabel("Determine that who pays the cargo");

	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox comboBox = new JComboBox(comboBox());
	
	ListenerForButton lForButtonOkey = new ListenerForButton();
	ListenerForButton2 l2ForButtonAcceptAndReject = new ListenerForButton2();
	ListenerForButton3 l3ForButtonOkey = new ListenerForButton3();
	ListenerForButton4 l4ForButtonforCreateReceiver = new ListenerForButton4();
	ListenerForButton5  l5ForButtonAfterReceiver = new ListenerForButton5();
	ListenerForButton6 l6ForButtonReturnMainMenu = new ListenerForButton6();
	ListenerForButton7 l7ForButtonOkIGotIt = new ListenerForButton7();

	
	public String[] comboBox(){
		String[] types = new String[3];
		int i = 0;
		for(TransportationType typ: TransportationType.values()){
			types[i] = typ.toString();
			i++;
		}
		return types;
		
	}
	
	public String[] whoPaysCargo(){
		String[] whoPaysCargo = new String[2];
		whoPaysCargo[0] = "sender"; whoPaysCargo[1] = "receiver";
		return whoPaysCargo;
	}
	
	public SwingPresentation(){
		mainFrame.setSize(400,300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		weightTextField.setToolTipText("Enter the weight of your cargo here");
		dateTextField.setToolTipText("Enter the current date ");
		mainMenu.add(weightLabel);
		mainMenu.add(weightTextField);
		mainMenu.add(dateLabel);
		mainMenu.add(dateTextField);
		mainMenu.add(vehicleTypeLabel);
		mainMenu.add(comboBox);
		
		
		buttonAccept.addActionListener(l2ForButtonAcceptAndReject);
		buttonReject.addActionListener(l2ForButtonAcceptAndReject);
		buttonOkey.addActionListener(lForButtonOkey);
		buttonOkey2.addActionListener(l3ForButtonOkey);
		buttonForCreateReceiver.addActionListener(l4ForButtonforCreateReceiver);
		buttonOkAfterReceiver.addActionListener(l5ForButtonAfterReceiver);
		theLastButton.addActionListener(l6ForButtonReturnMainMenu);
		okIGotIt.addActionListener(l7ForButtonOkIGotIt);
		
		mainMenu.add(okeyLabel);
		mainMenu.add(buttonOkey);
		mainMenu.setLayout(new GridLayout(4,1));
		mainMenu.setVisible(true);
		mainFrame.add(mainMenu);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}//end constructor
	
	public void createWhoPaysCargo(JPanel whoPaysCargo){
		receiverPanel.setVisible(false);
		whoPaysCargo.setLayout(new GridLayout(2,1));
		chooseWhoPays.setText("Determine that who pays the cargo cost: ");
		
		whoPaysCargo.add(chooseWhoPays);
		whoPaysCargo.add(whoPays);
		whoPaysCargo.add(theLastButton);
		whoPaysCargo.setVisible(true);
		
		Customer newCustomer = new Customer();
		newCustomer.setName(whoPays.getSelectedItem().toString());
		newCargo.setPayCargoCost(newCustomer);
		mainFrame.add(whoPaysCargo);
	}
	public void createSenderPanel(JPanel senderPanel, SenderCustomer newSender){
		senderPanel.setLayout(new GridLayout(5,1));

		
		
		JLabel idLabel = new JLabel("Write sender id");
		JTextField senderCustomerIDField = new JTextField("", 20);
		
		JLabel nameLabel = new JLabel("Write sender name");
		JTextField senderCustomerNameField = new JTextField("", 20);
		
		JLabel phoneLabel = new JLabel("Write phone number");
		JTextField senderCustomerPhoneField = new JTextField("",20);
		
		JLabel adressLabel = new JLabel("Write adress");
		JTextField senderCustomerAdressField = new JTextField("", 20);

		JLabel lastLabel = new JLabel("Press ok to enter receiver information.");
		
		senderPanel.add(idLabel);
		senderPanel.add(senderCustomerIDField);
		
		senderPanel.add(nameLabel);
		senderPanel.add(senderCustomerNameField);
		
		senderPanel.add(phoneLabel);
		senderPanel.add(senderCustomerPhoneField);
		
		senderPanel.add(adressLabel);
		senderPanel.add(senderCustomerAdressField);
		
		senderPanel.add(lastLabel);
		senderPanel.add(buttonForCreateReceiver);
		String senderID = senderCustomerIDField.getText();
		String senderName = senderCustomerNameField.getText();
		String senderAdress = senderCustomerAdressField.getText();
		Double senderPhone = 0.0;
		
		try{
			
			senderPhone = Double.parseDouble(senderCustomerPhoneField.getText());
		
		}catch(NumberFormatException e){
			
			e.getMessage();
		
		}

		mainFrame.add(senderPanel);

		newSender.setName(senderName);
		newSender.setAdress(senderAdress);
		newSender.setNationalID(senderID);
		newSender.setPhoneNum(senderPhone);
		
		newCargo.setSender(newSender);
		
	}
	public void createReceiverPanel(JPanel receiverPanel){

		senderPanel.setVisible(false);
		customerPanel.setVisible(false);
		
		ReceiverCustomer newReceiver = new ReceiverCustomer();
		
		JLabel idLabel = new JLabel("Write receiver id");
		JTextField receiverCustomerIDField = new JTextField("", 20);
		
		JLabel nameLabel = new JLabel("Write receiver name");
		JTextField receiverCustomerNameField = new JTextField("", 20);
		
		JLabel phoneLabel = new JLabel("Write phone number");
		JTextField receiverCustomerPhoneField = new JTextField("", 20);
		
		JLabel adressLabel = new JLabel("Write address");
		JTextField receiverCustomerAdressField = new JTextField("", 20);
		
		String receiverID = receiverCustomerIDField.getText();
		String receiverName = receiverCustomerNameField.getText();
		String receiverAdress = receiverCustomerAdressField.getText();
		Double receiverPhone = 0.0;
		
		try{
			
			receiverPhone = Double.parseDouble(receiverCustomerAdressField.getText());
		
		}catch(NumberFormatException e){
			
			e.getMessage();
		
		}
		
		
		receiverPanel.add(idLabel);
		receiverPanel.add(receiverCustomerIDField);
		
		receiverPanel.add(nameLabel);
		receiverPanel.add(receiverCustomerNameField);
		
		receiverPanel.add(phoneLabel);
		receiverPanel.add(receiverCustomerPhoneField);
		
		receiverPanel.add(adressLabel);
		receiverPanel.add(receiverCustomerAdressField);
		
		receiverPanel.add(buttonOkAfterReceiver);
		
		
		
		mainFrame.add(receiverPanel);
		
		newReceiver.setName(receiverName);
		newReceiver.setAdress(receiverAdress);
		newReceiver.setNationalID(receiverID);
		newReceiver.setPhoneNum(receiverPhone);
		newCargo.setReceiver(newReceiver);
		
	}
	//inner classes is starting here...
	private class ListenerForButton implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == buttonOkey){
				newCargo = new Cargo();
				Vehicle newVehicle = null;
				
				newCargo.setUniqueID("123");
				
				Double weight = Double.parseDouble(weightTextField.getText());
				
				String date = dateTextField.getText();
				String type = comboBox.getSelectedItem().toString();
				
				newCargo.setWeight(weight);
				newCargo.setOrderDate(date);
				
				
				if(newCompany.listAppropiateVehicles(date, weight, type).size()!=0){
					
					newCompany.addCargoToTheList(newCargo);
					
					newVehicle = newCompany.listAppropiateVehicles(date, weight, type).get(0);
					newVehicle.addCargoToTheVehicle(newCargo);
					
					newCompany.addVehicleToTheVehicleList2(newVehicle);
					price = newCompany.calculatedPrice(newVehicle, newCargo);
					
					mainMenu.setVisible(false);
					
					label2.setText("price = "+ price);
					
					pricePanel.add(label2);
					pricePanel.add(buttonAccept);
					pricePanel.add(buttonReject);
					pricePanel.setVisible(true);
					
					mainFrame.add(pricePanel);
					
					
				}//end if
				else{
					mainMenu.setVisible(false);
					String s = "";
					for(Vehicle vehicle :newCompany.getTotalVehicles()){
						s= s+ " " + vehicle.getUniqueID()+ " " + vehicle.getDepartureDate()+ " " + vehicle.getUpperLimitWeight(); 
					 
					}
				 
					vehicles.setText(s);
					mainMenu.setVisible(false);
					approriateVehicles.add(vehicles);
					approriateVehicles.add(okIGotIt);
					approriateVehicles.setVisible(true);
					mainFrame.add(approriateVehicles);
				}
				
				
				
				
				
				
			}
			
		}
	}//end ListenerForButton
	
	private class ListenerForButton2 implements ActionListener{
		
		JLabel senderLabel = new JLabel("Write sender customer name ");		

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == buttonAccept){
				
				pricePanel.setVisible(false);
				
				customerPanel.add(buttonOkey2);
				customerPanel.add(senderLabel);
				customerPanel.add(senderCustomerNameField);
				customerPanel.setVisible(true);
				
				mainFrame.add(customerPanel);
				
			}
			else if(e.getSource() == buttonReject){
				
				pricePanel.setVisible(false);
				mainMenu.setVisible(true);
			}
			
		}
		
	}//end ListenerForButton2
	
	private class ListenerForButton3 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {//first if
	
			if(e.getSource() == buttonOkey2){
				System.out.println(newCompany.isCustomerExists(senderCustomerNameField.getText()));
				if(newCompany.isCustomerExists(senderCustomerNameField.getText())){
					
					for(SenderCustomer eachSender : newCompany.getListOfSenders()){
						
						if(eachSender.getName().equals(senderCustomerNameField.getText().trim())){
							
							newCargo.setSender(eachSender);
							createReceiverPanel(receiverPanel);
						}
					}//end for
				}//end if
				else{
					
					customerPanel.setVisible(false);

					SenderCustomer newSender = new SenderCustomer();
					newCompany.addCustomerToTheListOfSenders(newSender);
					
					createSenderPanel(senderPanel, newSender);
					
					newCargo.setSender(newSender);
					
					
					
				}//end else
				
			}//end first if
		}//end actionPerformed()
	}//end ListenerForButton3


	private class ListenerForButton4 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == buttonForCreateReceiver){
				
				createReceiverPanel(receiverPanel);
			}
			
		}
		
	}//end ListenerForButton4
	private class ListenerForButton5 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == buttonOkAfterReceiver){			
				createWhoPaysCargo(whoPaysCargo);
			
			}
		
		}
	}
	private class ListenerForButton6 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == theLastButton){
				whoPaysCargo.setVisible(false);
				mainFrame.setVisible(false);

				SwingPresentation s2 = new SwingPresentation();
				s2.mainFrame.setVisible(true);
			}
			
		}
		
	}
	private class ListenerForButton7 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okIGotIt){
				approriateVehicles.setVisible(false);
				
			}
			
		}
		
	}//end method
	
}//end classer
