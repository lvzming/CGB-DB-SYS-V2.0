package com.tedu.cgb.team.common.exception;

public class NumberArgumentException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	public NumberArgumentException() {
		super();
	}
	public NumberArgumentException(String s) {
		super(s);
	}
	public NumberArgumentException(Throwable cause) {
		super(cause);
	}
	public NumberArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
}
