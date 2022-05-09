package View;

import javax.swing.*;

public class UserInteraction {
	public UserInteraction() {}

	/**
	 *
	 * @param message to display
	 * @Display: message in a JOptionPane
	 */
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

	/**
	 *
	 * @param message
	 * @return 0 - OK, 1 - Cancel
	 */
	public int displayConfirmation(String message) {
		return JOptionPane.showConfirmDialog(
				null,
				message,
				"Confirmation",
				JOptionPane.YES_NO_OPTION
		);
	}
}
