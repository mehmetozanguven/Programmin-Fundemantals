import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author MehmetOzanGuven
 *
 */
public class BookShelfMenu {
	private Book newBook;
	private Author newAuthor;
	
	
	public Boolean checkISBN(String isbn){
		
		String r = "(\\d){3}\\-(\\d){3}\\-(\\d){2}\\-(\\d){4}\\-(\\d)";
		Pattern p = Pattern.compile(r);
		Matcher m = p.matcher(isbn);
		
		if(m.matches()){
			
			return true;
		
		}
		else{
			
			return false;	
		
		}
	}
	
	public Boolean checkGenre(String genre){
		
		boolean correctInput = false;
		for (Genre eachGenre : Genre.values()) {
			if (genre.equals(String.valueOf(eachGenre))){	
				
				correctInput = true;
				break;
			}
		}
		
		return correctInput;
	}
	
	public Boolean checkPublishingYear(String year){
		
		String r = "\\b[0-9]{4}\\b";
		Pattern p = Pattern.compile(r);
		Matcher m = p.matcher(year);
		
		if(m.matches())
		
			return true;
		
		else{
			
			return false;
		
		}
	}
	
	public Boolean checkPrice(String price){
		
		String r ="\\b[0-9(.)?(,)?]+\\b";
		Pattern p = Pattern.compile(r);
		Matcher m = p.matcher(price);
		
		if(m.matches())
		
			return true;
		else{
			
			return false;
		
		}
	}	
	
	public Boolean forAuthorsCheckNameAndPlace(String name){
		
		String s = name;
		String r = "\\b[a-z|A-Z( )?]*\\b";
		Pattern p = Pattern.compile(r);
		Matcher m = p.matcher(s);
		if(m.matches()){
			return true;
		}
		else{	
			return false;
		}
	}

	public Boolean forAuthorsCheckBirthYear(String year){
		
		String r = "\\b[0-9]{4}\\b";
		Pattern p = Pattern.compile(r);
		Matcher m = p.matcher(year);
		
		if(m.matches())
		
			return true;
		else{
			
			return false;
		
		}
	}
	
	public void playScreen(){
		System.out.println(" ");
		System.out.println("--Welcome to the BookShelf X--");
		System.out.println("Enter 0 to exit");
		System.out.println("Enter 1 to add a book");
		System.out.println("Enter 2 to remove a book");
		System.out.println("Enter 3 to search a book by genre");
		System.out.println("Enter 4 to search a book by publishing year");
		System.out.println("Enter 5 to search a book by the name of the author");
		System.out.println("Enter 6 to search a book by price");
		System.out.println("Enter 7 to calculate the total price of books in the bookshelf");
		System.out.println("Enter 8 to calculate the total price of books of an author");
		System.out.println("NOTE = Please type  English characters in each input...");
		System.out.print("-->> ");
	}
	
