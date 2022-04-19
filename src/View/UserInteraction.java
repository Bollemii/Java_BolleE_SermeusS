package View;

import javax.swing.*;

public class UserInteraction {
	public UserInteraction() {}

	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(
				null,
				message,
				"Erreur",
				JOptionPane.ERROR_MESSAGE
		);
	}

	public void displayDataUpdate(int nbLinesUpdated) {
		JOptionPane.showMessageDialog(
				null,
				nbLinesUpdated + (nbLinesUpdated > 1 ? " lignes modifiées" : " ligne modifiée"),
				"Données à jour",
				JOptionPane.INFORMATION_MESSAGE
		);
	}
}
