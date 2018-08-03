package by.htp.library.dao.memory.base;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import by.htp.library.entity.Report;

public class BaseReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6110077187668104669L;
	private List<Report> reports = new LinkedList<Report>();

	public BaseReport() {
		super();
	}

	public BaseReport(List<Report> reports) {
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

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

}
