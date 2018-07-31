package by.htp.library.entity;

public enum Librarian {

	NAME("Marina"), SURNAME("Petrovna"), LOGIN("1"), PASSWORD("1");
	private String value;

	private Librarian(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
