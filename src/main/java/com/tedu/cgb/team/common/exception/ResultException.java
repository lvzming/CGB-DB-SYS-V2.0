package com.tedu.cgb.team.common.exception;

public class ResultException extends ServiceException {
	private static final long serialVersionUID = 1L;
	public ResultException() {
		super();
	}
	public ResultException(String s) {
		super(s);
	}
	public ResultException(Throwable cause) {
		super(cause);
	}
	public ResultException(String message, Throwable cause) {
		super(message, cause);
	}
	public ResultException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
