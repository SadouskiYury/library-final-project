package by.htp.library.dao.sql.util;

import java.util.ResourceBundle;

public class SqlPropertyManager {
	private static final ResourceBundle rb;
	static {
		rb = ResourceBundle.getBundle("db_config");
	}

	public static String getUrl() {
		return rb.getString("db.url");
	}

	public static String getLogin() {
		return rb.getString("db.login");
	}

	public static String getPass() {
		return rb.getString("db.pass");

	}

	public static String getQueryLogin() {
		return rb.getString("db.query.login");
	}

	public static String getQueryAuthor() {
		return rb.getString("db.query.author");
	}

	public static String getQueryBook() {
		return rb.getString("db.query.show.book");
	}

	public static String getDetailBook() {
		return rb.getString("db.query.show.datails.book");
	}

	public static String getQueryGotBook() {
		return rb.getString("db.query.take_date.for.reader");
	}

	public static String getQuertReader() {
		return rb.getString("db.query.add.reader");
	}
}
