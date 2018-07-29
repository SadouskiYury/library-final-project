package by.htp.library.dao.sql.util;

import static by.htp.library.dao.sql.util.MainSqlPropertyManager.getLogin;
import static by.htp.library.dao.sql.util.MainSqlPropertyManager.getPass;
import static by.htp.library.dao.sql.util.MainSqlPropertyManager.getUrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionDB {
	private static PreparedStatement st;

	static PreparedStatement conectionWithDB(String url) throws SQLException {
		Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass());

		st = conn.prepareStatement(url);
		return st;

	}
}