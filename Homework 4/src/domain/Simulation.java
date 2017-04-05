package domain;

public class Simulation {

	private Card[] allCards;
	private Player humanPlayer;
	private Player computerPlayer;
	
	public Simulation(){
		
		allCards = new Card[52];
		humanPlayer = new HumanPlayer();
		computerPlayer = new ComputerPlayer();
		createCard();
		
	}
	
	public Card[] getAllCards(){
		
		return allCards;
	
	}
	
	public Player getHumanPlayer() {
		return humanPlayer;
	
	}
	
	public void setHumanPlayer(HumanPlayer p1) {
		this.humanPlayer = p1;
	
	}
	
	public Player getComputerPlayer() {
		return computerPlayer;
	
	}
	
	public void setComputerPlayer(ComputerPlayer p2) {
		this.computerPlayer = p2;
	}
	
	public void init(){
		
		for(int i = 0; i < 52; i++){
			
			int shuffle = (int)(Math.random()*52);
			Card temporary = allCards[i];
			
			allCards[i] = allCards[shuffle];
			allCards[shuffle] = temporary;
			
		}
	}
	
	public void createCard(){
		
		int i = 0;
		
		while( i < 4){
			
			for(Type eachType: Type.values()){//first loop
				
				int y = 1;
				
				for(int j = i*13; j < i*13+13; j++){//second loop
					
					allCards[j]=new Card();
					allCards[j].setNumber(y);
					allCards[j].setType(eachType);
					allCards[j].setScore(eachType);
					
					y++;
				}//end second loop
				i++;
			}//end first loop
		}//end while
		
	}
	
	public void distributedCards(){
		
		int count = 0;
		
		while(count < 52){//first loop
		
			for(int i = 0; i< 26 ; i++){
				
				humanPlayer.addCard(allCards[count]);
				
				count++;
			
			}
			
			for(int j = 0; j < 26; j++){	
				
				computerPlayer.addCard(allCards[count]);
				
				count++;
			
			}
		}//end first loop
	
	}//end distributedCards()
	
}//end class