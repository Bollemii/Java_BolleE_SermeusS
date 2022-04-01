package Model;

public class Location {
	private String name;
	private int nbRows;
	private int nbSeatsPerRow;

	public Location(String name, int nbRows, int nbSeatsPerRow) {
		this.name = name;
		this.nbRows = nbRows;
		this.nbSeatsPerRow = nbSeatsPerRow;
	}
}
