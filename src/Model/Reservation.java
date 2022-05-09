package Model;

public class Reservation {
	private Visitor visitor;
	private Match match;
	private String seatType;
	private Character seatRow;
	private Integer seatNumber;
	private Double cost;

	public Reservation(Visitor visitor, Match match, String seatType, Character seatRow, Integer seatNumber, Double cost) {
		this.visitor = visitor;
		this.match = match;
		this.seatType = seatType;
		this.seatRow = seatRow;
		this.seatNumber = seatNumber;
		this.cost = cost;
	}
	public Reservation(Match match, String seatType, Character seatRow, Integer seatNumber, Double cost) {
		this(null, match, seatType, seatRow, seatNumber, cost);
	}

	public Visitor getVisitor() {
		return visitor;
	}
	public Match getMatch() {
		return match;
	}
	public Double getCost() {
		return cost;
	}
	public String getSeatType() {
		return seatType;
	}
	public Character getSeatRow() {
		return seatRow;
	}
	public Integer getSeatNumber() {
		return seatNumber;
	}

	public String getCodeSeat() {
		return seatType + " " + seatRow + seatNumber;
	}
}
