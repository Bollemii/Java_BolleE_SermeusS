package View;

import Business.ManagerUtils;
import View.TableModels.ReservationVisitorModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowReservationsVisitor extends JPanel {
	private TournamentFormatter formatter;
	private JPanel visitorPanel;
	private JLabel title;
	private JTable table;
	private ReservationVisitorModel model;
	private JComboBox<String> visitorBox;
	private JButton submit;

	public ShowReservationsVisitor() {
		formatter = new TournamentFormatter();
		this.setLayout(new BorderLayout());

		title = new JLabel("Réservations d'un visiteur", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		model = new ReservationVisitorModel();
		table = new JTable(model);
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
			int visitorID = ManagerUtils.getIDFromDescription(visitorBox.getSelectedItem().toString());
			model.setContents(formatter.getReservationsVisitor(visitorID));
			model.fireTableDataChanged();
		}
	}
}
