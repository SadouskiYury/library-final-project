package by.htp.library.logic;

import java.util.Scanner;

public class Logic {

	public static void startMenu() {
		Scanner sc = new Scanner(System.in);
		Menu.showMainMenu();
		Functions.sleep(500);
		label: while (true) {
			switch (sc.next()) {
			case "0":
				break label;
			case "1":
				if (Menu.menuReader(sc))
					Functions.sleep(500);
				Functions.showFunctionReader(sc);
				break label;
			case "2":
				Menu.menuLibrarian(sc);
				Functions.sleep(500);
				Functions.showFunctionLibrarian(sc);
				break label;
			case "3":
				Menu.menuReport(sc);
				break label;
			default:
				System.out.println("You entered incorrect number, please be attentive repeat Enter");
				System.out.println("Number must be [0-3]");
			}
		}
		sc.close();
	}

}
