package Model;

import Exceptions.ValueException;

public class RewardTournament {
	private Tournament tournament;
	private Reward reward;
	private Integer place;

	public RewardTournament(Tournament tournament, Reward reward, Integer place) throws ValueException {
		this.tournament = tournament;
		this.reward = reward;
		setPlace(place);
	}

	public void setPlace(Integer place) throws ValueException {
		if (place != null && place < 0)
			throw new ValueException("La place à un tournoi doit être positif");
		this.place = place;
	}
}
