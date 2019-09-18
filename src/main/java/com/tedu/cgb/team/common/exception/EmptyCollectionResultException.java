package com.tedu.cgb.team.common.exception;

public class EmptyCollectionResultException extends ResultException {
	private static final long serialVersionUID = 1L;
	public EmptyCollectionResultException() {
		super();
	}
	public EmptyCollectionResultException(String s) {
		super(s);
	}
	public EmptyCollectionResultException(Throwable cause) {
		super(cause);
	}
	public EmptyCollectionResultException(String message, Throwable cause) {
		super(message, cause);
	}
	public EmptyCollectionResultException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
