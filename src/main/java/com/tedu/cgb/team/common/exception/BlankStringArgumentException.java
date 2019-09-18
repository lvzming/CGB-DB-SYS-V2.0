package com.tedu.cgb.team.common.exception;

public class BlankStringArgumentException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

	public BlankStringArgumentException() {
		super();
	}
	public BlankStringArgumentException(String s) {
		super(s);
	}
	public BlankStringArgumentException(Throwable cause) {
		super(cause);
	}
	public BlankStringArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
}
