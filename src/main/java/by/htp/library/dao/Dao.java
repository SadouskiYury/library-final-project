package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Book;
import by.htp.library.entity.Reader;

public interface Dao {
	// reader or librarian
	Boolean login(String login, String pass);

	// будет содержать лист сотрудников либо кталог книг
	List<Reader> listReaders();

	// add reader or book
	int add(Object o);

	List<Book> showCatalogue();

	boolean returnBook(int id_book);
}
