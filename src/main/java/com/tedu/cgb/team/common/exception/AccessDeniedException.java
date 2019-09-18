package com.tedu.cgb.team.common.exception;

public class AccessDeniedException extends ServiceException {
	private static final long serialVersionUID = 1L;
	public AccessDeniedException() {
		super();
	}
	public AccessDeniedException(String s) {
		super(s);
	}
	public AccessDeniedException(Throwable cause) {
		super(cause);
	}
	public AccessDeniedException(String message, Throwable cause) {
		super(message, cause);
	}
	public AccessDeniedException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
