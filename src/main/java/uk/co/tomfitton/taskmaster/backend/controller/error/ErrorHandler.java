package uk.co.tomfitton.taskmaster.backend.controller.error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public List<Error> processValidationError(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		return processFieldErrors(fieldErrors);
	}
	
	private List<Error> processFieldErrors(List<FieldError> fieldErrors) {
		List<Error> errors = new ArrayList<Error>();
		for (FieldError fieldError : fieldErrors) {
			Error error = new Error(fieldError.getField(), fieldError.getDefaultMessage());
			errors.add(error);
		}
		return errors;
	}
	
}
