package Exceptions;

public class RequirementFieldException extends Exception {
	private String message;

	public RequirementFieldException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return "Des champs obligatoires ne sont pas remplis : " + message;
	}
}
