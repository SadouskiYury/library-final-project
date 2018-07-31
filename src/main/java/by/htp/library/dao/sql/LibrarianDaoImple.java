package by.htp.library.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.htp.library.dao.AbstractDao;
import by.htp.library.dao.sql.util.ConnectionDB;
import by.htp.library.dao.sql.util.SqlPropertyManager;
import by.htp.library.entity.Book;
import by.htp.library.entity.Librarian;
import by.htp.library.entity.Reader;

public class LibrarianDaoImple extends AbstractDao {
	private static ResultSet rs;

	@Override
	public Boolean login(String login, String pass) {
		return Librarian.LOGIN.getValue().equals(login) && Librarian.PASSWORD.getValue().equals(pass);
	}

	@Override
	public Boolean returnBook(int id_book) {
		return super.returnBook(id_book);
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
		return false;
	}

	@Override
	public Boolean add(Object o) {
		Reader reader = new Reader();
		Book book = new Book();
		if (o == null)
			return false;
		else if (o.getClass().equals(reader.getClass())) {
			reader = (Reader) o;
			return insertReader(reader);
		} else if (o.getClass() == book.getClass()) {
			System.out.println("--------Book___");
			return true;
		}
		return false;
	}

	private Boolean insertReader(Reader r) {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQuertReader())) {
			ps.setString(1, r.getName());
			ps.setString(2, r.getSurname());
			ps.setInt(3, r.getNumberPhone());
			ps.setString(4, r.getPassword());
			ps.setString(5, r.getNumberLibraryCard());
			ps.executeUpdate();
			System.out.println("Registartion completed "+r.getName());
			return true;
		} catch (SQLException e) {
			System.err.println("Impossible add new reader!");
			return false;
		}

	}
}
