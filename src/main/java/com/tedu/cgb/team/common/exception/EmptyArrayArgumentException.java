package com.tedu.cgb.team.common.exception;

public class EmptyArrayArgumentException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	public EmptyArrayArgumentException() {
		super();
	}
	public EmptyArrayArgumentException(String s) {
		super(s);
	}
	public EmptyArrayArgumentException(Throwable cause) {
		super(cause);
	}
	public EmptyArrayArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
}
