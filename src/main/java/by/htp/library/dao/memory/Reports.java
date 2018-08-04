package by.htp.library.dao.memory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import by.htp.library.dao.ReportDao;
import by.htp.library.dao.memory.serializble.Serializable;
import by.htp.library.entity.Book;
import by.htp.library.entity.Reader;
import by.htp.library.entity.Report;

public class Reports implements ReportDao {
	private GregorianCalendar currentDate;
	private GregorianCalendar returnDate;
	private Serializable serial;
	private List<Report> report;
	{
		currentDate = new GregorianCalendar();
		serial = new Serializable();
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
		int count = 0;
		List<TempReport> listTemp = new LinkedList<>();
		Map<Book, Integer> map = new HashMap<>();

		System.out.println("Report about read books");
		System.out.printf("%-10s%-25s%-10s%n", "ID_BOOK", "TITLE", "COUNT");
		System.out.println("---------------------------------------------------------------------------------");
		for (Report list : report) {
			for (Report list1 : report) {
				if (list.getBook().equals(list1.getBook())) {
					count++;
				}
			}
			map.put(list.getBook(), count);
			count = 0;
		}
		map.forEach((k, l) -> listTemp.add(new TempReport(l, k)));
		listTemp.sort(new Comparator<TempReport>() {
			@Override
			public int compare(TempReport o1, TempReport o2) {
				return o2.getCount_read() - o1.getCount_read();
			}
		});
		for (TempReport temp : listTemp) {
			System.out.printf("%-10d%-25s%-10d%n", temp.getBook().getId_book(), temp.getBook().getTitle(),
					temp.getCount_read());
		}
		System.out.println("----------------------------------------------------------------------------------");

	}

	@Override
	public void reportAboutReder() {
		int count = 0;
		currentDate.add(Calendar.DAY_OF_MONTH, -30);
		System.out.println("Report about active readers");
		System.out.printf("%-15s%-15s%-15s%-15s%n", "COUNT BOOKS", "Name", "Surname", "Login");
		System.out.println("---------------------------------------------------------------------------------");
		Map<Reader, Integer> map = new HashMap<>();
		for (Report r : report) {
			if (r.getTake_date().after(currentDate)) {
				for (Report rep : report) {
					if (rep.getReader().equals(r.getReader()))
						count++;
				}
				if (count >= 2 && count < 8)
					map.put(r.getReader(), count);
				count = 0;
			}
		}

		map.forEach((k, l) -> System.out.printf("%-15d%-15s%-15s%-15s%n", l, k.getName(), k.getSurname(),
				k.getNumberLibraryCard()));
		System.out.println("---------------------------------------------------------------------------------");
	}

	private class TempReport {
		private int count_read;
		private Book book;

		public TempReport() {
			super();

		}

		public TempReport(int count_read, Book book) {
			this.count_read = count_read;
			this.book = book;
		}

		public int getCount_read() {
			return count_read;
		}

		public void setCount_read(int count_read) {
			this.count_read = count_read;
		}

		public Book getBook() {
			return book;
		}

		public void setBook(Book book) {
			this.book = book;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((book == null) ? 0 : book.hashCode());
			result = prime * result + count_read;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TempReport other = (TempReport) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (book == null) {
				if (other.book != null)
					return false;
			} else if (!book.equals(other.book))
				return false;
			if (count_read != other.count_read)
				return false;
			return true;
		}

		private Reports getOuterType() {
			return Reports.this;
		}

		@Override
		public String toString() {
			return "TempReport [count_read=" + count_read + ", book=" + book + "]";
		}

	}
}
