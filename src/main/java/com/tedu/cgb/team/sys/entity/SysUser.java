package com.tedu.cgb.team.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SysUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;
	private String salt;
	private String email;
	private String mobile;
	private Integer valid = 1;
	private Integer deptId;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;

}
