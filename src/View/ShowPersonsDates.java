package View;

import View.TableModels.PersonsBirthdaysModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ShowPersonsDates extends JPanel {
	private TournamentFormatter formatter;
	private UserInteraction userInteraction;
	private JLabel title;
	private PersonsBirthdaysModel model;
	private JTable table;
	private JSpinner date1Spinner, date2Spinner;
	private JButton submit;

	public ShowPersonsDates() {
		formatter = new TournamentFormatter();
		userInteraction = new UserInteraction();

		this.setLayout(new BorderLayout());

		title = new JLabel("Personnes selon anniversaire", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		model = new PersonsBirthdaysModel();
		table = new JTable(model);
		table.setRowHeight(30);
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		this.add(new JScrollPane(table), BorderLayout.CENTER);

		JPanel choicePanel = new JPanel();
		date1Spinner = new JSpinner(new SpinnerDateModel(Date.from(Instant.now()), null, null, Calendar.YEAR));
		date1Spinner.setEditor(new JSpinner.DateEditor(date1Spinner, "dd-MM-yyyy"));
		choicePanel.add(date1Spinner);
		date2Spinner = new JSpinner(new SpinnerDateModel(Date.from(Instant.now()), null, null, Calendar.YEAR));
		date2Spinner.setEditor(new JSpinner.DateEditor(date2Spinner, "dd-MM-yyyy"));
		choicePanel.add(date2Spinner);
		submit = new JButton("Valider");
		submit.addActionListener(new ButtonListener());
		submit.setFont(new Font("Arial", Font.PLAIN, 20));
		choicePanel.add(submit);
		this.add(choicePanel, BorderLayout.SOUTH);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			GregorianCalendar date1 = new GregorianCalendar(
				Integer.parseInt(new SimpleDateFormat("yyyy").format(date1Spinner.getValue())),
				Integer.parseInt(new SimpleDateFormat("MM").format(date1Spinner.getValue()))-1,
				Integer.parseInt(new SimpleDateFormat("dd").format(date1Spinner.getValue()))
			);
			GregorianCalendar date2 = new GregorianCalendar(
					Integer.parseInt(new SimpleDateFormat("yyyy").format(date2Spinner.getValue())),
					Integer.parseInt(new SimpleDateFormat("MM").format(date2Spinner.getValue()))-1,
					Integer.parseInt(new SimpleDateFormat("dd").format(date2Spinner.getValue()))
			);
			formatter.getPersonsBirthdays(date1, date2);
		}
	}
}
