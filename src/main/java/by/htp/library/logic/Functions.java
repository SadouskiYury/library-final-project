package by.htp.library.logic;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.htp.library.dao.Dao;
import by.htp.library.entity.Author;
import by.htp.library.entity.Book;
import by.htp.library.entity.Reader;

public class Functions {

	public static void showFunctionReader(Scanner sc, Dao readerDao) {
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
				readerDao.showCatalouge();
				Menu.exitMenu(sc);
				break;
			case "2": {
				int id = checkIdBoock(sc);
				sleep(500);
				readerDao.showDetailsBook(id);
				Menu.exitMenu(sc);
				break;
			}
			default:
				System.out.println("You entered incorrect number, please be attentive repeat Enter");
				System.out.println("Number must be [0-2]");
			}
		}
	}

	public static void showFunctionLibrarian(Scanner sc, Dao librarianDao) {
		label: while (true) {
			Menu.showBoxLibrarian();
			switch (sc.next()) {
			case "0":
				break label;
			case "1":
				Reader reader = new Reader();
				librarianDao.add(reader.createReader(sc));
				Menu.exitMenu(sc);
				break;
			case "2":
				Author author = new Author();
				librarianDao.add(createBook(sc, author));
				Menu.exitMenu(sc);
				break;
			case "3":
				librarianDao.returnBook(checkIdBoock(sc));
				Menu.exitMenu(sc);
				break;
			case "4":
				Menu.issuedBook(sc, librarianDao);
				Menu.exitMenu(sc);
				break;
			case "5":
				librarianDao.showCatalouge();
				Menu.exitMenu(sc);
				break;
			default:
				System.out.println("You entered incorrect number, please be attentive repeat Enter");
				System.out.println("Number must be [0-5]");
			}
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
	
	private static Book createBook(Scanner sc, Author author) {
		Book book = new Book();
		System.out.println("Enter book's tile:");
		book.setTitle(sc.next());
		System.out.println("Enter book's type:");
		book.setType(sc.next());
		System.out.println("Enter book's preface:");
		book.setPreface(sc.next() + sc.nextLine());
		book.setAuthor(author.buildAuthor(sc));
		return book;
	}

	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
