package Model;

public class MatchPlayerResearch {
	private Match match;
	private Player player;
	private Integer points;

	public MatchPlayerResearch(Match match, Player player, Integer points) {
		this.match = match;
		this.player = player;
		this.points = points;
	}
	public MatchPlayerResearch(Match match, Integer points) {
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
}
