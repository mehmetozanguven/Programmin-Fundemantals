import java.util.*;

/**
 * 
 * @author MehmetOzanGuven
 *
 */
public class AuthorList {
	private ArrayList<Author> authorList = new ArrayList<Author>();

	
	public AuthorList(){
			
	}
	
	public Boolean isAuthorInTheList(String authorName){
		
		boolean correct = false;
		
		for(Author eachAuthor : authorList){
			
			if(eachAuthor.getName().equals(authorName)){
				
				correct = true;
				break;
			}
		}
		
		return correct;
	}
	
	public void addAuthorToAuthorList(Author author){
		
		authorList.add(author);
		
	}
	
	public void removeAuthorToAuthorList(Author author){
		
		authorList.remove(author);
	}
	
	public void removeBook(Author author, String title){
		
		author.removeBookToTheAuthor(title);
	}
	
	public ArrayList<Author> getAuthorList() {
		
		return authorList;
	
	}
	
	public Double calculateTotalPriceOfBooksOfAnAuthor(String author){
		
		double totalPriceOfBooksOfAnAuthor=0;
		
		for(Author eachAuthor:authorList){
			
			if(eachAuthor.getName().equals(author)){
			
				for(Book eachBooksOfAuthor : eachAuthor.getBooksOfAuthor()){

						totalPriceOfBooksOfAnAuthor += eachBooksOfAuthor.getPrice();
					
					}
				}
			}
		
		return totalPriceOfBooksOfAnAuthor;
	}
}