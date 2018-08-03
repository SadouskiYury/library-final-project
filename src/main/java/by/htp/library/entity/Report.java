package by.htp.library.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 725530909501433231L;
	private Book book;
	private Reader reader;
	private GregorianCalendar take_date;
	private GregorianCalendar date_return;

	public Report() {
		super();

	}

	public Report(Book book, Reader reader, GregorianCalendar take_date) {
		this.book = book;
		this.reader = reader;
		this.take_date = take_date;
		this.date_return = null;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public GregorianCalendar getTake_date() {
		return take_date;
	}

	public void setTake_date(GregorianCalendar take_date) {
		this.take_date = take_date;
	}

	public GregorianCalendar getDate_return() {
		return date_return;
	}

	public void setDate_return(GregorianCalendar date_return) {
		this.date_return = date_return;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((date_return == null) ? 0 : date_return.hashCode());
		result = prime * result + ((reader == null) ? 0 : reader.hashCode());
		result = prime * result + ((take_date == null) ? 0 : take_date.hashCode());
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
		Report other = (Report) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (date_return == null) {
			if (other.date_return != null)
				return false;
		} else if (!date_return.equals(other.date_return))
			return false;
		if (reader == null) {
			if (other.reader != null)
				return false;
		} else if (!reader.equals(other.reader))
			return false;
		if (take_date == null) {
			if (other.take_date != null)
				return false;
		} else if (!take_date.equals(other.take_date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Report [book=" + book + ", reader=" + reader + ", take_date="
				+ new SimpleDateFormat("yyyy-MM-dd").format(take_date.getTime()) + ", date_return=" + date_return + "]";
	}

}
