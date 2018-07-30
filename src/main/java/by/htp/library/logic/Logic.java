package by.htp.library.logic;

import java.util.Scanner;

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
			int login = sc.nextInt();
			System.out.println("Please, Enter your password");
			String pass = sc.next();
			if (reader.login(login, pass)) {
				System.out.println(
						"Welcome to Library! " + reader.getReader().getName() + " " + reader.getReader().getSurname());
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

	};

	private static void showFunctionReader(Scanner sc) {
		System.out.println("Menu");
		System.out.println("[0].Exit");
		System.out.println("[1].Show the catalog of books");
		System.out.println("[2].Show deatails about book");
		sleep(1000);
		label: while (true) {
			switch (sc.next()) {
			case "0":
				break label;
			case "1":
				reader.showCatalouge(reader.buildCatalogue());
				break label;
			case "2": {
				System.out.println("Enter id book");
				int id = sc.nextInt();
				reader.showDetailsBook(id);
			}
				break label;
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

	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

}
