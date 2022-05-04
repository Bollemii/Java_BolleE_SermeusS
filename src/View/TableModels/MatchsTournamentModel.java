package View.TableModels;

import Model.MatchPlayerResearch;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MatchsTournamentModel extends AbstractTableModel {
	private ArrayList<String> columnsNames;
	private ArrayList<MatchPlayerResearch> contents;

	public MatchsTournamentModel(ArrayList<MatchPlayerResearch> contents) {
		this.columnsNames = new ArrayList<>();
		this.columnsNames.add("Date");
		this.columnsNames.add("Prénom");
		this.columnsNames.add("Nom");
		this.columnsNames.add("Elo");
		this.columnsNames.add("Points");

		this.contents = contents;
	}
	public MatchsTournamentModel() {
		this(new ArrayList<>());
	}

	public void setContents(ArrayList<MatchPlayerResearch> contents) {
		this.contents = contents;
	}

	public void clear() {
		contents.clear();
	}

	@Override
	public String getColumnName(int column) {
		return columnsNames.get(column);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0 : return GregorianCalendar.class;
			case 3 :
			case 4 :
				return Integer.class;
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
		MatchPlayerResearch match = contents.get(rowIndex);
		switch (columnIndex) {
			case 0 : {
				if (match.getMatch().getDateStart() != null)
					return match.getMatch().getDateStart().getTime();
				return null;
			}
			case 1 : return match.getPlayer().getFirstName();
			case 2 : return match.getPlayer().getLastName();
			case 3 : return match.getPlayer().getElo();
			case 4 : return match.getPoints();
			default: return null;
		}
	}
}