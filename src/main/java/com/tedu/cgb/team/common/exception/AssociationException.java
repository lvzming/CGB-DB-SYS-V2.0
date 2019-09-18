package com.tedu.cgb.team.common.exception;

public class AssociationException extends ResultException {
	private static final long serialVersionUID = 1L;
	public AssociationException() {
		super();
	}
	public AssociationException(String s) {
		super(s);
	}
	public AssociationException(Throwable cause) {
		super(cause);
	}
	public AssociationException(String message, Throwable cause) {
		super(message, cause);
	}
	public AssociationException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
