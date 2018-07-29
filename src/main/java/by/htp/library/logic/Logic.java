package by.htp.library.logic;

import java.util.Scanner;

import by.htp.library.dao.sql.LibrarianDaoImple;
import by.htp.library.dao.sql.ReaderDaoImple;

public class Logic {

	public static void startMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Select user");
		System.out.println("[0].Exit");
		System.out.println("[1].Reader");
		System.out.println("[2].Librarian");

		label: while (true) {
			switch (sc.next()) {
			case "0":
				break label;
			case "1":
				menuReader();
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

	private static ReaderDaoImple menuReader() {
		ReaderDaoImple reader = new ReaderDaoImple();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please, Enter your login");
			String login = sc.next();
			System.out.println("Please, Enter your password");
			String pass = sc.next();
			if (reader.login(login, pass)) {
				System.out.println("Welcome to Library!");
				sc.close();
				return reader;
			} else {
				System.out.println("You entered incorrect login or password, please be attentive repeat Enter");
				System.out.println("If you wish exit, enter [0]");
				System.out.println("If you would like continue, enter other symbol");
				if (sc.next().equals("0"))
					break;
			}
		}
		sc.close();
		return reader;
	};

	private static void menuLibrarian() {
		LibrarianDaoImple librarian = new LibrarianDaoImple();
	};

	private static void menuReport() {

	}

}
