package Model;

import Exceptions.ValueException;

public class Result {
	private Player player;
	private Match match;
	private Integer points;

	public Result(Player player, Match match, Integer points) throws ValueException {
		this.player = player;
		this.match = match;
		setPoints(points);
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