	public void init(BookShelf bookShelf, AuthorList authorList){
		Scanner keyboard = new Scanner(System.in);
		while(true){
			
			int button = - 1;
			
			playScreen();
			
			String stringFormOfButton = keyboard.nextLine();
			String r = "\\b[0-8]\\b";
			
			Pattern p = Pattern.compile(r);
			Matcher m = p.matcher(stringFormOfButton);
			
			if(m.matches())
				button = Integer.parseInt(stringFormOfButton);
			else{
				System.out.println("Please enter a valid button.");
				continue;
			}
			
			if (button == 0){
				
				System.out.println("The End");
				System.exit(0);
			
			}
			
			else if ( button == 1){
				
				System.out.print("Enter the book name : ");
				String title = keyboard.nextLine();
				
				if(title.isEmpty() || (title.trim().length() == 0)){
					//if book has no name...
					title = "Anonymous book";
				} 
				//check book is in the bookShelf 
				if(bookShelf.isInBookList(title)){//if it is in the list
					
					System.out.println(title + " has been already added");
				}
				
				else{//if it is not in the list
						
						newBook = new Book();
						newBook.setTitle(title);
						//take input for attributes
						System.out.print("Enter the ISBN number : ");
						String isbnNumber = keyboard.nextLine();

						while(!checkISBN(isbnNumber)){
							System.out.println("You entered wrong type. It should be such as this format: ///-///-//-////-/");
							System.out.print("Enter ISBN number : ");
							isbnNumber = keyboard.nextLine();
						}
					
						newBook.setIsbn(isbnNumber);
						
						System.out.println("Please enter the genre in uppercase letter ");
						System.out.print("Enter the Genre : ");
						String genre = keyboard.nextLine();
						while(!checkGenre(genre)){
							System.out.println("Your genre value is wrong. Try again");
							System.out.print("Enter the Genre : ");
							genre = keyboard.nextLine();
						}
						
						newBook.setGenre(genre);
					
						System.out.print("Enter the publishing year : ");
						String publishingYearString = keyboard.nextLine();
						while(!checkPublishingYear(publishingYearString)){
							System.out.println("You entered wrong. Try one more");
							System.out.print("Enter the publishing year : ");
							publishingYearString = keyboard.nextLine();
						}
						
						Integer publishingYear = Integer.parseInt(publishingYearString);
						newBook.setPublishingYear(publishingYear);
						
						System.out.print("Enter publisher : ");
						String publisher = keyboard.nextLine();
						newBook.setPublisher(publisher);
						
						System.out.print("Enter the price of the book : ");
						String stringTypeOfPrice = keyboard.nextLine();
						
						while(!checkPrice(stringTypeOfPrice)){
							
							System.out.println("Price value is not compatible. Enter again");
							System.out.print("Enter the price : ");
							stringTypeOfPrice = keyboard.nextLine();
						
						}
						
						Double price = Double.parseDouble(stringTypeOfPrice);
						newBook.setPrice(price);
						System.out.println("If more than one author, please separete them with comma ");
						System.out.print("Enter Author(s) : ");
						String authors = keyboard.nextLine();
					
						StringTokenizer stringTokenizer = new StringTokenizer(authors, ",");
						while(stringTokenizer.hasMoreTokens()){
							
							String authorName = stringTokenizer.nextToken();
							
							if(authorName.isEmpty() || (authorName.trim().length() == 0)){
								//if author has no name...
								authorName = "Anonymous Author";
								
							}
							//check that author is in the list
							if(authorList.isAuthorInTheList(authorName)){
								//if it is, then just add the book
								System.out.println(authorName + " is already added");
								for(Author eachAuthor : authorList.getAuthorList()){
									
									if(eachAuthor.getName().equals(authorName)){
										
										eachAuthor.addBookToTheAuthor(newBook);
										//also add Author object to the list which is belong to Book class
										newBook.addAuthorsOfBook(eachAuthor);
									}
								}
							}//end if
							//if not
							else{
								//create new author
								newAuthor = new Author();
								//add the author to the list in the Book class 
								newBook.addAuthorsOfBook(newAuthor);
								
								authorList.addAuthorToAuthorList(newAuthor);
								System.out.println(authorName + " is the new Author");
								//add the authorList
								newAuthor.setName(authorName);
								//take input for attributes
								System.out.print("Enter the birth place : ");
								String birthPlace = keyboard.nextLine();
								while(!forAuthorsCheckNameAndPlace(birthPlace)){
									
									System.out.println("Your type is not that we are looking for..");
									System.out.print("Enter the birth place : ");
									birthPlace = keyboard.nextLine();
									
								}
								newAuthor.setBirthPlace(birthPlace);
								
								newAuthor.setBirthPlace(birthPlace);
								
								System.out.print("enter the year of birth : ");
								String stringTypeOfYear = keyboard.nextLine();
								while(!forAuthorsCheckBirthYear(stringTypeOfYear)){
									
									System.out.println("Please Enter the correct one");
									System.out.print("Enter birth year : ");
									stringTypeOfYear = keyboard.nextLine();
								}
							
								Integer yearOfBirth = Integer.parseInt(stringTypeOfYear);
								newAuthor.setBirthYear(yearOfBirth);
								newAuthor.addBookToTheAuthor(newBook);
							}//end else
						}//end while
					}//end else
					bookShelf.addBookToBookList(newBook);//add newBook to the BookShelf
				}//end button ==1
			else if ( button == 2 ) {
				
				System.out.print("Enter the ISBN of the book that you want to remove : ");
				String isbn = keyboard.nextLine();
				//if there is no book which has that isbn
				if(!bookShelf.checkISBNforRemove(isbn)){
					
					System.out.println("There is no book for this " + isbn);
				
				}//end if
				else{//if there is a book
					//reach to Author(s) for that book
					ArrayList<Book> copyBookList = new ArrayList<Book>(bookShelf.getBookList());//to avoid ConcurrentModificationException
					for(Book eachBook : copyBookList){
						
						if(eachBook.getIsbn().equals(isbn)){
							
							ArrayList<Author> copyAuthorsOfBook = new ArrayList<Author>(eachBook.getListAuthorsOfBook());//to avoid ConcurrentModificationException
							
							for(Author eachAuthorOfOneBook : copyAuthorsOfBook){
								
								if(eachAuthorOfOneBook.lastBookOfAuthor()){
									//if the last book of that Author, then remove that Author from the authorList
									authorList.removeAuthorToAuthorList(eachAuthorOfOneBook);

								}
								else{
									//if not the last book of that author, then just delete book
									eachAuthorOfOneBook.removeBookToTheAuthor(eachBook.getIsbn());
									//authorList.removeBook(eachAuthorOfOneBook, eachBook.getTitle());
								}
								eachBook.removeAuthorsOfBook(eachAuthorOfOneBook);

							}
							
							bookShelf.removeBookFromBookShelf(eachBook);	
						}//end if
						//remove the book from bookShelf
						
					}//end for
					
				}//end else
			}//end button == 2
			
			else if(button == 3){
				System.out.print("Enter a genre that you want to list books by that genre : ");
				String strGenre = keyboard.nextLine();
				
				Genre[] arrayOfEnumTypes = Genre.values();
				try{
				
					for(Genre eachGenre:arrayOfEnumTypes){
				
						if(eachGenre == Genre.valueOf(strGenre)){
					
							System.out.print("The list of books which has genre " + strGenre + ": ");
					
							bookShelf.searchByGenre(Genre.valueOf(strGenre));
					
							break;
							}
						}
					}
				catch(Exception e){
					System.out.println("There is no genre recorded in this BookShelf");
					}
				}
			
			else if(button == 4){
				System.out.print("Enter a year that you want to list books by that year : ");
				String stringFormOfYear = keyboard.nextLine();
				
				if(checkPublishingYear(stringFormOfYear)){
					
					int year = Integer.parseInt(stringFormOfYear);
					System.out.print("The list of books which published in the year thay you have typed : ");
					bookShelf.searchByPublishingYear(year);
				
				}
				else{
					
					System.out.println("Please enter a valid year.");
					continue;
				}	
			}
			
			else if(button == 5){
				System.out.print("Enter a name of the author to list books by the author : ");
				String author = keyboard.nextLine();
				
				if(forAuthorsCheckNameAndPlace(author)){
					
					System.out.println("The list of books which was written by the author that you have typed : ");
					bookShelf.searchByAuthorName(author);
				}
				else{	
					
					System.out.println("Please enter a valid name");
				}
				
			}
			else if(button == 6){
				
				Double lower=0.0;
				Double upper=0.0;
				
				System.out.print("Enter a lower boundary : ");
				String strLower = keyboard.nextLine();
				
				try{
					
					lower = Double.parseDouble(strLower);
				
				}catch(Exception e){
					
					System.out.println("Please enter a valid boundary");continue;
				}
				
				System.out.print("Enter an upper boundary : ");
				String strUpper = keyboard.nextLine();
				
				try{
					
					upper = Double.parseDouble(strUpper);
				
				}catch(Exception e){
					
					System.out.println("Please enter a valid boundary");
					continue;
					
				}
				
				System.out.print("The list of books which has the price in the interval that you have typed : ");
				bookShelf.searchByPrice(lower, upper);
				
			}
			
			else if(button == 7){
				
				System.out.print("The value of total price of books in the BookShelf X is : ");
				System.out.println(bookShelf.calculateTotalPriceInTheBookShelf());
			}
			
			else if(button == 8){
				
				int control = 0;
				System.out.print("Enter the name of the author that you want to calculate the total price of books of him/her : ");
				String author = keyboard.nextLine();
				
				for(Author eachAuthor:authorList.getAuthorList()){
				
					if(eachAuthor.getName().equals(author)){
					
						control = 1;
				
						System.out.print("It's : ");
				
						System.out.println(authorList.calculateTotalPriceOfBooksOfAnAuthor(author));
				
					}
					
				}
				
				if(control==0){
					
					System.out.println("the author name that you typed is not in author list");
				}
			}
		
		
		}//end while(true)
					
	}

}