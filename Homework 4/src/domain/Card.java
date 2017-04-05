package domain;
public class Card {

	private int number;
	private Type cardType;
	private int score;
	
	public int getScore() {
		
		return score;
	
	}

	public void setScore(Type type) {
		
		this.score = number*scoreMultiplay(type);

	}

	public void setType(Type type){
		
		cardType = type;
		
	}
	
	public void setNumber(int number){
		
		if(number > 13){
			//number can not be higher than 13
			//then program will be end
			System.exit(0);
		}
		
		this.number = number;
		
	}
	
	public int getNumber(){
		
		return number;
		
	}
	
	public Type getType(){
		
		return cardType;
		
	}
	
	public String toString(){
		if(number == 11)

			return("J("+number + ") " + cardType + " " );
		
		else if(number == 12)
			
			return("Q("+number + ") " + cardType + " " );
		
		else if(number == 13)
			
			return ("K("+number + ") " + cardType + " " );
		
		else{
			
			return(number + " " + cardType + " " + " ");
		}
	}
	
	public int scoreMultiplay(Type eachType){
		
		if(eachType == Type.CLUBS)
			
			return 11;
		
		else if(eachType == Type.DIAMONDS)
			
			return 9;
		
		else if(eachType == Type.HEARTHS)
			
			return 7;
		
		else{
			
			return 5;
		}
		
	}
	

}//end class
