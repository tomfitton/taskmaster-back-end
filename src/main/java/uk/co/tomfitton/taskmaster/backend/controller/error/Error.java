package uk.co.tomfitton.taskmaster.backend.controller.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

	private String field;
	private String message;
	
}
