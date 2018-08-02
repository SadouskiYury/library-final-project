package by.htp.library.dao.memory;

import java.util.GregorianCalendar;

import by.htp.library.dao.Dao;
import by.htp.library.entity.Author;
import by.htp.library.entity.Book;

public abstract class AbstractDaoMemory implements Dao {

	@Override
	public Boolean login(String login, String pass) {
		return null;
	}

	@Override
	public Boolean add(Object o) {
		return null;
	}

	@Override
	public Boolean issueBook(int id, String NumberLibraryCard) {
		return null;
	}

	@Override
	public Boolean returnBook(int id_book) {
		return null;
	}

	@Override
	public Boolean checkReader(String login) {
		return null;
	}

	@Override
	public void showCatalouge() {
	}

	@Override
	public void showDetailsBook(int id_book) {
	}

	public Book buildBook() {
		Book book = new Book();
		return book;
	}

	private Author buildAuthor() {
		Author author = new Author();
		GregorianCalendar birthday = new GregorianCalendar();
		return author;
	}

}
