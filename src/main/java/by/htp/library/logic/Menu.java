package by.htp.library.logic;

import java.util.Scanner;

import by.htp.library.dao.Dao;
import by.htp.library.dao.ReportDao;
import by.htp.library.entity.Librarian;

public class Menu {

	public static void menuReport(Scanner sc, ReportDao report) {
		label: while (true) {
			showBoxReport();
			switch (sc.next()) {
			case "0":
				break label;
			case "1":
				report.debtorsReport();
				break;
			case "2":
				report.reportAboutReadBooks();
				break;
			case "3":
				report.reportAboutReder();
				break;
			default:
				System.out.println("You entered incorrect number, please be attentive repeat Enter");
				System.out.println("Number must be [0-3]");
			}

		}
	}

	public static Boolean menuReader(Scanner sc, Dao readerDao) {
		while (true) {
			System.out.println("Please, Enter your login");
			String login = sc.next();
			System.out.println("Please, Enter your password");
			String pass = sc.next();
			if (readerDao.login(login, pass)) {
				System.out.println("Welcome to Library!");
				System.out.println(readerDao.checkReader(login));
				return true;
			} else {
				System.out.println("You entered incorrect login or password, please be attentive repeat Enter");
				exitMenu(sc);
			}
		}
	}

	public static Boolean menuLibrarian(Scanner sc, Dao librarian) {
		while (true) {
			System.out.println("Please, Enter your login");
			String login = sc.next();
			System.out.println("Please, Enter your password");
			String pass = sc.next();
			if (librarian.login(login, pass)) {
				System.out.println(
						"Welcome to Library! " + Librarian.NAME.getValue() + " " + Librarian.SURNAME.getValue());
				return true;
			} else {
				System.out.println("You entered incorrect login or password, please be attentive repeat Enter");
				exitMenu(sc);
			}
		}
	}

	public static void showMainMenu() {
		System.out.println("Select user");
		System.out.println("[0].Exit");
		System.out.println("[1].Reader");
		System.out.println("[2].Librarian");
		System.out.println("[3].Reports");
	}

	public static void showBoxLibrarian() {
		System.out.println("Menu");
		System.out.println("[0].Exit");
		System.out.println("[1].Add a reader");
		System.out.println("[2].Add a book");
		System.out.println("[3].Return a book");
		System.out.println("[4].Issue the book");
		System.out.println("[5].Show the catalog of books");
	}

	public static void showBoxReport() {
		System.out.println("Menu");
		System.out.println("[0].Exit");
		System.out.println("[1].Report on debtors readers ");
		System.out.println("[2].Read books report");
		System.out.println("[3].Report on the best readers");
	}

	public static void issuedBook(Scanner sc, Dao librarian) {
		System.out.println("Enter id_book!");
		while (!sc.hasNextInt()) {
			System.out.println("Input id_book must be number!. Try again.");
			sc.next();
		}
		int id = sc.nextInt();
		System.out.println("Enter your numberLibraryCard");
		librarian.issueBook(id, sc.next());
	}

	public static void exitMenu(Scanner sc) {
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
}
