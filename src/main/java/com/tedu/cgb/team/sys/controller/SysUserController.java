package com.tedu.cgb.team.sys.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.cgb.team.common.util.ShiroUtils;
import com.tedu.cgb.team.common.vo.JsonResult;
import com.tedu.cgb.team.common.vo.Page;
import com.tedu.cgb.team.common.vo.SysUserDeptVo;
import com.tedu.cgb.team.sys.entity.SysUser;
import com.tedu.cgb.team.sys.service.SysUserService;

@RestController
@RequestMapping("/user/")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String username, Integer pageCurrent) {
		Page<SysUserDeptVo> page = sysUserService.findPageObjects(username, pageCurrent);
		return new JsonResult(page);
	}
	
	@RequestMapping("doValidById")
	public JsonResult doValidById(Integer id, Integer valid) {
		String username = ShiroUtils.getCurrentUsername();
		sysUserService.validById(id, valid, username);
		return new JsonResult("修改成功");
	}
	
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysUser user, Integer[] roleIds) {
		sysUserService.saveObject(user, roleIds);
		return new JsonResult("保存成功");
	}
	
	@RequestMapping("doFindObjectById")
	public JsonResult doFindObjectById(Integer id) {
		Map<String, Object> result = sysUserService.findObjectById(id);
		return new JsonResult(result);
	}
	
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateUserById(SysUser user, Integer[] roleIds) {
		sysUserService.updateRecordWithRoleById(user, roleIds);
		return new JsonResult("Updated succussfully.");
	}
	
	@RequestMapping("doLogin")
	public JsonResult doLogin(
			boolean isRememberMe, 
			String username, 
			String password) {
		// 把数据封装到token对象
		UsernamePasswordToken token = 
				new UsernamePasswordToken(username, password);
		token.setRememberMe(isRememberMe);
		
		// 获取subject对象
		Subject subject = SecurityUtils.getSubject();
		// 把token对象提交到SecurityManager对象
		subject.login(token);
		return new JsonResult("Sign in successfully.");
	}
	
	@RequestMapping("doUpdatePassword")
	public JsonResult doUpdatePassword(
			@RequestParam("pwd") String password, 
			@RequestParam("newPwd") String newPassword,
			@RequestParam("cfgPwd") String confirmPassword) {
		sysUserService.updatePasswordById(password, newPassword, confirmPassword);
		return new JsonResult("修改成功");
	}
}
