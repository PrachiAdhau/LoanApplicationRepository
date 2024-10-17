package com.customer.exception;

public class InvalidPinCodeException extends RuntimeException
{
	public InvalidPinCodeException(String msg) 
	{
		super(msg);
	}

}
