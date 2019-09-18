package com.tedu.cgb.team.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.cgb.team.common.vo.CheckBox;
import com.tedu.cgb.team.common.vo.JsonResult;
import com.tedu.cgb.team.common.vo.Page;
import com.tedu.cgb.team.common.vo.SysRoleMenuVo;
import com.tedu.cgb.team.sys.entity.SysRole;
import com.tedu.cgb.team.sys.service.SysRoleService;

@RestController
@RequestMapping("/role/")
public class SysRoleController {
	
	@Autowired
	private SysRoleService service;
	
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String name, Integer pageCurrent) {
		Page<SysRole> page = service.findPageObjects(name, pageCurrent);
		return new JsonResult(page);
	}
	
	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
		service.deleteObject(id);
		return new JsonResult("删除成功");
	}
	
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysRole role, Integer... menuIds) {
		service.saveObject(role, menuIds);
		return new JsonResult("保存成功");
	}
	
	@RequestMapping("doFindObjectById")
	public JsonResult doFindObjectById(Integer id) {
		SysRoleMenuVo roleMenu = service.findObjectById(id);
		return new JsonResult(roleMenu);
	}
	
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysRole role, Integer... menuIds) {
		service.updateObject(role, menuIds);
		return new JsonResult("修改成功");
	}
	
	@RequestMapping("doFindRoles")
	public JsonResult doFindRoles() {
		List<CheckBox> result = service.findRoles();
		return new JsonResult(result);
	}
	
}
