import DataAccess.*;
import Model.*;
import View.*;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		//new MainWindow();
		try {
			DataAccess dataAccess = new DBAccess();
			ArrayList<MatchResearch> list;
			//list = dataAccess.getMatchsPlayer(new Player(101, "Emilien", "Bolle", null, 'M', true, 12));
			list = dataAccess.getAllMatchs();
			for (MatchResearch match : list) {
				System.out.println(match);
			}
		} catch (DataException exception) {
			System.out.println(exception.getMessage());
		}
	}
}

	}