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

	public static String getQueryReader() {
		return rb.getString("db.query.add.reader");
	}

	public static String getQueryAddAuthor() {
		return rb.getString("db.query.add.author");
	}

	public static String getQueryAddBook() {
		return rb.getString("db.query.add.book");
	}

	public static String getQueryIssueBook() {
		return rb.getString("db.query.issue.book");
	}

	public static String getQuerycheckBook() {
		return rb.getString("db.query.issue.book");
	}

	public static String getQueryReturnBook() {
		return rb.getString("db.query.return.book");
	}

	public static String getQueryDeptorsReport() {
		return rb.getString("db.query.deptors.report");
	}

	public static String getQueryReportBook() {
		return rb.getString("db.query.report.book");
	}

	public static String getQueryReportReaders() {
		return rb.getString("db.query.report.readers");
	}

}
