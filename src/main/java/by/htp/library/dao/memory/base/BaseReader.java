package by.htp.library.dao.memory.base;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.entity.Reader;

public class BaseReader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8854273386057245811L;
	private List<Reader> reader = new ArrayList<>();

	public BaseReader() {
		super();

	}

	public BaseReader(List<Reader> reader) {
		this.reader = reader;
	}

	public List<Reader> getReader() {
		return reader;
	}

	public void setReader(List<Reader> reader) {
		this.reader = reader;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reader == null) ? 0 : reader.hashCode());
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
		BaseReader other = (BaseReader) obj;
		if (reader == null) {
			if (other.reader != null)
				return false;
		} else if (!reader.equals(other.reader))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseReader [reader=" + reader + "]";
	}

}
