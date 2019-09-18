package com.tedu.cgb.team.common.exception;

public class IdArgumentException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	public IdArgumentException() {
		super();
	}
	public IdArgumentException(String s) {
		super(s);
	}
	public IdArgumentException(Throwable cause) {
		super(cause);
	}
	public IdArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
}
