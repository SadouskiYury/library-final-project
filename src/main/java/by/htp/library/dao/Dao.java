package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Book;
import by.htp.library.entity.Reader;

public interface Dao {
	// reader or librarian
	void login(Object o);

	int addNewReader(Reader r);

	// будет содержать лист сотрудников либо кталог книг
	List<Object> list();

	// add reader or book
	int add(Object o);

	List<Book> listIssuedBook();

}
