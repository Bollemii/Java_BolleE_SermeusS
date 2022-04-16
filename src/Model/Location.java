package Model;

import java.io.Serializable;

public class Location implements Serializable {
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
	public Location(Integer id, String name) {
		this(id, name, null, null);
	}
	public Location(String name) {
		this(null, name, null, null);
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
