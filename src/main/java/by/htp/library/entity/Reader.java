package by.htp.library.entity;

public class Reader {
	private String name;
	private String surname;
	private int numberLibraryCard;
	private String password;
	private int numberPhone;

	public Reader() {
		super();

	}

	public Reader(String name, String surname, int numberLibraryCard, String password, int numberPhone) {
		this.name = name;
		this.surname = surname;
		this.numberLibraryCard = numberLibraryCard;
		this.password = password;
		this.numberPhone = numberPhone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getNumberLibraryCard() {
		return numberLibraryCard;
	}

	public void setNumberLibraryCard(int numberLibraryCard) {
		this.numberLibraryCard = numberLibraryCard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(int numberPhone) {
		this.numberPhone = numberPhone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + numberLibraryCard;
		result = prime * result + numberPhone;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Reader other = (Reader) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberLibraryCard != other.numberLibraryCard)
			return false;
		if (numberPhone != other.numberPhone)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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
		return "Reader [name=" + name + ", surname=" + surname + ", numberLibraryCard=" + numberLibraryCard
				+ ", password=" + password + ", numberPhone=" + numberPhone + "]";
	}

}
