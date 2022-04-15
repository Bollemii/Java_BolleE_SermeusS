import DataAccess.*;
import Model.*;
import View.*;

import java.awt.*;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		//new MainWindow();
		try {
			DataAccess dataAccess = new DBAccess();
			ArrayList<Match> list;
			list = dataAccess.getMatchsPlayer(new Player(101, "Emilien", "Bolle", null, 'M', true, 12));
			//list = dataAccess.getAllMatchs();
			for (Match match : list) {
				System.out.println(match);
			}
			dataAccess.closeConnection();
		} catch (DataException exception) {
			System.out.println(exception.getMessage());
		}
	}
}