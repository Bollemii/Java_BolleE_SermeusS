package Model;

import Exceptions.ValueException;

public class MatchPlayerResearch {
	private Match match;
	private Player player;
	private Integer points;

	public MatchPlayerResearch(Match match, Player player, Integer points) throws ValueException {
		this.match = match;
		this.player = player;
		setPoints(points);
	}
	public MatchPlayerResearch(Match match, Integer points) throws ValueException {
		this(match, null, points);
	}

	public Match getMatch() {
		return match;
	}
	public Player getPlayer() {
		return player;
	}
	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) throws ValueException {
		if (points != null && points < 0)
			throw new ValueException("Les points à un match doivent être positifs");
		this.points = points;
	}
}
