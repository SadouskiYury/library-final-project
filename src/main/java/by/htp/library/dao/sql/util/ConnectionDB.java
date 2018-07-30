package by.htp.library.dao.sql.util;

import static by.htp.library.dao.sql.util.SqlPropertyManager.getLogin;
import static by.htp.library.dao.sql.util.SqlPropertyManager.getPass;
import static by.htp.library.dao.sql.util.SqlPropertyManager.getUrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionDB {
	private static PreparedStatement st;

	public static PreparedStatement conectionWithDB(String url) throws SQLException {
		Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass());
		st = conn.prepareStatement(url);
		return st;

	}
}