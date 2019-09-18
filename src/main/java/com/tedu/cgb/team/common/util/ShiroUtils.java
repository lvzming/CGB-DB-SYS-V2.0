package com.tedu.cgb.team.common.util;

import org.apache.shiro.SecurityUtils;

import com.tedu.cgb.team.sys.entity.SysUser;

public class ShiroUtils {
	
	public static String getCurrentUsername() {
		String username = ((SysUser) SecurityUtils.getSubject()
				.getPrincipal())
				.getUsername();
		return username;
	}
	
	public static SysUser getCurrentUser() {
		return (SysUser) SecurityUtils.getSubject().getPrincipal();
	}
}
