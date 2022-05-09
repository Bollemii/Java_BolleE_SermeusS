package Model;

public class Location {
	private Integer id;
	private String name;
	private Integer nbRows;
	private Integer nbSeatsPerRow;

	public Location(Integer id, String name, Integer nbRows, Integer nbSeatsPerRow) {
		this.id = id;
		this.name = name;
		this.nbRows = nbRows;
		this.nbSeatsPerRow = nbSeatsPerRow;
	}
	public Location(Integer id) {
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

	@Override
	public String toString() {
		return getName() + " (#" + getId() + ") : " + nbSeats() + " places";
	}
}
