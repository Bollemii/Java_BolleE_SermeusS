package Tournois;

import java.util.ArrayList;

public class Tournoi {
	private String name;
	private boolean isOfficial;
	private ArrayList<Match> matchs;

	public Tournoi(String name, boolean isOfficial) {
		this.name = name;
		this.isOfficial = isOfficial;
		matchs = new ArrayList<>();
	}
}
