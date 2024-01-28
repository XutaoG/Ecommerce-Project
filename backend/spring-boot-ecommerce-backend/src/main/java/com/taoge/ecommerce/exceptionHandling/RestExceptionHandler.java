package com.taoge.ecommerce.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler
{
	// Handles NotFoundException Thrown
	@ExceptionHandler
	public ResponseEntity<NotFoundErrorResponse> handleException(NotFoundException notFoundException)
	{
		NotFoundErrorResponse notFoundErrorResponse = new NotFoundErrorResponse();

		notFoundErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		notFoundErrorResponse.setMessage(notFoundException.getMessage());
		notFoundErrorResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(notFoundErrorResponse, HttpStatus.NOT_FOUND);
	}

	// Handles any other exceptions thrown
	@ExceptionHandler
	public ResponseEntity<NotFoundErrorResponse> handleException(Exception exception)
	{
		NotFoundErrorResponse errorResponse = new NotFoundErrorResponse();

		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
