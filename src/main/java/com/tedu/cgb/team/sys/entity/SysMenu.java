package com.tedu.cgb.team.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysMenu implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String url;
	private Integer type;
	private Integer sort;
	private String note;
	private Integer parentId;
	private String permission;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
