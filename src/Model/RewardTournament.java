package Model;

public class RewardTournament {
	private Tournament tournament;
	private Reward reward;
	private int place;

	public RewardTournament(Tournament tournament, Reward reward, int place) {
		this.tournament = tournament;
		this.reward = reward;
		this.place = place;
	}
}
