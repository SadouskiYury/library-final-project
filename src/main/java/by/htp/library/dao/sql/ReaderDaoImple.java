package by.htp.library.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import by.htp.library.dao.AbstractDao;
import by.htp.library.dao.sql.util.ConnectionDB;
import by.htp.library.dao.sql.util.SqlPropertyManager;
import by.htp.library.entity.Author;
import by.htp.library.entity.Book;
import by.htp.library.entity.EnumNameColumn;
import by.htp.library.entity.Reader;

public class ReaderDaoImple extends AbstractDao {
	private static Reader reader = new Reader();
	private static ResultSet rs;

	@Override
	public Boolean login(String login, String pass) {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryLogin())) {
			ps.setString(1, login);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			rs.next();
			buildReader(rs);
			System.out.println(reader.toString());
			return true;
		} catch (SQLException e) {
			System.err.println("You entered incorrect login or password");
			return false;
		}
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

	public void showCatalouge(List<Book> list) {
		System.out.printf("%-10s%-20s%-35s%n", "Book id", "Title ", "Author ");
		System.out.println("----------------------------------------------------------------------------------");
		for (Book l : list)
			System.out.printf("%-10d%-20s%-35s%n", l.getId_book(), l.getTitle(), l.getAuthor().getSurname());
		System.out.println("----------------------------------------------------------------------------------");
	}

	@Override
	public Boolean checkReader(String login, String pass) {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryGotBook())) {
			ps.setString(1, login);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			rs.next();
			GregorianCalendar takeDate = new GregorianCalendar();
			GregorianCalendar currentDate = new GregorianCalendar();
			takeDate.setTime(rs.getDate("take_date"));
			takeDate.add(Calendar.DAY_OF_MONTH, 30);
			if (takeDate.after(currentDate))
				System.out.println("You have a book, which have to return untill  "
						+ new SimpleDateFormat("yyyy-MM-dd").format(takeDate.getTime()));
			return true;
		} catch (SQLException e) {
			System.err.println("You have not read any books");
			return false;
		}
	}

	public void showDetailsBook(int id_book) {
		Book book = new Book();
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getDetailBook())) {
			ps.setInt(1, id_book);
			ResultSet rs = ps.executeQuery();
			rs.next();
			book = buildBook(rs);
			System.out.println(
					"Id book: " + book.getId_book() + ", Title: " + book.getTitle() + ", Type: " + book.getType());
			System.out.println(book.getAuthor().toString());
			System.out.println(
					"------------------------------------------------------------------------------------------------------");
			String[] preface = book.getPreface().split("[.]");
			for (String s : preface) {
				System.out.println(s + ".");
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Reader getReader() {
		return reader;
	}

	private Book buildBook(ResultSet rs) throws SQLException {
		Book book = new Book();
		book.setTitle(rs.getString(EnumNameColumn.BOOK_TITLE.getValue().trim()));
		book.setId_book(rs.getInt(EnumNameColumn.BOOK_ID.getValue()));
		book.setAuthor(buildAuthor(rs));
		book.setType(rs.getString(EnumNameColumn.BOOK_TYPE.getValue()));
		book.setPreface(rs.getString(EnumNameColumn.BOOK_PREFACE.getValue()));
		return book;
	}

	private void buildReader(ResultSet rs) throws SQLException {
		reader.setName(rs.getString(EnumNameColumn.READER_NAME.getValue()).trim());
		reader.setId(rs.getInt(EnumNameColumn.READER_ID.getValue()));
		reader.setSurname(rs.getString(EnumNameColumn.READER_SURNAME.getValue().trim()));
		reader.setPassword(rs.getString(EnumNameColumn.READER_PASSWORD.getValue().trim()));
		reader.setNumberPhone(rs.getInt(EnumNameColumn.READER_NUMBER_PHONE.getValue()));
		reader.setNumberLibraryCard(rs.getString(EnumNameColumn.READER_LOGIN.getValue()));

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
