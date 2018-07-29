package by.htp.library.entity;

public enum EnumLibrarian {
	LOGIN("Librarian"), PASSWORD("qweasd1988");
	private String value;

	private EnumLibrarian(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
