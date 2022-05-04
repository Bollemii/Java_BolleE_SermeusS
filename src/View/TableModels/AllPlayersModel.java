package View.TableModels;

import Model.Player;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AllPlayersModel extends AbstractTableModel {
	private ArrayList<String> columnsNames;
	private ArrayList<Player> contents;

	public AllPlayersModel(ArrayList<Player> contents) {
		this.columnsNames = new ArrayList<>();
		this.columnsNames.add("Id");
		this.columnsNames.add("Prénom");
		this.columnsNames.add("Nom");
		this.columnsNames.add("Date de naissance");
		this.columnsNames.add("Genre");
		this.columnsNames.add("Professionnel");
		this.columnsNames.add("Elo");

		this.contents = contents;
	}

	@Override
	public String getColumnName(int column) {
		return columnsNames.get(column);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0 :
			case 6 :
				return Integer.class;
			case 3 : return GregorianCalendar.class;
			case 4 : return Character.class;
			case 5 : return Boolean.class;
			default: return String.class;
		}
	}

	@Override
	public int getRowCount() {
		return contents.size();
	}

	@Override
	public int getColumnCount() {
		return columnsNames.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Player player = contents.get(rowIndex);
		switch (columnIndex) {
			case 0 : return player.getId();
			case 1 : return player.getFirstName();
			case 2 : return player.getLastName();
			case 3 : {
				if (player.getBirthDate() != null)
					return player.getBirthDate().getTime();
				return null;
			}
			case 4 : return player.getGender();
			case 5 : return player.isProfessional();
			case 6 : return player.getElo();
			default: return null;
		}
	}
}
