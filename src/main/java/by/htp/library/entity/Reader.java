package by.htp.library.entity;

public class Reader {
	private String name;
	private String surname;
	private String numberLibraryCard;
	private String password;
	private int id;
	private int numberPhone;

	public Reader() {
		super();

	}

	public Reader(String name, String surname, String numberLibraryCard, String password, int id, int numberPhone) {
		this.name = name;
		this.surname = surname;
		this.numberLibraryCard = numberLibraryCard;
		this.password = password;
		this.id = id;
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

	public String getNumberLibraryCard() {
		return numberLibraryCard;
	}

	public void setNumberLibraryCard(String numberLibraryCard) {
		this.numberLibraryCard = numberLibraryCard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((numberLibraryCard == null) ? 0 : numberLibraryCard.hashCode());
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
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberLibraryCard == null) {
			if (other.numberLibraryCard != null)
				return false;
		} else if (!numberLibraryCard.equals(other.numberLibraryCard))
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
		return "Reader [id=" + id + " ,name=" + name + ", surname=" + surname + ", numberLibraryCard="
				+ numberLibraryCard + ", password=" + password + ", numberPhone=" + numberPhone + "]";
	}

}
