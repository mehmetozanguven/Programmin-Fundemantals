import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * 
 * @author MehmetOzanGuven
 *
 */

public class DataAccessLayer {
		
	public void readBooks(BookShelf bookShelf, AuthorList authorList){
		try{
			
			Scanner keyboardForBooks = new Scanner(new File("books.dat"));
			
			while(keyboardForBooks.hasNextLine()){
				
				String line = keyboardForBooks.nextLine();
				Book book = new Book();
				StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
				
				while(stringTokenizer.hasMoreTokens()){
					
					book.setTitle(stringTokenizer.nextToken());
					book.setIsbn(stringTokenizer.nextToken());
					
					String genreLowerCase = stringTokenizer.nextToken();
					//to avoid "Ý"
					String genre = genreLowerCase.toUpperCase(Locale.ENGLISH);
					book.setGenre(genre);
					
					String year = stringTokenizer.nextToken();
					int publishingYear = Integer.parseInt(year);
					book.setPublishingYear(publishingYear);
					
					String publisher = stringTokenizer.nextToken();
					book.setPublisher(publisher);
					
					String strPrice = stringTokenizer.nextToken();
					Double price  = Double.parseDouble(strPrice);
					book.setPrice(price);
					
					String authorElement = stringTokenizer.nextToken();
					StringTokenizer stringTokenizerForAuthor = new StringTokenizer(authorElement, " \n");
					
					Author author = new Author();
					author.setName(stringTokenizerForAuthor.nextToken()  + " " + stringTokenizerForAuthor.nextToken());
					
					book.addAuthorsOfBook(author);
					
					bookShelf.addBookToBookList(book);
				}
			}
			
			keyboardForBooks.close();
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());	
		}
	}
	
	public void readAuthors(BookShelf bookShelf, AuthorList authorList){
		
		try{
			
			Scanner keyboardForAuthors = new Scanner(new File("authors.dat"));
		
	
			while(keyboardForAuthors.hasNextLine()){
				
				String line = keyboardForAuthors.nextLine();

				StringTokenizer stringTokenizerTwo = new StringTokenizer(line, ",");
		
				while(stringTokenizerTwo.hasMoreTokens()){
					String eachElement = stringTokenizerTwo.nextToken();
					
					int count = 0;
					
					for(Book eachBook:bookShelf.getBookList()){
						
						if(count==1){
							continue;
							
						}
												
						for(Author eachAuthor:eachBook.getAuthorsOfBook()){
							
							String authorName = eachAuthor.getName();
							if(authorName.equals(eachElement)){
								
								String  dateOfBirth= stringTokenizerTwo.nextToken();
								int birth = Integer.parseInt(dateOfBirth);
								eachAuthor.setBirthYear(birth);
								
								String place = stringTokenizerTwo.nextToken();
								eachAuthor.setBirthPlace(place);
								
								stringTokenizerTwo.nextToken();
								
								eachAuthor.addBookToTheAuthor(eachBook);
								authorList.addAuthorToAuthorList(eachAuthor);
								count=1;
							}
						}
					
					}
				
				}

			}
		
			keyboardForAuthors.close();
	
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
	}
	
}