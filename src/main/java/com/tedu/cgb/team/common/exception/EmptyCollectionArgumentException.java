package com.tedu.cgb.team.common.exception;

public class EmptyCollectionArgumentException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	public EmptyCollectionArgumentException() {
		super();
	}
	public EmptyCollectionArgumentException(String s) {
		super(s);
	}
	public EmptyCollectionArgumentException(Throwable cause) {
		super(cause);
	}
	public EmptyCollectionArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
}
