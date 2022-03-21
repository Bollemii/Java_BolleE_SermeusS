package Tournois;

public class Result {
	private Player player;
	private Match match;
	private int points;

	public Result(Player player, Match match) {
		this.player = player;
		this.match = match;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
