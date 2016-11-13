import java.util.*;
//
/**
 *
 * @author MehmetOzanGuven
 *
 */
public class BookShelf {
	private  ArrayList<Book> bookList = new ArrayList<Book>();
	
	public void addBookToBookList(Book book){
	
		bookList.add(book);
	
	}
	
	public Boolean isInBookList(String book){
		
		boolean correct = false;
		
		for(Book eachBook: bookList){
			
			if(eachBook.getTitle().equals(book)){
				
				correct = true;
				break;
			}
		}
		
		return correct;
	}
	
	public Boolean checkISBNforRemove(String isbn){
		
		boolean correct = false;
		
		for(Book eachBook : bookList){
			
			if(eachBook.getIsbn().equals(isbn)){
				
				correct = true;
				break;
			}
		}
		
		return correct;
	}
	
	public void removeBookFromBookShelf(Book book){
		
		bookList.remove(book);
	}
	
	public ArrayList<Book> getBookList() {
		
		return bookList;
	
	}
	
	public void searchByGenre(Genre genre){
		
		int count = 0;
		
		for(Book eachBook : bookList){
			
			if(eachBook.getGenre() == genre){
				
				count++;
				System.out.print("|" + eachBook.getTitle() + "|" + " ");
			
			}
		}
		
		if(count==0){System.out.println("There is no book of that genre that you have typed.");}
	}
	
	public void searchByPublishingYear(int year){
		
		boolean isTrue = false;
		
		for(Book eachBook:bookList){
			
			if(eachBook.getPublishingYear() == year){
				
				isTrue = true;
				
				System.out.print("|" + eachBook.getTitle() + "|" + " ");
			
			}
		}
		
		if(isTrue == false){System.out.println("There is no book published in the year you have typed.");}
		
	}
	
	public void searchByPrice(double lower, double upper){
		boolean isTrue = false;
		
		for(Book eachBook : bookList){
			
			if( (lower<=eachBook.getPrice()) && (eachBook.getPrice()<upper)){
				
				isTrue = true;
				System.out.print("|" + eachBook.getTitle() + "|" + " ");
			
			}
		}
		
		if(isTrue == false){System.out.println("There is no book in the price interval that you have typed.");}
	}
	
	public void searchByAuthorName(String author){
		boolean isTrue = false;
		
		for(Book eachBook : bookList){
			
			for(Author eachAuthor:eachBook.getAuthorsOfBook()){
				
				if(eachAuthor.getName().equals(author)){
					
					isTrue = true;
					System.out.println(eachBook.getTitle());
				}
			}
		}
		
		if(isTrue == false){System.out.println("There is no book of that author name that you have typed.");}
	}
	
	public Double calculateTotalPriceInTheBookShelf(){
		
		double totalPrice = 0;
		
		for (Book eachBook : bookList){
			
			totalPrice += eachBook.getPrice();
		
		}
		
		return totalPrice;
	}
	

	
}