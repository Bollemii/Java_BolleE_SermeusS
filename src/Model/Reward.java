package Model;

import java.io.Serializable;

public class Reward implements Serializable {
	private String name;
	private double cost;

	public Reward(String name, double cost) {
		this.name = name;
		this.cost = cost;
	}
}
