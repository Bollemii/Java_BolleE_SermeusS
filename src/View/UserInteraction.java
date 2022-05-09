package View;

import javax.swing.*;

public class UserInteraction {
	public UserInteraction() {}

	/**
	 * display an error message
	 * @param message
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

	/**
	 * display the number of lines updated in the dataBase
	 * @param nbLinesUpdated
	 * @Display: nbLinesUpdated in a JOptionPane
	 */
	public void displayDataUpdate(int nbLinesUpdated) {
		JOptionPane.showMessageDialog(
				null,
				nbLinesUpdated + (nbLinesUpdated > 1 ? " lignes modifiées" : " ligne modifiée"),
				"Données à jour",
				JOptionPane.INFORMATION_MESSAGE
		);
	}

	/**
	 * display the message and ask the user his confirmation
	 * @param message
	 * @Display: message in a JOptionPane with YES or NO options
	 * @return 0 - YES, 1 - NO
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
