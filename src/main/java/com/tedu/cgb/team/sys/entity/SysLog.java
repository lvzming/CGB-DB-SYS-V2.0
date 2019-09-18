package com.tedu.cgb.team.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysLog implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String operation;
	private String method;
	private String params;
	private Long time;
	private String ip;
	private Date createdTime;

}
