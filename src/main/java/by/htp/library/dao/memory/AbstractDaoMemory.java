package by.htp.library.dao.memory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import by.htp.library.dao.Dao;
import by.htp.library.dao.memory.serializble.Serializable;
import by.htp.library.entity.Book;
import by.htp.library.entity.Report;

public abstract class AbstractDaoMemory implements Dao {
	private static int count;

	@Override
	public void showCatalouge() {
		Serializable serial = new Serializable();
		List<Book> list = serial.readBookBase().getCatalog();
		System.out.printf("%-10s%-30s%-35s%n", "Book id", "Title ", "Author ");
		System.out.println("----------------------------------------------------------------------------------");
		for (Book l : list)
			System.out.printf("%-10d%-30s%-35s%n", l.getId_book(), l.getTitle(), l.getAuthor().getSurname());
		System.out.println("----------------------------------------------------------------------------------");
	}

	@Override
	public abstract Boolean login(String login, String pass);

	@Override
	public Boolean checkReader(String login) {
		count = 0;
		Serializable serial = new Serializable();
		GregorianCalendar takeDate = new GregorianCalendar();
		GregorianCalendar currentDate = new GregorianCalendar();
		List<Report> report = serial.readReportBase().getReports();
		for (Report r : report) {
			if (r.getReader().getNumberLibraryCard().equals(login) && r.getDate_return() == null) {
				takeDate = r.getTake_date();
				takeDate.add(Calendar.DAY_OF_MONTH, 30);
				if (takeDate.before(currentDate)) {
					System.out.println(
							"You have overdue a BOOK" + r.getBook().getTitle() + ", which have to return untill  "
									+ new SimpleDateFormat("yyyy/MM/dd").format(takeDate.getTime()));
					return false;
				} else {
					System.out.println("You have a book: " + r.getBook().getTitle() + ", which have to return untill  "
							+ new SimpleDateFormat("yyyy/MM/dd").format(takeDate.getTime()));
					count++;
					return true;
				}
			}
			System.out.println("You don't have any books");
			return true;
		}
		return false;
	}

	@Override
	public Boolean add(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean issueBook(int id, String NumberLibraryCard) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean returnBook(int id_book) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void showDetailsBook(int id_book) {
		throw new UnsupportedOperationException();
	}

	public static int getCount() {
		return count;
	}

}
