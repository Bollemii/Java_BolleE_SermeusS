package Exceptions;

public class ValueException extends Exception {
	private String message;

	public ValueException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return "Valeur erronée :" + message;
	}
}
