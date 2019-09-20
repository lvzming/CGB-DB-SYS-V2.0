package com.tedu.cgb.team.common.vo;

import java.io.Serializable;
import java.util.Date;

import com.tedu.cgb.team.sys.entity.SysDept;
import com.tedu.cgb.team.sys.entity.SysUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SysUserDeptVo extends SysUser implements Serializable {
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
	private SysDept sysDept;
}
