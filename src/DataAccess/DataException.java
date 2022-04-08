package DataAccess;

public class DataException extends Exception {
	private String message;

	public DataException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return "Erreur lors de la connection à la base de donnée :\n   " + message;
	}
}
