package Model;

import java.io.Serializable;

public class Reservation implements Serializable {
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

	public Match getMatch() {
		return match;
	}
	public Double getCost() {
		return cost;
	}

	public String getCodeSeat() {
		return seatType + " " + seatRow + seatNumber;
	}
}
