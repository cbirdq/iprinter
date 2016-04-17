package com.printer.exception;

public class RegisterFailedException extends Exception {
	
	public RegisterFailedException(String msg) {
		super(msg);
	}
	
	
	public RegisterFailedException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
