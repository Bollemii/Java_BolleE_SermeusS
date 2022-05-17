package Model;

import Exceptions.ValueException;

public class Location {
	private Integer id;
	private String name;
	private Integer nbRows;
	private Integer nbSeatsPerRow;

	public Location(Integer id, String name, Integer nbRows, Integer nbSeatsPerRow) throws ValueException {
		this.id = id;
		this.name = name;
		setNbRows(nbRows);
		setNbSeatsPerRow(nbSeatsPerRow);
	}
	public Location(Integer id) throws ValueException {
		this(id, null, null, null);
	}

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Integer getNbRows() {
		return nbRows;
	}
	public Integer getNbSeatsPerRow() {
		return nbSeatsPerRow;
	}

	public int nbSeats() {
		return nbRows * nbSeatsPerRow;
	}

	public void setNbRows(Integer nbRows) throws ValueException {
		if (nbRows != null && nbRows < 0)
			throw new ValueException("Le nombre de rangées doit être positif");
		this.nbRows = nbRows;
	}

	public void setNbSeatsPerRow(Integer nbSeatsPerRow) throws ValueException {
		if (nbSeatsPerRow != null && nbSeatsPerRow < 0)
			throw new ValueException("Le nombre de sièges par rangée doit être positif");
		this.nbSeatsPerRow = nbSeatsPerRow;
	}

	@Override
	public String toString() {
		return getName() + " (#" + getId() + ") : " + nbSeats() + " places";
	}
}
