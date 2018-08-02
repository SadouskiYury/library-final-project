package by.htp.library.logic;

import by.htp.library.dao.Dao;
import by.htp.library.dao.ReportDao;
import by.htp.library.dao.memory.LibrarianDaoImple;
import by.htp.library.dao.memory.ReaderDaoImple;
import by.htp.library.dao.memory.Reports;

public class DaoMemory {
	public final static Dao readerDao;
	public final static Dao librarianDao;
	public final static ReportDao reportDao;
	static {
		readerDao = new ReaderDaoImple();
		librarianDao = new LibrarianDaoImple();
		reportDao = new Reports();
	}
}
