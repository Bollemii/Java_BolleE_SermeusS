package View.TableModels;

import Model.Match;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AllMatchsModel extends AbstractTableModel {
	private ArrayList<String> columnsNames;
	private ArrayList<Match> contents;

	public AllMatchsModel(ArrayList<Match> contents) {
		this.columnsNames = new ArrayList<>();
		this.columnsNames.add("Id");
		this.columnsNames.add("Date");
		this.columnsNames.add("Durée");
		this.columnsNames.add("Finale");
		this.columnsNames.add("Commentaire");
		this.columnsNames.add("Tournoi");
		this.columnsNames.add("Arbitre");
		this.columnsNames.add("Emplacement");

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
			case 2 :
				return Integer.class;
			case 1 : return GregorianCalendar.class;
			case 3 : return Boolean.class;
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
		Match match = contents.get(rowIndex);
		switch (columnIndex) {
			case 0 : return match.getId();
			case 1 : {
				if (match.getDateStart() != null)
					return match.getDateStart().getTime();
				return null;
			}
			case 2 : return match.getDuration();
			case 3 : return match.isFinal();
			case 4 : return match.getComment();
			case 5 : return match.getTournament().toString();
			case 6 : return match.getReferee().toString();
			case 7 : return match.getLocation().toString();
			default: return null;
		}
	}
}
