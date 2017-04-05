package domain;
import java.util.*;

public class Menu {
	private Simulation simulation;
	private Scanner keyboard ;
	private ArrayList<Player> listPlayer;
	
	public Menu(){
		
		simulation = new Simulation();
		keyboard = new Scanner(System.in);
		listPlayer = new ArrayList<Player>();
	
	}
	
	public ArrayList<Player> getListPlayer(){
		
		return listPlayer;
	}
	
	public Simulation getS1(){
		
		return simulation; 
	
	}
	
	public void createHumanPlayer(String name){
		
		simulation.getHumanPlayer().setName(name);
		
	}
	
	public void addPlayerToList(){
		
		listPlayer.add(simulation.getHumanPlayer());
		listPlayer.add(simulation.getComputerPlayer());
		
	}
	
	public void createComputerName(){
		
		simulation.getComputerPlayer().setName("anonmys");
		
	}
	
	
	
	
	public Card chooseCardForHumanPlayer(int choose){
		
		Card chosenCard = simulation.getHumanPlayer().getNextCard(choose - 1);
		
		simulation.getHumanPlayer().removeCard(chosenCard);
		
		return chosenCard;
	}
	
	public Integer smallestHigherNumber(int number){
		
		ArrayList<Integer> cardNumberOfComputer = new ArrayList<Integer>();
		
		for(Card eachCard : simulation.getComputerPlayer().getListCard()){
			
			cardNumberOfComputer.add(eachCard.getNumber());
		
		}
		
		Collections.sort(cardNumberOfComputer);
		
		Integer smallestHigherNumber = 0;
		
		for(Integer xEach : cardNumberOfComputer){
			
			if(xEach > number){
				
				smallestHigherNumber = xEach;
				
				break;
			}
		}
		
		return smallestHigherNumber;
	}
	
	public Card chooseCardForComputer(int number){ // if humanPlayer starts first, then we take humanPlayer's card number as a parameter.
		Card chosenCard = simulation.getComputerPlayer().getNextCard(0);
		int noHigherScore = 1;
		int smallest = 0;
		
		//Computer chooses higher number card than the humanPlayer. However this card is the lowest number in its card list.
		int smallestHigherNumber = smallestHigherNumber(number);
		
		for(Card eachCard:simulation.getComputerPlayer().getListCard()){
			if(eachCard.getNumber() == smallestHigherNumber){
				chosenCard = eachCard;
				simulation.getComputerPlayer().removeCard(eachCard);
				noHigherScore = 0;
				break;
			}
			//end if
		}//end for
		
		
		if(noHigherScore==1){ // if computer has no better card than the humanPlayer, then computer will choose card which its number is equal to humanPlayer score,
			//also if computer has not equal card, then computer will choose card which has the lowest number in its list.
			smallest = simulation.getComputerPlayer().getNextCard(0).getNumber();
			System.out.println(smallest);
				for(Card eachCard:simulation.getComputerPlayer().getListCard()){
					if(eachCard.getNumber()  == number){ // check computer has a equal card
						chosenCard = eachCard;
						break;
					}
					else if(eachCard.getNumber()<smallest){ // if not, choose lowest one.
						smallest = eachCard.getNumber();
						chosenCard = eachCard;
				}//end second if
			}//end for
				simulation.getComputerPlayer().removeCard(chosenCard);
		}// end first if
		return chosenCard;
	}
	
	public Card chooseCardForComputer(){ //if computerPlayer  starts first...
		Card chosenCard = simulation.getComputerPlayer().getNextCard(0);
		simulation.getComputerPlayer().removeCard(chosenCard);
		return chosenCard;
	}
	
	public int checkWhoWins(int humanCardNumber, int computerCardNumber){
		
		if(humanCardNumber>computerCardNumber)
			return 1;
		
		else if (humanCardNumber<computerCardNumber)
			return 2;
		
		else{
			return 0;
		
		}
	}
	
	public void restoreScore(int winner, int humanScore, int computerScore){
		if(winner==1){
			int newScore = simulation.getHumanPlayer().getScore() + humanScore;
			simulation.getHumanPlayer().setScore(newScore);
			System.out.println(simulation.getHumanPlayer().getName() + " won this round");
		}
		
		else if(winner==2){
			int newScore = simulation.getComputerPlayer().getScore() + computerScore;
			simulation.getComputerPlayer().setScore(newScore);
			System.out.println(simulation.getComputerPlayer().getName() + " won this round");
		}
		
		else if(winner==0){
			System.out.println("a draw");
		
		}
	}//end restoreScore()
	
