package lt.ssva.task.handlers;

public class UserError extends RuntimeException {
	
	public UserError(String message) {
		super(message);
	}

}