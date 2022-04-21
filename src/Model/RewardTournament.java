package Model;

import java.io.Serializable;

public class RewardTournament implements Serializable {
	private Tournament tournament;
	private Reward reward;
	private int place;

	public RewardTournament(Tournament tournament, Reward reward, int place) {
		this.tournament = tournament;
		this.reward = reward;
		this.place = place;
	}
}
