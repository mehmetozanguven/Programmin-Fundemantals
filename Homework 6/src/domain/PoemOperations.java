package domain;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

import javax.naming.InvalidNameException;

import fileAccessLayer.*;

public class PoemOperations {
	
	private ArrayList<Poem> poemList = new ArrayList<Poem>();
	private DataAccessLayer copyData ;
	
	public PoemOperations(DataAccessLayer dal) {
		copyData = dal;
	}
	
	public void addPoemToPoemList(Poem poem){
		poemList.add(poem);
	}
	
	public ArrayList<Poem> getPoemList(){
		
		return poemList;
	
	}

	
	public String findPoemThatHasMaxOccurenceOfGivenWord(String word) throws InvalidNameException{
		int maxOccurence = 0;
		int occurence = 0;
		String poem="There is no poem that contains the word you typed.";
			for(Poem eachPoem : poemList){
				if(eachPoem.getWordsInThePoem().contains(word)){
					occurence = 0;
					for(String eachWord: eachPoem.getWordsInThePoem()){
						if(eachWord.equals(word)){
							occurence++;
						}
					}
				}
				if(occurence >maxOccurence){
					maxOccurence = occurence;
					poem = eachPoem.getTitle();
				}
			}

		
		return poem;
	}
	
	public ArrayList<Integer> numberOfOccurencesOfWordsInList(){
		ArrayList<Integer> allNumbers = new ArrayList<Integer>();
		
		for(String key : copyData.getHashMap1().keySet()){
			allNumbers.add(copyData.getHashMap1().get(key).size());
		}
		
		Collections.sort(allNumbers, Collections.reverseOrder());
		return allNumbers;
	}
	
	public ArrayList<String> top10PopularWords(){
		ArrayList<Integer> listNumberOfEachWord = numberOfOccurencesOfWordsInList();
		ArrayList<String> list10Words = new ArrayList<String>();
		for(int i = 0; i < 10; i++){
			for(String key : copyData.getHashMap1().keySet()){
				if(copyData.getHashMap1().get(key).size() == listNumberOfEachWord.get(i)){
					if(!list10Words.contains(key)){
						list10Words.add(key);
					}
					else{
						continue;
					}
				}
			}
		}
		return list10Words;
	}		
	
	public String top10PopularWordsToString(){
		StringBuilder return_str = new StringBuilder();
		for(String words : top10PopularWords()){
			return_str.append("'");
			return_str.append(words);
			return_str.append("'");
			return_str.append(" ");
		}
		return return_str.toString();
	}
	
	public ArrayList<String> findPoemsBeginWithGivenWord(String word){
		ArrayList<String> listOfPoem = new ArrayList<String>();
		try{
			word = copyData.convertToEnglishCharacter(word);
		
		for(Poem poem:copyData.getHashMap2().get(word)){
			//System.out.println(poem.getTitle());
			if(poem.getFirstWordOfPoem().equals(word)){
				//System.out.println("...");
				listOfPoem.add(poem.getTitle());
			}
		}

		}catch(Exception e){
			System.out.println("There is no poem that begin with a word you typed");
		}
		return listOfPoem;
		}

	public void addPoem(String filePath, String originalFile) throws IOException{
		copyData.appendPoemToTheFile(filePath, originalFile,this);
	}
		
}