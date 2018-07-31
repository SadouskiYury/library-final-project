package by.htp.library.logic;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.htp.library.dao.sql.LibrarianDaoImple;
import by.htp.library.dao.sql.ReaderDaoImple;

public class Logic {
	private static ReaderDaoImple reader;
	private static LibrarianDaoImple librarian;
	static {
		reader = new ReaderDaoImple();
		librarian = new LibrarianDaoImple();
	}

	public static void startMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Select user");
		System.out.println("[0].Exit");
		System.out.println("[1].Reader");
		System.out.println("[2].Librarian");
		sleep(1000);
		label: while (true) {
			switch (sc.next()) {
			case "0":
				break label;
			case "1":
				if (menuReader(sc))
					sleep(1000);
				showFunctionReader(sc);
				break label;
			case "2":
				menuLibrarian();
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
			if (reader.login(login, pass)) {
				System.out.println(
						"Welcome to Library! " + reader.getReader().getName() + " " + reader.getReader().getSurname());
				reader.checkReader(login, pass);
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
				reader.showCatalouge(reader.buildCatalogue());
				exitMenu(sc);
				break;
			case "2": {
				System.out.println("Enter id book");
				int id = checkIdBoock(sc);
				sleep(1000);
				reader.showDetailsBook(id);
				exitMenu(sc);
				break;
			}
			default:
				System.out.println("You entered incorrect number, please be attentive repeat Enter");
				System.out.println("Number must be [0-2]");
			}
		}
	}

	private static void menuLibrarian() {
		librarian = new LibrarianDaoImple();
	};

	private static void menuReport() {

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
