package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Book;
import by.htp.library.entity.Reader;

public interface Dao {
	Boolean login(String login, String pass);

	Boolean add(Object o);

	List<Book> buildCatalogue();

	void showCatalouge(List<Book> list);

	Boolean returnBook(int id_book);

	Boolean checkReader(String login, String pass);

	void showDetailsBook(int id_book);
}
