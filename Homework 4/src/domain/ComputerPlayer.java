package domain;
public class ComputerPlayer extends Player{
	private String[] randomName = {"Nick", "George", "Jason", "Caren"};
	
	public void setName(String name){
		
		int randomChoose = (int)(Math.random()*4) ;
		
		super.setName(randomName[randomChoose]);
		
	}
	
	public Card getNextCard(int index){
		
		int randomChoose = (int)(Math.random()*super.getListCard().size());
		
		return super.getListCard().get(randomChoose);
		
	}

	public String[] getRandomName() {
		
		return randomName;
	
	}

	public void setRandomName(String[] randomName) {
		
		this.randomName = randomName;
		
	}
	
	
}
