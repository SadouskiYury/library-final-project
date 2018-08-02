package by.htp.library.dao.memory.serializble;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import by.htp.library.dao.memory.base.BaseBook;
import by.htp.library.dao.memory.base.BaseReader;
import by.htp.library.dao.memory.base.BaseReport;

public class Serializable {
	private ObjectInputStream ois;
	private BaseReader readerBase;
	private BaseBook bookBase;
	private BaseReport reportBase;
	private ObjectOutputStream ous;

	public BaseReader readReaderBase() {
		try {
			ois = new ObjectInputStream(new FileInputStream("Serializable/BaseReader.txt"));
			readerBase = (BaseReader) ois.readObject();
		} catch (FileNotFoundException e) {
			System.err.println("File BaseReader.txt not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Impossible read file BaseReader.txt");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("File don't have BaseReader's object");
			e.printStackTrace();
		}

		return readerBase;
	}

	public BaseBook readBookBase() {
		try {

			ois = new ObjectInputStream(new FileInputStream("Serializable/BaseBook.txt"));
			bookBase = (BaseBook) ois.readObject();
		} catch (FileNotFoundException e) {
			System.err.println("File BaseBook.txt not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Impossible read file BaseBook.txt");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("File don't have BaseBook's object");
			e.printStackTrace();
		}
		return bookBase;
	}

	public BaseReport readReportBase() {
		try {
			ois = new ObjectInputStream(new FileInputStream("Serializable/BaseReport.txt"));
			reportBase = (BaseReport) ois.readObject();
		} catch (FileNotFoundException e) {
			System.err.println("File BaseReport.txt not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Impossible read file BaseReport.txt");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("File don't have BaseRepoert's object");
			e.printStackTrace();
		}
		return reportBase;
	}

	public void writeReaderBase(BaseReader readerBase) {
		try {
			ous = new ObjectOutputStream(new FileOutputStream(createNewFile("Serializable/BaseReader.txt")));
			ous.writeObject(readerBase);
			ous.flush();
			ous.close();
		} catch (FileNotFoundException e) {
			System.out.println("File BaseReader.txt not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Impossible write file BaseBook.txt");
			e.printStackTrace();
		}
	}

	public void writetBookBase(BaseBook base) {

		try {
			ous = new ObjectOutputStream(new FileOutputStream(createNewFile("Serializable/BaseBook.txt")));
			ous.writeObject(base);
			ous.flush();
			ous.close();

		} catch (

		FileNotFoundException e) {
			System.out.println("File BaseBook.txt not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Impossible write BaseBook.txt");
			e.printStackTrace();
		}

	}

	public void writeReportBase(BaseReport base) {

		try {
			ous = new ObjectOutputStream(new FileOutputStream(createNewFile("Serializable/BaseReport.txt")));
			ous.writeObject(base);
			ous.flush();
			ous.close();

		} catch (FileNotFoundException e) {
			System.out.println("File BaseReport.txt not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Impossible write BaseReport.txt");
			e.printStackTrace();
		}
	}

	private File createNewFile(String path) {
		File f = new File(path);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				System.err.println("Cant creat new file!");
				e.printStackTrace();
			}
		}
		return f;
	}
}
