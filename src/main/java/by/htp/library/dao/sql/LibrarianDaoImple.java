package by.htp.library.dao.sql;

import java.util.List;

import by.htp.library.dao.AbstractDao;
import by.htp.library.entity.Book;

public class LibrarianDaoImple extends AbstractDao {

	@Override
	public Boolean login(String login, String pass) {
		return super.login(login, pass);
	}

	@Override
	public int add(Object o) {
		return super.add(o);
	}

	@Override
	public List<Book> buildCatalogue() {
		return null;
	}

	@Override
	public void showCatalouge(List<Book> list) {
	}

	@Override
	public Boolean checkReader(String login, String pass) {
		return super.checkReader(login, pass);
	}

	@Override
	public Boolean returnBook(int id_book) {
		return super.returnBook(id_book);
	}

}
