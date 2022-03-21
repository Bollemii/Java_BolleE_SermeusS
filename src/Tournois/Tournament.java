package Tournois;

import java.util.ArrayList;

public class Tournament {
	private String name;
	private boolean isOfficial;
	private ArrayList<Match> matchs;

	public Tournament(String name, boolean isOfficial) {
		this.name = name;
		this.isOfficial = isOfficial;
		matchs = new ArrayList<>();
	}
}
