package View.TableModels;

import Business.ManagerUtils;
import Model.MatchPlayerResearch;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MatchsPlayerModel extends AbstractTableModel {
	private ArrayList<String> columnsNames;
	private ArrayList<MatchPlayerResearch> contents;

	public MatchsPlayerModel(ArrayList<MatchPlayerResearch> contents) {
		this.columnsNames = new ArrayList<>();
		this.columnsNames.add("Tournoi");
		this.columnsNames.add("Date");
		this.columnsNames.add("Emplacement");
		this.columnsNames.add("Arbitre");
		this.columnsNames.add("Points");

		this.contents = contents;
	}
	public MatchsPlayerModel() {
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
			case 4 : return Integer.class;
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
			case 0 : return match.getMatch().getTournament().toString();
			case 1 : {
				if (match.getMatch().getDateStart() != null)
					return ManagerUtils.getDateHourString(match.getMatch().getDateStart());
				return null;
			}
			case 2 : return match.getMatch().getLocation().toString();
			case 3 : return match.getMatch().getReferee().toString();
			case 4 : return match.getPoints();
			default: return null;
		}
	}
}
