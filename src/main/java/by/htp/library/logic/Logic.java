package by.htp.library.logic;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.htp.library.dao.sql.LibrarianDaoImple;
import by.htp.library.dao.sql.ReaderDaoImple;
import by.htp.library.dao.sql.ReportDaoImple;
import by.htp.library.entity.Author;
import by.htp.library.entity.Book;
import by.htp.library.entity.Librarian;
import by.htp.library.entity.Reader;

public class Logic {
	private static ReaderDaoImple readerDao;
	private static LibrarianDaoImple librarianDao;
	static {
		readerDao = new ReaderDaoImple();
		librarianDao = new LibrarianDaoImple();
	}

	public static void startMenu() {
		Scanner sc = new Scanner(System.in);
		showMainMenu();
		sleep(500);
		label: while (true) {
			switch (sc.next()) {
			case "0":
				break label;
			case "1":
				if (menuReader(sc))
					sleep(500);
				showFunctionReader(sc);
				break label;
			case "2":
				menuLibrarian(sc);
				sleep(500);
				showFunctionLibrarian(sc);
				break label;
			case "3":
				menuReport(sc);
				break label;
			default:
				System.out.println("You entered incorrect number, please be attentive repeat Enter");
				System.out.println("Number must be [0-3]");
			}
		}
		sc.close();
	}

	private static void showMainMenu() {
		System.out.println("Select user");
		System.out.println("[0].Exit");
		System.out.println("[1].Reader");
		System.out.println("[2].Librarian");
		System.out.println("[3].Reports");
	}

	private static Boolean menuReader(Scanner sc) {
		while (true) {
			System.out.println("Please, Enter your login");
			String login = sc.next();
			System.out.println("Please, Enter your password");
			String pass = sc.next();
			if (readerDao.login(login, pass)) {
				System.out.println("Welcome to Library! " + readerDao.getReader().getName() + " "
						+ readerDao.getReader().getSurname());
				readerDao.checkReader(login);
				return true;
			} else {
				System.out.println("You entered incorrect login or password, please be attentive repeat Enter");
				exitMenu(sc);
			}
		}
	}

	private static void showFunctionReader(Scanner sc) {
		label: while (true) {
			System.out.println("Menu");
			System.out.println("[0].Exit");
			System.out.println("[1].Show the catalog of books");
			System.out.println("[2].Show deatails about book");
			sleep(500);
			switch (sc.next()) {
			case "0":
				break label;
			case "1":
				readerDao.showCatalouge(readerDao.buildCatalogue());
				exitMenu(sc);
				break;
			case "2": {
				int id = checkIdBoock(sc);
				sleep(500);
				readerDao.showDetailsBook(id);
				exitMenu(sc);
				break;
			}
			default:
				System.out.println("You entered incorrect number, please be attentive repeat Enter");
				System.out.println("Number must be [0-2]");
			}
		}
	}

	private static void showFunctionLibrarian(Scanner sc) {
		label: while (true) {
			showBoxLibrarian();
			switch (sc.next()) {
			case "0":
				break label;
			case "1":
				Reader reader = new Reader();
				librarianDao.add(reader.createReader(sc));
				exitMenu(sc);
				break;
			case "2":
				Author author = new Author();
				Book book = new Book();
				librarianDao.add(book.createBook(sc, author));
				exitMenu(sc);
				break;
			case "3":
				librarianDao.returnBook(checkIdBoock(sc));
				exitMenu(sc);
				break;
			case "4":
				issuedBook(sc);
				exitMenu(sc);
				break;
			case "5":
				librarianDao.showCatalouge(librarianDao.buildCatalogue());
				exitMenu(sc);
				break;
			default:
				System.out.println("You entered incorrect number, please be attentive repeat Enter");
				System.out.println("Number must be [0-5]");
			}
		}

	}

	private static void showBoxLibrarian() {
		System.out.println("Menu");
		System.out.println("[0].Exit");
		System.out.println("[1].Add a reader");
		System.out.println("[2].Add a book");
		System.out.println("[3].Return a book");
		System.out.println("[4].Issue the book");
		System.out.println("[5].Show the catalog of books");
	}

	private static void issuedBook(Scanner sc) {
		System.out.println("Enter id_book!");
		while (!sc.hasNextInt()) {
			System.out.println("Input id_book must be number!. Try again.");
			sc.next();
		}
		int id = sc.nextInt();
		System.out.println("Enter your numberLibraryCard");
		librarianDao.issueBook(id, sc.next());
	}

	private static Boolean menuLibrarian(Scanner sc) {
		while (true) {
			System.out.println("Please, Enter your login");
			String login = sc.next();
			System.out.println("Please, Enter your password");
			String pass = sc.next();
			if (librarianDao.login(login, pass)) {
				System.out.println(
						"Welcome to Library! " + Librarian.NAME.getValue() + " " + Librarian.SURNAME.getValue());
				return true;
			} else {
				System.out.println("You entered incorrect login or password, please be attentive repeat Enter");
				exitMenu(sc);
			}
		}
	}

	private static void menuReport(Scanner sc) {
		label: while (true) {
			showBoxReport();
			switch (sc.next()) {
			case "0":
				break label;
			case "1":
				ReportDaoImple.debtorsReport();
				break;
			case "2":

				break;
			case "3":

				break;
			default:
				System.out.println("You entered incorrect number, please be attentive repeat Enter");
				System.out.println("Number must be [0-3]");
			}

		}
	}

	private static void showBoxReport() {
		System.out.println("Menu");
		System.out.println("[0].Exit");
		System.out.println("[1].Report on debtors readers ");
		System.out.println("[2].Read books report");
		System.out.println("[3].Report on the best readers");
	}

	private static void exitMenu(Scanner sc) {
		while (true) {
			System.out.println("Are you wish to continue(Y/N)?");
			String result = sc.next();
			if ("Y".equalsIgnoreCase(result)) {
				break;
			} else if ("N".equalsIgnoreCase(result)) {
				System.exit(0);
			} else
				System.out.println("You entered incorrect date, please be attentive repeat Enter");
		}
	}

	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static int checkIdBoock(Scanner sc) {
		System.out.println("Enter id book");
		String s = new String();
		while (true) {
			s = sc.next();
			Pattern p = Pattern.compile("\\D");
			Matcher m = p.matcher(s);
			if (m.find())
				System.out.println("You entered wrong id Book!,it must be number!");
			else {
				int id = Integer.parseInt(s.trim());
				return id;
			}
		}
	}

}
