package Model;

public class Reservation {
	private Visitor visitor;
	private Match match;
	private String seatType;
	private char seatRow;
	private int seatNumber;
	private double cost;

	public Reservation(Visitor visitor, Match match, String seatType, char seatRow, int seatNumber, double cost) {
		this.visitor = visitor;
		this.match = match;
		this.seatType = seatType;
		this.seatRow = seatRow;
		this.seatNumber = seatNumber;
		this.cost = cost;
	}
}
