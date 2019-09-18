package com.tedu.cgb.team.sys.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.cgb.team.common.vo.JsonResult;
import com.tedu.cgb.team.common.vo.Node;
import com.tedu.cgb.team.sys.entity.SysMenu;
import com.tedu.cgb.team.sys.service.SysMenuService;

@RestController
@RequestMapping("/menu/")
public class SysMenuController {
	
	@Autowired
	private SysMenuService service;
	
	@GetMapping("doFindObjects")
	public JsonResult doFindObjects() {
		List<Map<String, Object>> result = service.findObjects();
		return new JsonResult(result);
	}
	
	@PostMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysMenu menu) {
		service.updateObject(menu);
		return new JsonResult("修改成功");
	}
	
	@PostMapping("doDeleteObject")
	public JsonResult doDeleteSysMenusById(Integer id) {
		service.deleteSysMenusById(id);
		return new JsonResult("删除成功");
	}
	
	@PostMapping("doSaveObject")
	public JsonResult doSaveObject(SysMenu menu) {
		service.saveObject(menu);
		return new JsonResult("保存成功");
	}
	
	@GetMapping("doFindZtreeMenuNodes")
	public JsonResult doFindZtreeMenuNodes() {
		List<Node> result = service.findZtreeMenuNodes();
		return new JsonResult(result);
	}
}
