package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Book;

public abstract class AbstractDao implements Dao {

	public abstract Boolean login(String login, String pass);

	public List<Object> list() {
		return null;
	}

	public int add(Object o) {
		return 0;
	}

	public List<Book> showCatalogue() {
		return null;
	}

	public boolean returnBook(int id_book) {
		return false;
	}

}
