package domain;

public class HumanPlayer extends Player {
	
	public void setName(String name) {
		
		if(name == "" || name == null)
			
			super.setName("anonymous");
		
		else{
			
			super.setName(name);
		
		}
	}

	
	public Card getNextCard(int index) {
		
		return super.getListCard().get(index);
		
	}

	
	
}
