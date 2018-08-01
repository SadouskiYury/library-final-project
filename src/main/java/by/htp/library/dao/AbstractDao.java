package by.htp.library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import by.htp.library.dao.sql.util.ConnectionDB;
import by.htp.library.dao.sql.util.EnumNameColumn;
import by.htp.library.dao.sql.util.SqlPropertyManager;
import by.htp.library.entity.Author;
import by.htp.library.entity.Book;

public abstract class AbstractDao implements Dao {

	@Override
	public abstract Boolean login(String login, String pass);

	@Override
	public Boolean checkReader(String login) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean returnBook(int id_book) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean issueBook(int id, String NumberLibraryCard) {
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

	@Override
	public void showCatalouge(List<Book> list) {
		System.out.printf("%-10s%-20s%-35s%n", "Book id", "Title ", "Author ");
		System.out.println("----------------------------------------------------------------------------------");
		for (Book l : list)
			System.out.printf("%-10d%-20s%-35s%n", l.getId_book(), l.getTitle(), l.getAuthor().getSurname());
		System.out.println("----------------------------------------------------------------------------------");
	}

	@Override
	public List<Book> buildCatalogue() {
		List<Book> list = new ArrayList<>();
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryBook())) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(buildBook(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Book buildBook(ResultSet rs) throws SQLException {
		Book book = new Book();
		book.setTitle(rs.getString(EnumNameColumn.BOOK_TITLE.getValue().trim()));
		book.setId_book(rs.getInt(EnumNameColumn.BOOK_ID.getValue()));
		book.setAuthor(buildAuthor(rs));
		book.setType(rs.getString(EnumNameColumn.BOOK_TYPE.getValue()));
		book.setPreface(rs.getString(EnumNameColumn.BOOK_PREFACE.getValue()));
		return book;
	}

	private Author buildAuthor(ResultSet rs) throws SQLException {
		Author author = new Author();
		GregorianCalendar birthday = new GregorianCalendar();
		birthday.setTime(rs.getDate(EnumNameColumn.AUTHOR_BIRTHDAY.getValue()));
		author.setName(rs.getString(EnumNameColumn.AUTHOR_NAME.getValue().trim()));
		author.setMidlenme(rs.getString(EnumNameColumn.AUTHOR_MIDLENAME.getValue().trim()));
		author.setSurname(rs.getString(EnumNameColumn.AUTHOR_SURNAME.getValue().trim()));
		author.setBirthDate(birthday);
		author.setId(rs.getInt(EnumNameColumn.AUTHOR_ID.getValue()));
		return author;
	}

}
