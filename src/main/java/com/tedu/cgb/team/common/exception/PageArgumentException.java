package com.tedu.cgb.team.common.exception;

public class PageArgumentException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	public PageArgumentException() {
		super();
	}
	public PageArgumentException(String s) {
		super(s);
	}
	public PageArgumentException(Throwable cause) {
		super(cause);
	}
	public PageArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
}
