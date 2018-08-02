package by.htp.library.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.htp.library.dao.sql.util.ConnectionDB;
import by.htp.library.dao.sql.util.EnumNameColumn;
import by.htp.library.dao.sql.util.SqlPropertyManager;
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
	public Boolean checkReader(String login) {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryGotBook())) {
			ps.setString(1, login);
			rs = ps.executeQuery();
			GregorianCalendar takeDate = new GregorianCalendar();
			GregorianCalendar currentDate = new GregorianCalendar();
			while (rs.next()) {
				if (rs.getDate(EnumNameColumn.REPORT_RETURN_DATE.getValue()) == null) {
					takeDate.setTime(rs.getDate(EnumNameColumn.REPORT_TAKE_DATE.getValue()));
					takeDate.add(Calendar.DAY_OF_MONTH, 30);
					if (takeDate.after(currentDate))
						System.out
								.println("You have a book: " + rs.getString("title") + ", which have to return untill  "
										+ new SimpleDateFormat("yyyy/MM/dd").format(takeDate.getTime()));
					else
						System.out.println(
								"You have overdue a BOOK: " + rs.getString("title") + ", which have to return untill  "
										+ new SimpleDateFormat("yyyy/MM/dd").format(takeDate.getTime()));

				}
			}
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
			String text = book.getPreface();
			Pattern p = Pattern.compile("[.!?]");
			Matcher m = p.matcher(text);
			StringBuffer sb = new StringBuffer();
			while (m.find())
				m.appendReplacement(sb, "\n");
			System.out.print(sb.toString());
			System.out.println(
					"-------------------------------------------------------------------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Reader getReader() {
		return reader;
	}

	private void buildReader(ResultSet rs) throws SQLException {
		reader.setName(rs.getString(EnumNameColumn.READER_NAME.getValue()).trim());
		reader.setSurname(rs.getString(EnumNameColumn.READER_SURNAME.getValue().trim()));
		reader.setPassword(rs.getString(EnumNameColumn.READER_PASSWORD.getValue().trim()));
		reader.setNumberPhone(rs.getInt(EnumNameColumn.READER_NUMBER_PHONE.getValue()));
		reader.setNumberLibraryCard(rs.getString(EnumNameColumn.READER_LOGIN.getValue()));

	}

}
