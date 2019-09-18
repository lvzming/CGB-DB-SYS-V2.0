package com.tedu.cgb.team.sys.service;

import java.util.Map;

import com.tedu.cgb.team.common.vo.Page;
import com.tedu.cgb.team.common.vo.SysUserDeptVo;
import com.tedu.cgb.team.sys.entity.SysUser;

public interface SysUserService {
	Page<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent);
	
	int validById(Integer id, Integer valid, String modifiedUser);
	
	int saveObject(SysUser user, Integer[] roleIds);
	
	Map<String, Object> findObjectById(Integer id);
	
	int updateRecordWithRoleById(SysUser user, Integer[] roleIds);
	
	/**
	 * 根据id更新对应的加密密码
	 * @param oldPassword 用户输入的原密码
	 * @param newPassword 用户输入的新密码
	 * @param confirmPassword 用户输入的新密码确认
	 * @return
	 */
	int updatePasswordById(String oldPassword, String newPassword, String confirmPassword);
}
