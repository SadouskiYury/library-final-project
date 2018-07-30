package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Book;
import by.htp.library.entity.Reader;

public interface Dao {
	// reader or librarian
	Boolean login(int login, String pass);

	// будет содержать лист сотрудников либо кталог книг
	List<Reader> listReaders();

	// add reader or book
	int add(Object o);

	List<Book> buildCatalogue();

	boolean returnBook(int id_book);
}
