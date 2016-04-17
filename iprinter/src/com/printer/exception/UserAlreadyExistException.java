package com.printer.exception;

public class UserAlreadyExistException extends Exception {

	public UserAlreadyExistException(String msg) {
		super(msg);
	}
	
	
	public UserAlreadyExistException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
