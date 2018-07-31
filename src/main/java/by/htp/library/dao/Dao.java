package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Book;
import by.htp.library.entity.Reader;

public interface Dao {
	// reader or librarian
	Boolean login(String login, String pass);

	public int add(Object o);

	List<Book> buildCatalogue();

	void showCatalouge(List<Book> list);

	Boolean returnBook(int id_book);

	Boolean checkReader(String login, String pass);
}
