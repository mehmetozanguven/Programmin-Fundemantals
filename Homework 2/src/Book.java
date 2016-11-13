import java.util.ArrayList;
/**
 * 
 * @author MehmetOzanGuven
 *
 */
public class Book {
	
	private Genre genre;
	private String title;
	private String isbn;
	private String publisher;	
	private int publishingYear;
	private double price;
	private ArrayList<Author> authorsOfBook = new ArrayList<Author>();

	public Book(){
		
	}	
	public Boolean isAuthorInTheList(String authorName){
		
		boolean correct = false;
		
		for(Author eachAuthor : authorsOfBook){
			
			if(eachAuthor.getName().equals(authorName)){
				
				correct = true;
				break;
			}
		}
		
		return correct;
	}
	public ArrayList<Author> getAuthorsOfBook() {
		
		return authorsOfBook;
		
	}

	public void addAuthorsOfBook(Author newAuthor) {
		
		authorsOfBook.add(newAuthor);
		
	}
	
	public void removeAuthorsOfBook(Author removeAuthor){
		
		authorsOfBook.remove(removeAuthor);
	}
	
	
	public Genre getGenre() {
		
		return genre;
	
	}
	
	public void setGenre(String genre) {
			
		this.genre = Genre.valueOf(genre);
	
	}
	
	public String getTitle() {
		
		return title;
	
	}
	
	public void setTitle(String title) {
		
		this.title = title;
	
	}
	
	public String getIsbn() {
		
		return isbn;
	
	}
	
	public void setIsbn(String isbn) {
		
		this.isbn = isbn;
				
	}
	
	public String getPublisher() {
		
		return publisher;
	
	}
	
	public void setPublisher(String publisher) {
		
		this.publisher = publisher;
	
	}
	
	public int getPublishingYear() {
		
		return publishingYear;
	
	}
	
	public void setPublishingYear(int publishingYear) {
			
		this.publishingYear = publishingYear;
		
	}
	
	public double getPrice() {
		
		return price;
	
	}
	
	public void setPrice(double price) {
		
		this.price = price;
		
	}
	
	public ArrayList<Author> getListAuthorsOfBook(){
		
		return authorsOfBook;
	}
	
}