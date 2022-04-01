package Model;

public class RewardForPlace {
	private Tournament tournament;
	private Reward reward;
	private int place;

	public RewardForPlace(Tournament tournament, Reward reward, int place) {
		this.tournament = tournament;
		this.reward = reward;
		this.place = place;
	}
}
