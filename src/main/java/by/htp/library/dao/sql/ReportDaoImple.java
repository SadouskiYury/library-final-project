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
}