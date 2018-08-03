package by.htp.library.dao.memory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import by.htp.library.dao.memory.serializble.Serializable;
import by.htp.library.entity.Reader;
import by.htp.library.entity.Report;

public class ReaderDaoImple extends AbstractDaoMemory {
	private Serializable serial = new Serializable();

	@Override
	public Boolean login(String login, String pass) {

		List<Reader> reader = serial.readReaderBase().getReader();
		for (Reader r : reader) {
			if (r.getNumberLibraryCard().equals(login) && r.getPassword().equals(pass)) {
				System.out.println(r.toString());
				return true;
			}
		}

		return false;
	}

	public void showDetailsBook(int id_book) {
		List<Report> report = serial.readReportBase().getReports();
		for (Report r : report) {
			if (r.getBook().getId_book() == id_book) {
				System.out.println("Id book: " + r.getBook().getId_book() + ", Title: " + r.getBook().getTitle()
						+ ", Type: " + r.getBook().getType());
				System.out.println(r.getBook().getAuthor().toString());
				System.out.println(
						"------------------------------------------------------------------------------------------------------");
				String text = r.getBook().getPreface().trim();
				Pattern p = Pattern.compile("[.!?]");
				Matcher m = p.matcher(text);
				StringBuffer sb = new StringBuffer();
				while (m.find())
					m.appendReplacement(sb, "\n");
				System.out.print(sb.toString());
				System.out.println(
						"-------------------------------------------------------------------------------------------------------");
			}

		}

	}

}
