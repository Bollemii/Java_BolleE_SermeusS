package View;

import Business.ManagerUtils;
import Business.TournamentManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowReservationsVisitor extends JPanel {
	private TournamentManagement manager;
	private JPanel visitorPanel;
	private JLabel title;
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox<String> visitorBox;
	private JButton submit;

	public ShowReservationsVisitor() {
		manager = new TournamentManagement();
		setLayout(new BorderLayout());

		// title
		title = new JLabel("Réservations d'un visiteur", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		add(title, BorderLayout.NORTH);

		// table
		String[] tableHead = {"Match", "Place", "Cout"};
		tableModel = new DefaultTableModel(tableHead, 0);
		table = new JTable(tableModel);
		table.setRowHeight(30);
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		add(new JScrollPane(table), BorderLayout.CENTER);

		visitorPanel = new JPanel();

		visitorBox = new JComboBox<>(manager.getVisitorsList().toArray(new String[0]));
		visitorPanel.add(visitorBox);
		submit = new JButton("Submit");
		submit.addActionListener(new ButtonListener());
		visitorPanel.add(submit);
		add(visitorPanel, BorderLayout.SOUTH);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			tableModel.setRowCount(0);
			int visitorID = ManagerUtils.getIDFromDescription(visitorBox.getSelectedItem().toString());
			for (String[] match : manager.getReservationsVisitor(visitorID)) {
				tableModel.addRow(match);
			}
		}
	}
}
