package by.htp.library.dao.memory;

import by.htp.library.dao.ReportDao;

public class Reports implements ReportDao {

	@Override
	public void debtorsReport() {
	}

	@Override
	public void reportAboutReadBooks() {
	}

	@Override
	public void reportAboutReder() {
		System.out.println("you in memory");
	}

}
