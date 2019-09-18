package com.tedu.cgb.team.common.vo;

import java.io.Serializable;

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
	private SysDept sysDept;
}
