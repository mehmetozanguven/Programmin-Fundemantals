package fileAccessLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import domain.*;

public class DataAccessLayer {

	private Scanner file;
	private FileWriter fileWriter;
	private StringTokenizer stringTokenizer;
	private HashMap<String, ArrayList<Integer>> hashmap1;
	private HashMap<String, ArrayList<Poem>> hashmap2;
	
	public DataAccessLayer() {
	
		hashmap1 = new HashMap<String, ArrayList<Integer>>();
		hashmap2 = new HashMap<String, ArrayList<Poem>>();
	
	}
		
	public HashMap<String, ArrayList<Poem>> getHashMap2(){
	
		return hashmap2;
	
	}
	
	public HashMap<String, ArrayList<Integer>> getHashMap1(){
	
		return hashmap1;
	
	}
	
	public void readPoemsFromFile(String fileName, PoemOperations operation){
		Poem poem = null;
		file = null;
		int poemCount = 0;
		String word;
		try{
			file = new Scanner(new File(fileName));
			int numberOfPosition= 1;
			boolean isNewPoem = false;
			while(file.hasNextLine()){
				boolean isFirstWordOfLine = true; 
				String line = file.nextLine();
				if(line.contains("Title")){
					isNewPoem = true;
					poemCount++;
					poem = new Poem();
					operation.addPoemToPoemList(poem);
					stringTokenizer = new StringTokenizer(line,":");
					stringTokenizer.nextToken();
					poem.setTitle(stringTokenizer.nextToken());
					poem.setOrderInTheFile(poemCount);
					
				}
				else{
					stringTokenizer = new StringTokenizer(line,"\n ,.:!?;-&()");
					int totalCount = stringTokenizer.countTokens();
					
					for(int countForTotalCount = 0; countForTotalCount < totalCount; countForTotalCount++){
						if(isFirstWordOfLine == true){
							word = convertToEnglishCharacter(stringTokenizer.nextToken());
							poem.addFirstWordsOfEachLineInPoem(word);
							if(isNewPoem == true){
							poem.setFirstWordOfPoem(word);
							isNewPoem = false;
							}
							
							doesPoemHaveWord(word, poem);
							if(hashmap1.containsKey(word)){
								hashmap1.get(word).add(-1*numberOfPosition);
							}
							else{
							
								ArrayList<Integer> list = new ArrayList<Integer>();
								list.add(-1*numberOfPosition);
								hashmap1.put(word, list);
							
						}//else(if word not in hashmap)
							
							isFirstWordOfLine = false;
							numberOfPosition++;
						}//if(isfirst == true)
						else{
							word = convertToEnglishCharacter(stringTokenizer.nextToken());
							doesPoemHaveWord(word, poem);
							if(hashmap1.containsKey(word)){
								hashmap1.get(word).add(numberOfPosition);
							}
							else{
							
								ArrayList<Integer> list = new ArrayList<Integer>();
								list.add(numberOfPosition);
								hashmap1.put(word, list);
							}//else(if word not in hm)
							numberOfPosition++;
						}// else(isfirst==false)
						
						poem.getWordsInThePoem().add(word);
						
					}//for
		
					} // else(if there is no title)
					
				
				
			} // end while hasnextline.
			
			
		}catch(FileNotFoundException e){
			e.getMessage();
			System.exit(0);
		}
		
	} // end of read method.
	
	public void doesPoemHaveWord(String word, Poem poem){
		if(hashmap2.containsKey(word)){
			if(!hashmap2.get(word).contains(poem)){
				hashmap2.get(word).add(poem);
			}
		}
		else{
			ArrayList<Poem> listOfPoems = new ArrayList<Poem>();
			listOfPoems.add(poem);
			hashmap2.put(word, listOfPoems);
		}
	}
	
	public String convertToEnglishCharacter(String word){
		
		String convertedWord = "";
		for(Character eachCharInWord : word.toCharArray()){
			if(eachCharInWord == 'I'){
				eachCharInWord = 'i';
			}
			else{
				eachCharInWord = eachCharInWord.toLowerCase(eachCharInWord);
			}
			convertedWord = convertedWord+eachCharInWord;
		}
		return convertedWord;
	}
	
	public void appendPoemToTheFile(String filePath, String originalFile,PoemOperations operation) throws IOException{
		Poem poem = null;
		String word;
		file = new Scanner(new File(filePath));
		fileWriter = new FileWriter(originalFile,true);
					int numberOfPosition= 1;
					boolean isNewPoem = false;
					int poemCount = operation.getPoemList().size();
					while(file.hasNextLine()){
						boolean isFirst = true; 
						String line = file.nextLine();
						if(line.contains("Title")){
							isNewPoem = true;
							poemCount++;
							poem = new Poem();
							operation.addPoemToPoemList(poem);
							stringTokenizer = new StringTokenizer(line,":\n");
							String stringTitle = stringTokenizer.nextToken();
							fileWriter.write(stringTitle + ":");
							String title = stringTokenizer.nextToken();
							fileWriter.write(title);
							poem.setTitle(title);
							poem.setOrderInTheFile(poemCount);
							
						}
						else{
							stringTokenizer = new StringTokenizer(line,"\n ,.:!?;-&()");
							int totalCount = stringTokenizer.countTokens();
							
							for(int countForTotalCount = 0; countForTotalCount < totalCount; countForTotalCount++){
								if(isFirst == true){
									String firstWordOfLine = stringTokenizer.nextToken();
									fileWriter.write("\n" + firstWordOfLine + " ");
									word = convertToEnglishCharacter(firstWordOfLine);
									poem.addFirstWordsOfEachLineInPoem(word);
									
									if(isNewPoem == true){
									poem.setFirstWordOfPoem(word);
									isNewPoem = false;
									
									}
									
									doesPoemHaveWord(word, poem);
									if(hashmap1.containsKey(word)){
										hashmap1.get(word).add(-1*numberOfPosition);
									}
									else{
									
										ArrayList<Integer> list = new ArrayList<Integer>();
										list.add(-1*numberOfPosition);
										hashmap1.put(word, list);
									
								}//else(if word not in hm)
									
									isFirst = false;
									numberOfPosition++;
								}//if(isfirst == true)
								else{
									String wordNotFirst = stringTokenizer.nextToken();
									fileWriter.write(wordNotFirst + " ");
									word = convertToEnglishCharacter(wordNotFirst);
									doesPoemHaveWord(word, poem);
									if(hashmap1.containsKey(word)){
										hashmap1.get(word).add(numberOfPosition);
									}
									else{
									
										ArrayList<Integer> list = new ArrayList<Integer>();
										list.add(numberOfPosition);
										hashmap1.put(word, list);
									}//else(if word not in hm)
									numberOfPosition++;
								}// else(isfirst==false)
								
								poem.getWordsInThePoem().add(word);
								
							}//for
				
							} // else(if there is no title)
							
						
						
					} // end while hasnextline.
					
					
		
		fileWriter.close();
		file.close();
		
		
	} // end of append method.
	
}
