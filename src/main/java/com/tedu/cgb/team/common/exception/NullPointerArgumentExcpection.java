package com.tedu.cgb.team.common.exception;

public class NullPointerArgumentExcpection extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	public NullPointerArgumentExcpection() {
		super();
	}
	public NullPointerArgumentExcpection(String s) {
		super(s);
	}
	public NullPointerArgumentExcpection(Throwable cause) {
		super(cause);
	}
	public NullPointerArgumentExcpection(String message, Throwable cause) {
		super(message, cause);
	}

}
