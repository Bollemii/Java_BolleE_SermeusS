package Model;

public class Result {
	private Player player;
	private Match match;
	private Integer points;

	public Result(Player player, Match match, Integer points) {
		this.player = player;
		this.match = match;
		this.points = points;
	}
	public Result(Player player, Match match) {
		this(player, match, null);
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

	public void setPoints(int points) {
		this.points = points;
	}
}
