package View;

import View.Forms.ReservationForm;

import javax.swing.*;
import java.awt.*;

public class ShowNewReservation extends JPanel {
	private JPanel mainPanel;
	private ReservationForm reservationForm;
	private JLabel title;

	public ShowNewReservation() {
		this.setLayout(new BorderLayout());

		title = new JLabel("Nouvelle réservation", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		reservationForm = new ReservationForm();
		mainPanel.add(reservationForm);

		this.add(mainPanel, BorderLayout.CENTER);
	}
}
