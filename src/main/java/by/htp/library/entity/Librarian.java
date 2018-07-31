package by.htp.library.entity;

public class Librarian {
	private String name;
	private String surname;
	private EnumNameColumn loggin;
	private EnumNameColumn password;

	public Librarian() {
		super();

	}

	public Librarian(String name, String surname, EnumNameColumn loggin, EnumNameColumn password) {
		this.name = name;
		this.surname = surname;
		this.loggin = loggin;
		this.password = password;
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

	public EnumNameColumn getLoggin() {
		return loggin;
	}

	public void setLoggin(EnumNameColumn loggin) {
		this.loggin = loggin;
	}

	public EnumNameColumn getPassword() {
		return password;
	}

	public void setPassword(EnumNameColumn password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loggin == null) ? 0 : loggin.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Librarian other = (Librarian) obj;
		if (loggin != other.loggin)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password != other.password)
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
		return "Librarian [name=" + name + ", surname=" + surname + ", loggin=" + loggin + ", password=" + password
				+ "]";
	}

}
