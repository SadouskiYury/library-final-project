package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Book;
import by.htp.library.entity.Reader;

public abstract class AbstractDao implements Dao {

	public Boolean login(int login, String pass) {
		return null;
	}

	public List<Reader> listReaders() {
		return null;
	}

	public int add(Object o) {
		return 0;
	}

	public abstract List<Book> buildCatalogue();

	public boolean returnBook(int id_book) {
		return false;
	}

}
