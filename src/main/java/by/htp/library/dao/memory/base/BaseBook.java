package by.htp.library.dao.memory.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.entity.Book;

public class BaseBook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7851387711789355828L;
	private List<Book> Catalog = new ArrayList<Book>();

	public BaseBook() {
		super();

	}

	public BaseBook(List<Book> catalog) {
		Catalog = catalog;
	}

	public List<Book> getCatalog() {
		return Catalog;
	}

	public void setCatalog(List<Book> catalog) {
		Catalog = catalog;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Catalog == null) ? 0 : Catalog.hashCode());
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
		BaseBook other = (BaseBook) obj;
		if (Catalog == null) {
			if (other.Catalog != null)
				return false;
		} else if (!Catalog.equals(other.Catalog))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseBook [Catalog=" + Catalog + "]";
	}

}
