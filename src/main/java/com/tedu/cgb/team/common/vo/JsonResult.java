package com.tedu.cgb.team.common.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JsonResult implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 1代表SUCCESS，0代表ERROR */
	private int state = 1;
	private String message = "ok";
	private Object data;
	
	public JsonResult(Object data) {
		this.data = data;
	}
	public JsonResult(String message) {
		this.message = message;
	}
	public JsonResult(Throwable t) {
		this.message = t.getMessage();
		this.state = 0;
	}
}