	public int whoPlayFirst(){
		//if returns 0, then humanPlayer begins first, otherwise computerPlayer
		return (int)(Math.random()*2);
	
	}
	
	public boolean checkIndexOutOfList(int index){
		if(index > simulation.getHumanPlayer().listCardSize())
			
			return false;
		
		else{
			
			return true;
		}
	}
	
	public void gameMenu(){
		
		int whoBeginToTheRound = 0;
		simulation.init();
		simulation.distributedCards();
		
		System.out.println("Welcome...");
		System.out.println();
		System.out.println("Firstly, Introduce yourself : What is your name ? ");
		
		String name = keyboard.next();
		createHumanPlayer(name);
		
		createComputerName();
		System.out.println("Hello " + name + ", You will play with " + simulation.getComputerPlayer().getName());
		System.out.println("The one who will start will be decided by randomly in 2 seconds\n2\n1");

		whoBeginToTheRound = whoPlayFirst(); // we choose randomly who is going to start
		//first loop
		while(simulation.getHumanPlayer().listCardSize()>0){//game will continue until there are no cards left.
			//second loop
			while( ( simulation.getHumanPlayer().listCardSize() > 0 ) && ( whoBeginToTheRound == 0) ){ // humanPlayer starts first.
				
				System.out.println(name +  " will start first for this raund");
				System.out.print("Your card list: ");
				simulation.getHumanPlayer().showPlayerCards();
				
				System.out.println();
				System.out.println("Enter the number you see just before the cards in the list: ");
				String strChoose = keyboard.next();
				int choose = Integer.parseInt(strChoose);
				
				if(checkIndexOutOfList(choose) == false){
					
					System.out.println("Your choose is meaningless");
					System.out.println("Program is closing...");
					System.exit(0);
				}
				
				Card humanCard = chooseCardForHumanPlayer(choose);
				int humanScore = humanCard.getScore();
				int humanCardNumber = humanCard.getNumber();
				
				Card computerCard = chooseCardForComputer(humanCardNumber);
				
				System.out.println();
				System.out.println("According to your card, " + simulation.getComputerPlayer().getName() + " has used " + computerCard.toString());
				System.out.println();
				
				int computerScore = computerCard.getScore();
				int computerCardNumber = computerCard.getNumber();
				
				int result = checkWhoWins(humanCardNumber,computerCardNumber );
				
				restoreScore(result, humanScore,computerScore);
				
				System.out.println(simulation.getComputerPlayer().listCardSize() + " remaining cards per each player");
				
				
				if( result == 1){//if humanPlayer wins, then it goes third loop
					
					whoBeginToTheRound = 1;
				}
			}//end second loop
			
			//third loop
			while( ( simulation.getHumanPlayer().listCardSize() > 0 ) && ( whoBeginToTheRound == 1) ){ // computerPlayer starts first.
				
				System.out.println(simulation.getComputerPlayer().getName() + " will start first for this raund");
				
				Card computerCard = chooseCardForComputer();
				
				
				System.out.println();
				System.out.println(simulation.getComputerPlayer().getName() + " used:: " + computerCard.toString());
				System.out.print("Your card list: ");
				simulation.getHumanPlayer().showPlayerCards();
				System.out.println();
				System.out.println("It's your turn now.Enter the number you see just before the cards in the list: ");

				String strChoose = keyboard.next();
				int choose = Integer.parseInt(strChoose);
				
				if(checkIndexOutOfList(choose) == false){
					
					System.out.println("Your choose is meaningless");
					System.out.println("Program is closing...");
					System.exit(0);
				}
				
				System.out.println();
				
				Card humanCard = chooseCardForHumanPlayer(choose);
				int humanScore = humanCard.getScore();
				int humanCardNumber = humanCard.getNumber();
				
				int computerScore = computerCard.getScore();
				int computerCardNumber = computerCard.getNumber();
				
				int result = checkWhoWins(humanCardNumber,computerCardNumber);
				
				restoreScore(result,humanScore,computerScore);
				
				System.out.println(simulation.getComputerPlayer().listCardSize() + " remaining cards per each player");
				
				if( result == 2){//if computerPlayer wins, then it goes second loop.
					
					whoBeginToTheRound = 0;
				}
			}//end third loop
		}//end first loop
		addPlayerToList();
		System.out.println("The scores have written to the txt file : \"humanPlayerName_computerPlayerName.txt\"");
		
	} // end method
	
}//end class