package by.htp.library.dao.memory.base;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import by.htp.library.dao.memory.serializble.Serializable;
import by.htp.library.entity.Author;
import by.htp.library.entity.Book;
import by.htp.library.entity.Reader;
import by.htp.library.entity.Report;

public class CreatedNewFirstBase {
	public static void createNewBase() {
		List<Book> BaseBook = new ArrayList<Book>();
		BaseBook.add(new Book("War and Peace",
				new Author("Lev", "Nikolaevich", "Tolstoy", new GregorianCalendar(1872, 05, 04)),
				"\"War and Peace\" ( Russian preref  \"War and Peace\" ) - a novel - the epic of Leo Tolstoy , describing Russian society in the era of wars against Napoleon in 1805 - 1812.",
				"novel"));
		BaseBook.add(new Book("The Black Man",
				new Author("Sergey", "Alexandrovich", "Esenin", new GregorianCalendar(1895, 06, 21)),
				"The man in black, black, Black, Black man On the bed gets to me, the Black man will not let me Sleep all night. November 14, 1925. I revised my attitude to this",
				"roman"));
		BaseBook.add(new Book("Master and Margarita",
				new Author("Mihail", "Afanseivch", "Bulgakov", new GregorianCalendar(1895, 04, 21)),
				"The novel alternates between two settings. The first is Moscow during the 1930s, where Satan appears at the Patriarch Ponds in the guise of \"Professor Woland\", a mysterious gentleman and \"magician\" of uncertain origin. He arrives with a retinue that includes the grotesquely dressed valet Koroviev; the mischievous, gun-happy, fast-talking black cat Behemoth;",
				"novel"));
		BaseBook.add(new Book("Master and Margarita",
				new Author("Mihail", "Afanseivch", "Bulgakov", new GregorianCalendar(1895, 04, 21)),
				"The novel alternates between two settings. The first is Moscow during the 1930s, where Satan appears at the Patriarch Ponds in the guise of \"Professor Woland\", a mysterious gentleman and \"magician\" of uncertain origin. He arrives with a retinue that includes the grotesquely dressed valet Koroviev; the mischievous, gun-happy, fast-talking black cat Behemoth;",
				"novel"));
		Serializable serial = new Serializable();
		BaseBook base = new BaseBook(BaseBook);
		serial.writetBookBase(base);

		List<Reader> reader = new ArrayList<>();
		reader.add(new Reader("Pavel", "Olchovik", "12345AB", "password1", 7558806));
		reader.add(new Reader("Kolya", "Petrov", "12345AA", "password2", 4555665));
		reader.add(new Reader("Maikl", "Sidorov", "12345ABC", "password3", 45534343));
		BaseReader base1 = new BaseReader(reader);
		serial.writeReaderBase(base1);

		List<Report> reports = new LinkedList<>();

		reports.add(new Report(BaseBook.get(0), reader.get(0), new GregorianCalendar()));
		reports.add(new Report(BaseBook.get(1), reader.get(1), new GregorianCalendar(2018, 05, 23)));
		reports.add(new Report(BaseBook.get(3), reader.get(2), new GregorianCalendar()));
		BaseReport base2 = new BaseReport(reports);
		System.out.println(base2.toString());
		serial.writeReportBase(base2);
	}
}
