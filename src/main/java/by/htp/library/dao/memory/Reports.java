package by.htp.library.dao.memory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import by.htp.library.dao.ReportDao;
import by.htp.library.dao.memory.serializble.Serializable;
import by.htp.library.dao.sql.util.EnumNameColumn;
import by.htp.library.entity.Book;
import by.htp.library.entity.Reader;
import by.htp.library.entity.Report;

public class Reports implements ReportDao {
	private GregorianCalendar currentDate;
	private GregorianCalendar returnDate;
	private Serializable serial;
	private List<Reader> reader;
	private List<Book> book;
	private List<Report> report;
	{
		currentDate = new GregorianCalendar();
		serial = new Serializable();
		reader = serial.readReaderBase().getReader();
		book = serial.readBookBase().getCatalog();
		report = serial.readReportBase().getReports();
	}

	@Override
	public void debtorsReport() {
		System.out.println("Debtors REPORT");
		System.out.printf("%-10s%-15s%-15s%-15s%-10s%n", "Name", "Surname ", "NumbeerPhone", "Take date",
				"Overdue day");
		System.out.println("----------------------------------------------------------------------------------");
		for (Report list : report) {
			if (list.getDate_return() == null) {
				returnDate = list.getTake_date();
				returnDate.add(Calendar.DAY_OF_MONTH, 30);
				if (currentDate.after(returnDate)) {
					long day = currentDate.getTimeInMillis() - returnDate.getTimeInMillis();
					System.out.printf("%-10s%-15s%-15s%-15s%-10s%n", list.getReader().getName().trim(),
							list.getReader().getSurname().trim(), list.getReader().getNumberPhone(),
							new SimpleDateFormat("yyyy/MM/dd").format(list.getTake_date().getTime()),
							(day / (1000L * 60L * 60L * 24L)));
				}
			}

		}
		System.out.println("----------------------------------------------------------------------------------");
	}

	@Override
	public void reportAboutReadBooks() {

		System.out.println("Report about read books");
		System.out.printf("%-10s%-20s%-10s%n", "ID_BOOK", "TITLE", "COUNT");
		System.out.println("---------------------------------------------------------------------------------");
		int count = 0;
		int countMap = 0;
		Map<Integer, Book> map = new HashMap<>();
		for (Report list : report) {
			for (Report list1 : report) {
				if (list.getBook().equals(list1.getBook())) {
					count++;
				}
			}
			System.out.println(count);
			map.put(++countMap, list.getBook());
			count = 0;
		}

		Set<Integer> keys = map.keySet();
		for (Integer key : keys) {
			System.out.println(map.get(key).getId_book() + "   " + map.get(key).getTitle());
		}

		// System.out.println(listNext.toString());
		// System.out.printf("%-10d%-20s%-10d%n", rs.getInt("id_book"),
		// rs.getString(EnumNameColumn.BOOK_TITLE.getValue()),
		// rs.getInt("count"));
		//
		// System.out.println("----------------------------------------------------------------------------------");

	}

	@Override
	public void reportAboutReder() {
		int count = 0;
		currentDate.add(Calendar.DAY_OF_MONTH, -30);
		System.out.println("Report about active readers");
		System.out.printf("%-15s%-15s%-15s%-15s%n", "COUNT BOOKS", "Name", "Surname", "Login");
		System.out.println("---------------------------------------------------------------------------------");
		for (Report r : report) {
			if (r.getTake_date().after(currentDate)) {
				for (Report rep : report) {
					if (rep.getReader().equals(r.getReader())) {
						count++;
					}
				}
				if (count >= 2 && count < 8)
					System.out.printf("%-15d%-15s%-15s%-15s%n", count, r.getReader().getName(),
							r.getReader().getSurname(), r.getReader().getNumberLibraryCard());
				count = 0;
			}
		}
		System.out.println("---------------------------------------------------------------------------------");
	}

}
