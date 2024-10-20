package com.customer.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.customer.exception.InvalidMobileNoException;
import com.customer.exception.InvalidPinCodeException;
import com.customer.exception.invalidEmailException;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

	@ExceptionHandler(invalidEmailException.class)
	public ResponseEntity<String>handelinvalidGmailException(invalidEmailException inm)
	{
		String msg=inm.getMessage();
		return new ResponseEntity<String>(msg,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidMobileNoException.class)
	public ResponseEntity<String>handelinvalidMobileNo(InvalidMobileNoException inm)
	{
		String msg=inm.getMessage();
		return new ResponseEntity<String>(msg,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(InvalidPinCodeException.class)
	public ResponseEntity<String>handleInvalidPinCodeException(InvalidPinCodeException inm)
	{
		String msg=inm.getMessage();
		return new ResponseEntity<String>(msg,HttpStatus.BAD_REQUEST);
	}
}
