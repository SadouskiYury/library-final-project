package by.htp.library.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Author implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7793382842036044943L;
	private int id;
	private String name;
	private String midlenme;
	private String surname;
	private GregorianCalendar birthDate;

	public Author() {
		super();
	}

	public Author(int id, String name, String midlenme, String surname, GregorianCalendar birthDate) {
		this.id = id;
		this.name = name;
		this.midlenme = midlenme;
		this.surname = surname;
		this.birthDate = birthDate;
	}

	public Author(String name, String midlenme, String surname, GregorianCalendar birthDate) {
		this.name = name;
		this.midlenme = midlenme;
		this.surname = surname;
		this.birthDate = birthDate;
	}

	public Author buildAuthor(Scanner sc) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMidlenme() {
		return midlenme;
	}

	public void setMidlenme(String midlenme) {
		this.midlenme = midlenme;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public GregorianCalendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(GregorianCalendar birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((midlenme == null) ? 0 : midlenme.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (id != other.id)
			return false;
		if (midlenme == null) {
			if (other.midlenme != null)
				return false;
		} else if (!midlenme.equals(other.midlenme))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Author [name=" + name + ", midlenme=" + midlenme + ", surname=" + surname
				+ ", birthDate=" + new SimpleDateFormat("yyyy-MM-dd").format(birthDate.getTime()) + "]";
	}

}
