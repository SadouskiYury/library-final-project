package by.htp.library.dao.sql;

import java.util.List;

import by.htp.library.dao.AbstractDao;
import by.htp.library.entity.Book;
import by.htp.library.entity.EnumLibrarian;
import by.htp.library.entity.Reader;

public class LibrarianDaoImple extends AbstractDao {

	@Override
	public Boolean login(String login, String pass) {
		if (login.equals(EnumLibrarian.LOGIN) && pass.equals(EnumLibrarian.PASSWORD))
			return true;
		else
			return false;
	}

	@Override
	public int add(Object o) {
		return super.add(o);
	}

	@Override
	public List<Book> showCatalogue() {
		return super.showCatalogue();
	}

	@Override
	public boolean returnBook(int id_book) {
		return super.returnBook(id_book);
	}

	public List<Reader> listReaders() {
		return null;
	}

}
