package domain;

public class GameApplication {
	
	public static void main(String[] args)  {
		
		Menu menu = new Menu();
		DataAccessLayer fileAccess = new DataAccessLayer();
		
		menu.gameMenu();
		fileAccess.write(menu);
	}

}
