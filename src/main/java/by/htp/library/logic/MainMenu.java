package by.htp.library.logic;

import java.util.Scanner;

import by.htp.library.dao.Dao;
import by.htp.library.dao.ReportDao;
import by.htp.library.logic.dao.type.DaoDB;
import by.htp.library.logic.dao.type.DaoMemory;

public class MainMenu {
	private static Dao readerDao;
	private static Dao librarianDao;
	private static ReportDao reportDao;

	public static void startMenu() {
		Scanner sc = new Scanner(System.in);
		if (chouseDBorMemory(sc)) {
			readerDao = DaoDB.readerDao;
			librarianDao = DaoDB.librarianDao;
			reportDao = DaoDB.reportDao;
		} else {
			readerDao = DaoMemory.readerDao;
			librarianDao = DaoMemory.librarianDao;
			reportDao = DaoMemory.reportDao;

		}
		Menu.showMainMenu();
		Functions.sleep(500);
		label: while (true) {
			switch (sc.next()) {
			case "0":
				break label;
			case "1":
				if (Menu.menuReader(sc, readerDao))
					Functions.sleep(500);
				Functions.showFunctionReader(sc, readerDao);
				break label;
			case "2":
				Menu.menuLibrarian(sc, librarianDao);
				Functions.sleep(500);
				Functions.showFunctionLibrarian(sc, librarianDao);
				break label;
			case "3":
				Menu.menuReport(sc, reportDao);
				break label;
			default:
				System.out.println("You entered incorrect number, please be attentive repeat Enter");
				System.out.println("Number must be [0-3]");
			}
		}
		sc.close();
	}

	private static Boolean chouseDBorMemory(Scanner sc) {
		while (true) {
			System.out.println("Select Dao?");
			System.out.println("[1].Dao DataBase ");
			System.out.println("[2].Dao Memory ");
			switch (sc.next()) {
			case "1":
				return true;
			case "2":
				return false;
			default:
				System.out.println("You entered incorrect number, please be attentive repeat Enter");
				System.out.println("Number must be [1-2]");
			}
		}
	}
}
