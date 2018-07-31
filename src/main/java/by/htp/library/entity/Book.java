package by.htp.library.entity;

public class Book {
	private int id_book;
	private String title;
	private Author author;
	private String preface;
	private String type;

	public Book() {
		super();

	}

	public Book(int id_book, String title, Author author, String preface, String type) {
		this.id_book = id_book;
		this.title = title;
		this.author = author;
		this.preface = preface;
		this.type = type;
	}

	public int getId_book() {
		return id_book;
	}

	public void setId_book(int id_book) {
		this.id_book = id_book;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getPreface() {
		return preface;
	}

	public void setPreface(String preface) {
		this.preface = preface;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + id_book;
		result = prime * result + ((preface == null) ? 0 : preface.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (id_book != other.id_book)
			return false;
		if (preface == null) {
			if (other.preface != null)
				return false;
		} else if (!preface.equals(other.preface))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id_book=" + id_book + ", title=" + title + ", author=" + author + ", preface=" + preface
				+ ", type=" + type + "]";
	}

}