package by.htp.library.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.htp.library.dao.sql.LibrarianDaoImple;
import by.htp.library.dao.sql.ReaderDaoImple;
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
		System.out.println("Select user");
		System.out.println("[0].Exit");
		System.out.println("[1].Reader");
		System.out.println("[2].Librarian");
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
			default:
				System.out.println("You entered incorrect number, please be attentive repeat Enter");
				System.out.println("Number must be [0-2]");
			}
		}
		sc.close();
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
				System.out.println("If you wish exit, enter [0]");
				System.out.println("If you would like continue, enter other symbol");
				if (sc.next().equals("0"))
					break;
			}
		}
		return false;

	}

	private static void showFunctionReader(Scanner sc) {

		label: while (true) {
			System.out.println("Menu");
			System.out.println("[0].Exit");
			System.out.println("[1].Show the catalog of books");
			System.out.println("[2].Show deatails about book");
			sleep(1000);
			switch (sc.next()) {
			case "0":
				break label;
			case "1":
				readerDao.showCatalouge(readerDao.buildCatalogue());
				exitMenu(sc);
				break;
			case "2": {
				System.out.println("Enter id book");
				int id = checkIdBoock(sc);
				sleep(1000);
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
				librarianDao.add(createReader(sc));
				exitMenu(sc);
				break;
			case "2":
				librarianDao.add(createBook(sc));
				exitMenu(sc);
				break;
			case "3":
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
				System.out.println("If you wish exit, enter [0]");
				System.out.println("If you would like continue, enter other symbol");
				if (sc.next().equals("0"))
					break;
			}
		}
		return false;

	}

	private static void menuReport() {

	}

	private static Reader createReader(Scanner sc) {
		Reader reader = new Reader();
		System.out.println("Enter login:");
		reader.setNumberLibraryCard(sc.next());
		System.out.println("Enter password");
		reader.setPassword(validationPassword(sc));
		System.out.println("Enter name");
		reader.setName(sc.next());
		System.out.println("Enter surname");
		reader.setSurname(sc.next());
		System.out.println("Enter numberPhone");
		reader.setNumberPhone(sc.nextInt());
		return reader;
	}

	private static Book createBook(Scanner sc) {
		Book book = new Book();
		System.out.println("Enter book's tile:");
		book.setTitle(sc.next());
		System.out.println("Enter book's type:");
		book.setType(sc.next());
		System.out.println("Enter book's preface:");
		book.setPreface(sc.next() + sc.nextLine());
		book.setAuthor(buildAuthor(sc));
		return book;
	}

	private static Author buildAuthor(Scanner sc) {
		Author author = new Author();
		System.out.println("Enter name of author:");
		author.setName(sc.next());
		System.out.println("Enter midleName of author:");
		author.setMidlenme(sc.next());
		System.out.println("Enter surName of author:");
		author.setSurname(sc.next());
		System.out.println("Enter birthday of author in next format (yyyy/mm/dd)");
		GregorianCalendar birthday = new GregorianCalendar();
		while (true) {
			try {

				Date date = new SimpleDateFormat("yyyy/MM/dd").parse(sc.next());
				birthday.setTimeInMillis(date.getTime());
				author.setBirthDate(birthday);
				break;
			} catch (ParseException e) {
				e.printStackTrace();
				System.err.println("Inncorect format date, try Enter again");
			}
		}
		return author;
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

	private static String validationPassword(Scanner sc) {
		while (true) {
			String s = sc.next();
			Pattern p = Pattern.compile("[0-9]");
			Matcher m = p.matcher(s);
			if (s.length() > 6 && m.find())
				return s;
			System.out.println("The password must contain at least 6 characters and one digit!");
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
