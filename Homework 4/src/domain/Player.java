package domain;
import java.util.*;

public  class Player {
	private String name;
	private int score;
	private ArrayList<Card> listCard ;
	
	public Player(){
		
		listCard = new ArrayList<Card>();
	}
	
	public ArrayList<Card> getListCard() {
	
		return listCard;
	
	}
	
	public void addCard(Card card){
		
		listCard.add(card);

	}
	
	public void removeCard(Card card){
		
		listCard.remove(card);
	
	}
	
	public String getName() {
		
		return name;
	
	}
	
	public int listCardSize(){
		
		return listCard.size();
	
	}
	
	public Card getNextCard(int index){

		return null;
	
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setScore(int score){
		
		this.score = score;
	
	}
	
	public int getScore() {
		
		return score;
	
	}

	public void showPlayerCards(){
		
		int index=0;		
		
		while(index<this.listCardSize()){
			
			System.out.print("(" + (index+1) +") " + this.getListCard().get(index).toString() + "  ");
			
			index++;
		}
	}
	
}//end class
