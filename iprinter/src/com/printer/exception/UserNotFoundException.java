package com.printer.exception;

public class UserNotFoundException extends Exception {
	
	public UserNotFoundException(String msg) {
		super(msg);
	}
	
	
	public UserNotFoundException(String msg, Throwable e) {
		super(msg, e);
	}
	
	
}
