package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Book;
import by.htp.library.entity.Reader;

public abstract class AbstractDao implements Dao {

	@Override
	public abstract Boolean login(String login, String pass);

	@Override
	public abstract List<Book> buildCatalogue();

	@Override
	public abstract void showCatalouge(List<Book> list);

	@Override
	public abstract Boolean checkReader(String login, String pass);

	@Override
	public Boolean returnBook(int id_book) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean add(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void showDetailsBook(int id_book) {
		throw new UnsupportedOperationException();
	}

}
