package Model;

public class Location {
	private Integer id;
	private String name;
	private Integer nbRows;
	private Integer nbSeatsPerRow;

	public Location(String name, Integer nbRows, Integer nbSeatsPerRow) {
		this.name = name;
		this.nbRows = nbRows;
		this.nbSeatsPerRow = nbSeatsPerRow;
	}
	public Location(String name) {
		this(name, null, null);
	}

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public int nbPlaces() {
		return nbRows * nbSeatsPerRow;
	}
}
