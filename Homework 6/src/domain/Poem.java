package domain;

import java.util.ArrayList;

public class Poem {

	private ArrayList<String> firstWordsOfEachLineInPoem;
	private String firstWordOfPoem;
	private String title;
	private int numOfWordsOnThePoem;
	private int orderInTheFile;
	private ArrayList<String> wordsInThePoem;
	
	public Poem(){
	
		wordsInThePoem = new ArrayList<String>();
		firstWordsOfEachLineInPoem = new ArrayList<String>();
	
	}
	
	public ArrayList<String> getWordsInThePoem() {
	
		return wordsInThePoem;
	
	}

	public void addWordToThePoemList(String word) {
	
		wordsInThePoem.add(word);
		numOfWordsOnThePoem++;
	
	}

	public String getTitle() {
	
		return title;
	
	}
	public void setTitle(String title) {
	
		this.title = title;
	
	}
	public int getNumOfWordsInThePoem() {
		
		return numOfWordsOnThePoem;
	
	}

	public int getOrderInTheFile() {
	
		return orderInTheFile;
	
	}
	public void setOrderInTheFile(int orderInTheFile) {
	
		this.orderInTheFile = orderInTheFile;
	
	}

	public String getFirstWordOfPoem() {
	
		return firstWordOfPoem;
	
	}

	public void setFirstWordOfPoem(String firstWordOfPoem) {
	
		this.firstWordOfPoem = firstWordOfPoem;
	
	}

	public ArrayList<String> getFirstWordsOfEachLineInPoem() {
	
		return firstWordsOfEachLineInPoem;
	
	}

	public void addFirstWordsOfEachLineInPoem(String word) {
		
		firstWordsOfEachLineInPoem.add(word);
	
	}
	
}