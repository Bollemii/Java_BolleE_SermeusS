package Model;

import Exceptions.ValueException;

public class Reservation {
	private Visitor visitor;
	private Match match;
	private String seatType;
	private Character seatRow;
	private Integer seatNumber;
	private Double cost;

	public Reservation(Visitor visitor, Match match, String seatType, Character seatRow, Integer seatNumber, Double cost) throws ValueException {
		this.visitor = visitor;
		this.match = match;
		this.seatType = seatType;
		this.seatRow = seatRow;
		setSeatNumber(seatNumber);
		setCost(cost);
	}
	public Reservation(Match match, String seatType, Character seatRow, Integer seatNumber, Double cost) throws ValueException {
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

	public void setSeatNumber(Integer seatNumber) throws ValueException {
		if (seatNumber != null && seatNumber < 0)
			throw new ValueException("Le numéro de siège doit être positif");
		this.seatNumber = seatNumber;
	}

	public void setCost(Double cost) throws ValueException {
		if (cost != null && cost < 0)
			throw new ValueException("Le coût d'une réservation doit être positif");
		this.cost = cost;
	}
}
