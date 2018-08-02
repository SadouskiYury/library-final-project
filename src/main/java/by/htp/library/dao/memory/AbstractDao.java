package by.htp.library.dao.memory;

import java.util.List;

import by.htp.library.dao.Dao;
import by.htp.library.entity.Book;

public abstract class AbstractDao implements Dao {

	@Override
	public Boolean login(String login, String pass) {
		return null;
	}

	@Override
	public Boolean add(Object o) {
		return null;
	}

	@Override
	public Boolean issueBook(int id, String NumberLibraryCard) {
		return null;
	}

	@Override
	public List<Book> buildCatalogue() {
		return null;
	}

	@Override
	public void showCatalouge(List<Book> list) {
	}

	@Override
	public Boolean returnBook(int id_book) {
		return null;
	}

	@Override
	public Boolean checkReader(String login) {
		return null;
	}

	@Override
	public void showDetailsBook(int id_book) {
	}

}
