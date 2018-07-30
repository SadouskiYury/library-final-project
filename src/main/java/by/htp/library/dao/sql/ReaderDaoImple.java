package by.htp.library.dao.sql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import by.htp.library.dao.AbstractDao;
import by.htp.library.dao.sql.util.ConnectionDB;
import by.htp.library.dao.sql.util.SqlPropertyManager;
import by.htp.library.entity.Author;
import by.htp.library.entity.Book;
import by.htp.library.entity.Reader;

public class ReaderDaoImple extends AbstractDao {
	private static Reader reader = new Reader();
	private static ResultSet rs;

	@Override
	public Boolean login(int login, String pass) {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryLogin())) {
			ps.setInt(1, login);
			ps.setString(2, pass);
			System.out.println(ps.toString());
			rs = ps.executeQuery();
			rs.next();// ???почему юез него ошибка??
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
		for (Book l : list)
			System.out.println("Book id: " + l.getId_book() + " ,title: " + l.getTitle() + ". Author: " + " "
					+ l.getAuthor().getSurname());
	}

	private void buildReader(ResultSet rs) throws SQLException {
		reader.setName(rs.getString("name").trim());
		reader.setNumberLibraryCard(rs.getInt("id_reader"));
		reader.setSurname(rs.getString("surname").trim());
		reader.setPassword(rs.getString("password").trim());

	}

	public static void showDetailsBook(int id_book) {

	}

	public static Reader getReader() {
		return reader;
	}

	private Book buildBook(ResultSet rs) throws SQLException {
		Book book = new Book();
		GregorianCalendar dateOfmade = new GregorianCalendar();
		if (rs.getDate("date") == (null))
			book.setDateOfmade(null);
		else {
			dateOfmade.setTime(rs.getDate("date"));
			book.setDateOfmade(dateOfmade);
		}
		book.setTitle(rs.getString("title").trim());
		book.setId_book(rs.getInt("id"));
		book.setAuthor(buildAuthor(rs));
		book.setType(rs.getString("type"));
		book.setPreface(rs.getString("preface"));
		return book;
	}

	private Author buildAuthor(ResultSet rs) throws SQLException {
		Author author = new Author();
		GregorianCalendar birthday = new GregorianCalendar();
		birthday.setTime(rs.getDate("birthday"));
		author.setName(rs.getString("name").trim());
		author.setMidlenme(rs.getString("midlename").trim());
		author.setSurname(rs.getString("surname").trim());
		author.setBirthDate(birthday);
		author.setId(rs.getInt("author.id"));
		return author;
	}

	private int cheсkAuthor(Author author) throws SQLException {
		Date birthday = new Date(author.getBirthDate().getTimeInMillis());
		PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryAuthor());
		ps.setString(1, author.getName());
		ps.setString(2, author.getMidlenme());
		ps.setString(3, author.getSurname());
		ps.setDate(4, birthday);
		ps.setString(5, author.getName());
		ps.setString(6, author.getMidlenme());
		ps.setString(7, author.getSurname());
		ps.setDate(8, birthday);
		int result = ps.executeUpdate();

		return result;
	}

}
