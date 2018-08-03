package by.htp.library.logic.dao.type;

import by.htp.library.dao.Dao;
import by.htp.library.dao.ReportDao;
import by.htp.library.dao.sql.LibrarianDaoImple;
import by.htp.library.dao.sql.ReaderDaoImple;
import by.htp.library.dao.sql.report.Reports;

public class DaoDB {
	public final static Dao readerDao;
	public final static Dao librarianDao;
	public final static ReportDao reportDao;
	static {
		readerDao = new ReaderDaoImple();
		librarianDao = new LibrarianDaoImple();
		reportDao = new Reports();
	}

}
