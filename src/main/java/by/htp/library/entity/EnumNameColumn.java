package by.htp.library.entity;

public enum EnumNameColumn {
	READER_LOGIN("login"), READER_PASSWORD("password"), READER_NAME("name"), READER_ID("id_reader"), READER_SURNAME(
			"surname"), READER_NUMBER_PHONE("number_phone"), BOOK_TITLE("title"), BOOK_ID("id"), BOOK_PREFACE(
					"preface"), BOOK_TYPE("type"), AUTHOR_ID("id"), AUTHOR_NAME("name"), AUTHOR_SURNAME(
							"surname"), AUTHOR_MIDLENAME("midlename"), AUTHOR_BIRTHDAY("birthday");

	private String value;

	private EnumNameColumn(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
