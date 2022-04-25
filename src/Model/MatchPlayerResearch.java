package Model;

public class MatchPlayerResearch {
	private Match match;
	private Player player;
	private Integer points;
	private Referee referee;
	private Location location;
	private Tournament tournament;

	public MatchPlayerResearch(Match match, Player player, Integer points, Referee referee, Location location, Tournament tournament) {
		this.match = match;
		this.player = player;
		this.points = points;
		this.referee = referee;
		this.location = location;
		this.tournament = tournament;
	}
	public MatchPlayerResearch(Match match, Integer points, Referee referee, Location location, Tournament tournament) {
		this(match, null, points, referee, location, tournament);
	}
	public MatchPlayerResearch(Match match, Player player, Integer points) {
		this(match, player, points, null, null, null);
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
	public Referee getReferee() {
		return referee;
	}
	public Location getLocation() {
		return location;
	}
	public Tournament getTournament() {
		return tournament;
	}
}
