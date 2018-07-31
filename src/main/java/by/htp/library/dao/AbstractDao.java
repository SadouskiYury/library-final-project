package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Book;
import by.htp.library.entity.Reader;

public abstract class AbstractDao implements Dao {

	public Boolean login(String login, String pass) {
		return null;
	}

	public List<Reader> listReaders()  {
//		throw new NoSuchMethodException();
		return null;

	}

	@Override
	public Boolean checkReader(String login, String pass) {
		return null;
	}

	public int add(Object o) {
		return 0;
	}

	public abstract List<Book> buildCatalogue();

	public Boolean returnBook(int id_book) {
		return false;
	}

}
