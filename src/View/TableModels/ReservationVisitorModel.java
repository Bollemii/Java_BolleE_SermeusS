package View.TableModels;

import Business.ManagerUtils;
import Model.Reservation;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ReservationVisitorModel extends AbstractTableModel {
	private ArrayList<String> columnsNames;
	private ArrayList<Reservation> contents;

	public ReservationVisitorModel(ArrayList<Reservation> contents) {
		this.columnsNames = new ArrayList<>();
		this.columnsNames.add("Tournoi");
		this.columnsNames.add("Date");
		this.columnsNames.add("Place");
		this.columnsNames.add("Prix");
		this.columnsNames.add("Emplacement");

		this.contents = contents;
	}
	public ReservationVisitorModel() {
		this(new ArrayList<>());
	}

	public void setContents(ArrayList<Reservation> contents) {
		this.contents = contents;
	}

	@Override
	public String getColumnName(int column) {
		return columnsNames.get(column);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 3 : return Double.class;
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
		Reservation reservation = contents.get(rowIndex);
		switch (columnIndex) {
			case 0 : return reservation.getMatch().getTournament().toString();
			case 1 : {
				if (reservation.getMatch().getDateStart() != null)
					return ManagerUtils.getDateHourString(reservation.getMatch().getDateStart());
				return null;
			}
			case 2 : return reservation.getCodeSeat();
			case 3 : return reservation.getCost();
			case 4 : return reservation.getMatch().getLocation().toString();
			default: return null;
		}
	}
}
