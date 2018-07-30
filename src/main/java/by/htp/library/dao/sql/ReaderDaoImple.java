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

	private void buildReader(ResultSet rs) throws SQLException {
		reader.setName(rs.getString("name").trim());
		reader.setId(rs.getInt("id_reader"));
		reader.setSurname(rs.getString("surname").trim());
		reader.setPassword(rs.getString("password").trim());
		reader.setNumberPhone(rs.getInt("number_phone"));
		reader.setNumberLibraryCard(rs.getString("login"));

	}

	public void showDetailsBook(int id_book) {
		Book book = new Book();
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getDetailBook())) {
			ps.setInt(1, id_book);
			ResultSet rs = ps.executeQuery();
			rs.next();
			book = buildBook(rs);
			System.out.println(
					"Id book: " + book.getId_book() + "Title: " + book.getTitle() + "Type: " + book.getTitle());
			System.out.println(book.getAuthor().toString());
			System.out.println(book.getAuthor().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	private Book buildBook(ResultSet rs) throws SQLException {
		Book book = new Book();
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
		GregorianCalendar dateOfmade = new GregorianCalendar();
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

	public Reader getReader() {
		return reader;
	}

	@Override
	public String checkReader(String login, String pass) {
		return null;
	}

}
