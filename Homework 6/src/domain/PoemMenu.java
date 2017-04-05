package domain;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

import fileAccessLayer.*;

public class PoemMenu {
	private Scanner keyboard;
	private PoemOperations operation;
	
	public PoemMenu(DataAccessLayer dal){
		
		
		keyboard = new Scanner(System.in);
		operation = new PoemOperations(dal);
		dal.readPoemsFromFile("poems.txt",operation);
	
	}
	
	public void showMenu(){
		System.out.println("Welcome");
		System.out.println("Press 1 to add a new poem");
		System.out.println("Press 2 to find the poem that has maximum occurence of word");
		System.out.println("Press 3 to list top 10 popular words");
		System.out.println("Press 4 to find the poems that begin with a given word");
		System.out.println("Press 5 to find acrostics with a given word if any");
		System.out.println("Press 6 to exit");
	}
	
	public void run() throws InvalidNameException, IOException{
	
		while(true){
			int button = - 1;
			
			showMenu();
			
			String stringFormOfButton = keyboard.nextLine();
			String r = "\\b[1-6]\\b";
			
			Pattern p = Pattern.compile(r);
			Matcher m = p.matcher(stringFormOfButton);
			
			if(m.matches())
				button = Integer.parseInt(stringFormOfButton);
			else{
				System.out.println("Please enter a valid button.");
			}
		if(button == 1){
			System.out.println("Enter a file path.(Please use this symbol double \"\\\" ");
			String filePath = keyboard.nextLine();
			//"C:\\Users\\asus\\Desktop\\poem1.txt"
			operation.addPoem(filePath, "poems.txt");
		}
		
		else if(button == 2){
			System.out.println("Enter a word");
			String word = keyboard.nextLine();
			System.out.println(operation.findPoemThatHasMaxOccurenceOfGivenWord(word));
		}
		
		else if(button == 3){
			System.out.println(operation.top10PopularWordsToString());
		}
		
		else if ( button == 4){
			System.out.println("Enter a word");
			String word = keyboard.nextLine();
			System.out.println(operation.findPoemsBeginWithGivenWord(word));
		}
		
		else if ( button == 5){
			System.out.println("We couldnt do this part.");
			//String word = keyboard.nextLine();
			//System.out.println(operation.findAcrostics(word));
		}
		
		else if ( button == 6){
			System.out.println("The End");
			break;
		}
		}
	}
}