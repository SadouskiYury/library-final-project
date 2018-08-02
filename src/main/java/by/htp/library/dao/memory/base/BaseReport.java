package by.htp.library.dao.memory.base;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import by.htp.library.entity.Report;

public class BaseReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6110077187668104669L;
	private Set<Report> reports = new TreeSet<Report>(new Comparator<Report>() {

		@Override
		public int compare(Report o1, Report o2) {
			int result = o1.getTake_date().compareTo(o2.getTake_date());
			if (result == 0)
				return o1.getBook().getTitle().compareToIgnoreCase(o2.getBook().getTitle());
			return result;
		}
	});

	public BaseReport() {
		super();
	}

	public BaseReport(Set<Report> reports) {
		this.reports = reports;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reports == null) ? 0 : reports.hashCode());
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
		BaseReport other = (BaseReport) obj;
		if (reports == null) {
			if (other.reports != null)
				return false;
		} else if (!reports.equals(other.reports))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseReport [reports=" + reports + "]";
	}

}
