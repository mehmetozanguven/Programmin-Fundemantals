/**
 * 
 * @author MehmetOzanGuven
 *
 */
public class BookShelfApp {
	public static void main(String[] args) {
		AuthorList authorList = new AuthorList();
		BookShelf bookShelf = new BookShelf();
		BookShelfMenu menu = new BookShelfMenu();
		DataAccessLayer dataAccessLayer = new DataAccessLayer();
		dataAccessLayer.readBooks(bookShelf, authorList);
		dataAccessLayer.readAuthors(bookShelf, authorList);
		menu.init(bookShelf, authorList);
		
		
	}
}
