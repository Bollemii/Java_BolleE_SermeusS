package Model;

import Exceptions.ValueException;

public class Reward {
	private String name;
	private Double cost;

	public Reward(String name, Double cost) throws ValueException {
		this.name = name;
		setCost(cost);
	}

	public void setCost(Double cost) throws ValueException {
		if (cost != null && cost < 0)
			throw new ValueException("Le prix d'une récompense doit être positif");
		this.cost = cost;
	}
}
