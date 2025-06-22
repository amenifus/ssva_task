package lt.ssva.task.handlers;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorsHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(ConstraintViolationException ex) {
		String reason = ex.getConstraintViolations().stream().map(violation -> violation.getMessage()).collect(Collectors.joining(", "));
		
		return new ResponseEntity<ErrorResponse>(new ErrorResponse("Neteisingas API kvietimas: " + reason), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({MissingRequestValueException.class, NoResourceFoundException.class})
	public ResponseEntity<ErrorResponse> handleValidationExceptions(Exception  ex) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse("Neteisingas API kvietimas: blogas URL"), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
		String message;
		
		if (ex instanceof UserError) {
			message = ex.getMessage();
		} else {
			UUID errorId = UUID.randomUUID();
		
			System.out.println(String.format("Unexpected error: ID=%s,\n ERROR=%s", errorId, ex));
			message = "Įvyko nenumatyta klaida. ID=" + errorId;
		}
		
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}