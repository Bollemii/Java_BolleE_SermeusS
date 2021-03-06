package View.TableModels;

import Model.Person;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class PersonsBirthdaysModel extends AbstractTableModel {
	private ArrayList<String> columnsNames;
	private ArrayList<Person> contents;

	public PersonsBirthdaysModel(ArrayList<Person> contents) {
		this.columnsNames = new ArrayList<>();
		this.columnsNames.add("Id");
		this.columnsNames.add("Prénom");
		this.columnsNames.add("Nom");
		this.columnsNames.add("Anniversaire");
		this.columnsNames.add("Genre");
		this.columnsNames.add("Type");

		this.contents = contents;
	}
	public PersonsBirthdaysModel() {
		this(new ArrayList<>());
	}

	public void setContents(ArrayList<Person> contents) {
		this.contents = contents;
	}

	@Override
	public String getColumnName(int column) {
		return columnsNames.get(column);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0 : return Integer.class;
			case 3 : return Date.class;
			case 4 : return Character.class;
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
		Person person = contents.get(rowIndex);
		switch (columnIndex) {
			case 0 : return person.getId();
			case 1 : return person.getFirstName();
			case 2 : return person.getLastName();
			case 3 : return person.getBirthDate().getTime();
			case 4 : return person.getGender();
			case 5 : return person.getTypePerson();
			default: return null;
		}
	}
}
