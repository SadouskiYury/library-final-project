package by.htp.library.dao;

public interface Dao {
	Boolean login(String login, String pass);

	Boolean add(Object o);

	Boolean issueBook(int id, String NumberLibraryCard);

	Boolean returnBook(int id_book);

	Boolean checkReader(String login);

	void showCatalouge();

	void showDetailsBook(int id_book);

}
