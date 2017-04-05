package domain;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class DataAccessLayer {

	public void write(Menu menu){
	
		PrintWriter outputStream = null;
		
		try{
			
			outputStream = new PrintWriter(new FileOutputStream("humanPlayerName_computerPlayerName.txt"));
			
			for(Player eachPlayer : menu.getListPlayer()){
				outputStream.write(eachPlayer.getName() + ":" + " " + eachPlayer.getScore());
				outputStream.println();
				
			}//end loop
			
			outputStream.close();
			
		}catch(FileNotFoundException e){
			
			e.getMessage();
		}
		
	}//end write()
}//end class
