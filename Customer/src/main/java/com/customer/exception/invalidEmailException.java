package com.customer.exception;

public class invalidEmailException extends RuntimeException{
	public invalidEmailException(String msg) {
		super(msg);
	}

}
