package by.htp.library.dao.sql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import by.htp.library.dao.sql.util.ConnectionDB;
import by.htp.library.dao.sql.util.EnumNameColumn;
import by.htp.library.dao.sql.util.SqlPropertyManager;
import by.htp.library.entity.Author;
import by.htp.library.entity.Book;
import by.htp.library.entity.Librarian;
import by.htp.library.entity.Reader;

public class LibrarianDaoImple extends AbstractDaoSQL {
	private GregorianCalendar takeDate;
	private GregorianCalendar currentDate;
	private ResultSet rs;
	private int count;
	{
		takeDate = new GregorianCalendar();
		currentDate = new GregorianCalendar();
	}

	@Override
	public Boolean login(String login, String pass) {
		return Librarian.LOGIN.getValue().equals(login) && Librarian.PASSWORD.getValue().equals(pass);
	}

	@Override
	public Boolean returnBook(int id_book) {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryReturnBook())) {
			ps.setDate(1, new Date(currentDate.getTimeInMillis()));
			ps.setInt(2, id_book);
			ps.executeUpdate();
			System.out.println("Book returned!");
			return true;
		} catch (SQLException e) {
			System.err.println("Impossible return new book!");
			return false;
		}
	}

	@Override
	public Boolean issueBook(int id, String NumberLibraryCard) {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryIssueBook())) {
			if (checkReaderForGetBook(NumberLibraryCard, id)) {
				ps.setInt(1, id);
				ps.setDate(2, new Date(currentDate.getTimeInMillis()));
				ps.setString(3, NumberLibraryCard);
				ps.executeUpdate();
				System.out.println("Book issued!");
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.err.println("Impossible issue this book!");
			return false;
		}
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
		} else if (o.getClass().equals(book.getClass())) {
			book = (Book) o;
			return insertBook(book);
		}
		return false;
	}

	@Override
	public Boolean checkReader(String login) {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryGotBook())) {
			ps.setString(1, login);
			rs = ps.executeQuery();
			takeDate = new GregorianCalendar();
			count = 0;
			while (rs.next()) {
				if (rs.getDate(EnumNameColumn.REPORT_RETURN_DATE.getValue()) == null) {
					takeDate.setTime(rs.getDate(EnumNameColumn.REPORT_TAKE_DATE.getValue()));
					takeDate.add(Calendar.DAY_OF_MONTH, 30);
					if (takeDate.after(currentDate)) {
						System.out
								.println("You have a book: " + rs.getString("title") + ", which have to return untill  "
										+ new SimpleDateFormat("yyyy/MM/dd").format(takeDate.getTime()));
						count++;
					} else {
						System.out.println(
								"You have overdue a BOOK: " + rs.getString("title") + ", which have to return untill  "
										+ new SimpleDateFormat("yyyy/MM/dd").format(takeDate.getTime()));
						return false;
					}
				}
			}
			return true;
		} catch (SQLException e) {
			System.err.println("You have not read any books");
			return false;
		}

	}

	private Boolean checkReaderForGetBook(String NumberLibaryCard, int id_book) {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryGotBook())) {
			ps.setString(1, NumberLibaryCard);
			rs = ps.executeQuery();
			if (checkReader(NumberLibaryCard) && count < 3 && checkBookForUse(id_book)) {
				return true;
			} else {
				System.out.println("You can't take this book in use!");
				return false;
			}
		} catch (SQLException e) {
			System.err.println("You have not read any books");
			return false;
		}

	}

	private Boolean checkBookForUse(int id) {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQuerycheckBook())) {
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt("id_book") == id && rs.getDate(EnumNameColumn.REPORT_RETURN_DATE.getValue()) == null) {
					System.out.println("The book in use now!");
					return false;
				}
			}
			return true;
		} catch (SQLException e) {
			System.err.println("Incorect id book");
			return false;
		}

	}

	private Boolean insertReader(Reader r) {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryReader())) {
			ps.setString(1, r.getName());
			ps.setString(2, r.getSurname());
			ps.setInt(3, r.getNumberPhone());
			ps.setString(4, r.getPassword());
			ps.setString(5, r.getNumberLibraryCard());
			ps.executeUpdate();
			System.out.println("Registartion completed " + r.getName());
			return true;
		} catch (SQLException e) {
			System.err.println("Impossible add new reader!");
			return false;
		}
	}

	private Boolean insertBook(Book b) {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryAddBook())) {
			if (cheсkAuthor(b.getAuthor())) {
				System.out.println(b.toString());
				ps.setString(1, b.getTitle());
				ps.setString(2, b.getPreface());
				ps.setString(3, b.getType());
				ps.setString(4, b.getAuthor().getName());
				ps.setString(5, b.getAuthor().getMidlenme());
				ps.setString(6, b.getAuthor().getSurname());
				Date birthday = new Date(b.getAuthor().getBirthDate().getTimeInMillis());
				ps.setDate(7, birthday);
				ps.executeUpdate();
				System.out.println("Created the book completed succesful!");
				return true;
			} else
				return false;
		} catch (SQLException e) {
			System.err.println("Impossible add new book!");
			return false;
		}
	}

	private Boolean cheсkAuthor(Author author) throws SQLException {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryAddAuthor())) {
			Date birthday = new Date(author.getBirthDate().getTimeInMillis());
			ps.setString(1, author.getName());
			ps.setString(2, author.getMidlenme());
			ps.setString(3, author.getSurname());
			ps.setDate(4, birthday);
			ps.setString(5, author.getName());
			ps.setString(6, author.getMidlenme());
			ps.setString(7, author.getSurname());
			ps.setDate(8, birthday);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("Impossible add new author!");
			return false;
		}
	}

}
