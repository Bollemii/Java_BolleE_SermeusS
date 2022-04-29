package View;

import Business.ManagerUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowReservationsVisitor extends JPanel {
	private TournamentFormatter formatter;
	private JPanel visitorPanel;
	private JLabel title;
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox<String> visitorBox;
	private JButton submit;

	public ShowReservationsVisitor() {
		formatter = new TournamentFormatter();
		this.setLayout(new BorderLayout());

		// title
		title = new JLabel("Réservations d'un visiteur", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		// table
		String[] tableHead = {"Match", "Date de début", "Place", "Prix", "Emplacement"};
		tableModel = new DefaultTableModel(tableHead, 0);
		table = new JTable(tableModel);
		table.setRowHeight(30);
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		this.add(new JScrollPane(table), BorderLayout.CENTER);

		visitorPanel = new JPanel();

		visitorBox = new JComboBox<>(formatter.getVisitorsList().toArray(new String[0]));
		visitorPanel.add(visitorBox);
		submit = new JButton("Valider");
		submit.addActionListener(new ButtonListener());
		visitorPanel.add(submit);
		this.add(visitorPanel, BorderLayout.SOUTH);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			tableModel.setRowCount(0);
			int visitorID = ManagerUtils.getIDFromDescription(visitorBox.getSelectedItem().toString());
			for (String[] match : formatter.getReservationsVisitor(visitorID)) {
				tableModel.addRow(match);
			}
		}
	}
}
