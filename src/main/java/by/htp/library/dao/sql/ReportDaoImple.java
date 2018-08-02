package by.htp.library.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import by.htp.library.dao.sql.util.ConnectionDB;
import by.htp.library.dao.sql.util.EnumNameColumn;
import by.htp.library.dao.sql.util.SqlPropertyManager;

public class ReportDaoImple {

	public static void debtorsReport() {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryDeptorsReport())) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GregorianCalendar returnDate = new GregorianCalendar();
				GregorianCalendar currentDate = new GregorianCalendar();
				returnDate.setTime(rs.getDate(EnumNameColumn.REPORT_TAKE_DATE.getValue()));
				returnDate.add(Calendar.DAY_OF_MONTH, 30);
				if (currentDate.after(returnDate)) {
					long day = currentDate.getTimeInMillis() - returnDate.getTimeInMillis();
					System.out.println("Debtors REPORT");
					System.out.printf("%-10s%-15s%-15s%-15s%-10s%n", "Name", "Surname ", "NumbeerPhone", "Take date",
							"Overdue day");
					System.out.println(
							"----------------------------------------------------------------------------------");
					System.out.printf("%-10s%-15s%-15s%-15s%-10s%n",
							rs.getString(EnumNameColumn.READER_NAME.getValue()),
							rs.getString(EnumNameColumn.READER_SURNAME.getValue()),
							rs.getInt(EnumNameColumn.READER_NUMBER_PHONE.getValue()),
							new SimpleDateFormat("yyyy/MM/dd")
									.format(rs.getDate(EnumNameColumn.REPORT_TAKE_DATE.getValue())),
							(day / (1000L * 60L * 60L * 24L)));
					System.out.println(
							"----------------------------------------------------------------------------------");
				}
			}
		} catch (SQLException e) {
			System.err.println("Incorect query for deptors reader");

		}
	}

	public static void reportAboutReadBooks() {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryReportBook())) {
			ResultSet rs = ps.executeQuery();
			System.out.println("Report about read books");
			System.out.printf("%-10s%-20s%-10s%n", "ID_BOOK", "TITLE", "COUNT");
			System.out.println("---------------------------------------------------------------------------------");
			while (rs.next()) {
				System.out.printf("%-10d%-20s%-10d%n", rs.getInt("id_book"),
						rs.getString(EnumNameColumn.BOOK_TITLE.getValue()), rs.getInt("count"));
			}
			System.out.println("----------------------------------------------------------------------------------");
		} catch (

		SQLException e) {
			System.err.println("Incorect query for read books");

		}
	}

	public static void reportAboutReder() {
		try (PreparedStatement ps = ConnectionDB.conectionWithDB(SqlPropertyManager.getQueryReportReaders())) {
			ResultSet rs = ps.executeQuery();
			System.out.println("Report about active readers");
			System.out.printf("%-15s%-15s%-15s%-15s%n", "COUNT BOOKS", "Name", "Surname", "Login");
			System.out.println("---------------------------------------------------------------------------------");
			while (rs.next()) {
				System.out.printf("%-15d%-15s%-15s%-57s%n", rs.getInt("count"),
						rs.getString(EnumNameColumn.READER_NAME.getValue()),
						rs.getString(EnumNameColumn.READER_SURNAME.getValue()),
						rs.getString(EnumNameColumn.READER_LOGIN.getValue()));
			}
			System.out.println("----------------------------------------------------------------------------------");
		} catch (

		SQLException e) {
			System.err.println("Incorect query for about reader");

		}
	}

}