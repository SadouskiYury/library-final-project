package by.htp.library.dao.memory;

import java.util.GregorianCalendar;
import java.util.List;

import by.htp.library.dao.memory.base.BaseBook;
import by.htp.library.dao.memory.base.BaseReader;
import by.htp.library.dao.memory.base.BaseReport;
import by.htp.library.dao.memory.serializble.Serializable;
import by.htp.library.entity.Book;
import by.htp.library.entity.Librarian;
import by.htp.library.entity.Reader;
import by.htp.library.entity.Report;

public class LibrarianDaoImple extends AbstractDaoMemory {

	private GregorianCalendar currentDate;
	private Serializable serial;
	private List<Reader> reader;
	private List<Book> book;
	private List<Report> report;
	{
		currentDate = new GregorianCalendar();
		serial = new Serializable();
		reader = serial.readReaderBase().getReader();
		book = serial.readBookBase().getCatalog();
		setIdCountBook();
		report = serial.readReportBase().getReports();
	}

	@Override
	public Boolean login(String login, String pass) {
		return Librarian.LOGIN.getValue().equals(login) && Librarian.PASSWORD.getValue().equals(pass);

	}

	@Override
	public Boolean returnBook(int id_book) {
		for (Report list : report) {
			if (list.getBook().getId_book() == id_book) {
				list.setDate_return(currentDate);
				System.out.println("Book returned!");
			}
		}
		BaseReport base = new BaseReport(report);
		serial.writeReportBase(base);
		System.out.println("Base update");
		return true;
	}

	@Override
	public Boolean issueBook(int id, String NumberLibraryCard) {
		for (Reader r : reader) {
			if (r.getNumberLibraryCard().equals(NumberLibraryCard) && checkReader(NumberLibraryCard)) {
				Reader reader = r;
				for (Report rep : report) {
					if (AbstractDaoMemory.getCount() < 3) {
						if (rep.getDate_return() == null) {
							System.out.println("Sorry,The book in use!");
							return false;
						} else {
							Report reportNew = new Report();
							reportNew.setReader(reader);
							reportNew.setBook(gotBook(id));
							reportNew.setTake_date(currentDate);
							report.add(reportNew);
							System.out.println("Book issue");
							BaseReport base = new BaseReport(report);
							serial.writeReportBase(base);
							System.out.println("Base update");
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public Boolean add(Object o) {
		Reader reader = new Reader();
		Book book = new Book();
		if (o == null)
			return false;
		else if (o.getClass().equals(reader.getClass())) {
			reader = (Reader) o;
			insertReader(reader);
			return true;
		} else if (o.getClass().equals(book.getClass())) {
			book = (Book) o;
			insertBook(book);
		}
		return false;
	}

	private void insertReader(Reader r) {
		reader.add(r);
		System.out.println("Registartion completed " + r.getName());
		BaseReader base = new BaseReader(reader);
		serial.writeReaderBase(base);
		System.out.println("Base update");
	}

	private void insertBook(Book b) {
		book.add(b);
		System.out.println("Created the book completed succesful!");
		BaseBook base = new BaseBook(book);
		serial.writetBookBase(base);
		System.out.println("Base update");

	}

	private Book gotBook(int id_book) {
		for (Book b : book) {
			if (b.getId_book() == id_book)
				return b;
		}
		return null;
	}

	private void setIdCountBook() {
		if (!book.isEmpty()) {
			int i = 0;
			for (Book b : book) {
				if (b.getId_book() > i)
					i = b.getId_book();
			}
			Book.setCount(i);
		}
	}
}
