import java.util.ArrayList;
/**
 * 
 * @author MehmetOzanGuven 
 *
 */
public class Author {
	private String name;
	private String birthPlace;
	private int birthYear;
	
	private ArrayList<Book> booksOfEachAuthor = new ArrayList<Book>();

	public Author(){
		 
	}
	
	public ArrayList<Book> getBooksOfAuthor(){
		
		return booksOfEachAuthor;
	}

	public void addBookToTheAuthor(Book newBook){

		booksOfEachAuthor.add(newBook);
		           
	}
	
	public void removeBookToTheAuthor(String isbn){
		ArrayList<Book> copyOfEachAuthorList = new ArrayList<Book>(booksOfEachAuthor);
		
		for(Book book:copyOfEachAuthorList){
			
			if(book.getIsbn().equals(isbn)){
				
				booksOfEachAuthor.remove(book);}
		}
	}
	
	public Boolean lastBookOfAuthor(){
		
		boolean correct = false;
		
		if(booksOfEachAuthor.size() == 1)
			
			correct = true;
		
		return correct;
	}
	
	public Boolean hasAuthorTheBook(String book){
		
		boolean correct = false;
		
		for(Book eachBook : booksOfEachAuthor){
			
			if(eachBook.getTitle().equals(book)){
				
				correct = true;
				break;
			}
			
			else{
				
				correct = false;
			}
			
		}
		
		return correct;
	}
	
	public String getName() {
		
		return name;
	
	}
	
	public void setName(String name) {
		
		this.name = name;
	
	}
	
	public String getBirthPlace() {
		
		return birthPlace;
	
	}
	
	public void setBirthPlace(String birthPlace) {
				
		this.birthPlace = birthPlace;
	
	}
	
	public int getBirthYear() {
		
		return birthYear;
	
	}
	
	public void setBirthYear(int birthYear) {
		
		this.birthYear = birthYear;
	
	}
	
	
}