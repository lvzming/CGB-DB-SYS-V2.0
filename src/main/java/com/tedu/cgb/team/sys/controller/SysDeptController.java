package com.tedu.cgb.team.sys.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.cgb.team.common.vo.JsonResult;
import com.tedu.cgb.team.common.vo.Node;
import com.tedu.cgb.team.sys.entity.SysDept;
import com.tedu.cgb.team.sys.service.SysDeptService;

@RestController
@RequestMapping("/dept/")
public class SysDeptController {
	
	@Autowired
	private SysDeptService service;
	
	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects() {
		List<Map<String, Object>> result = service.findObjects();
		return new JsonResult(result);
	}
	
	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
		service.deleteObject(id);
		return new JsonResult("删除成功");
	}
	
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysDept dept) {
		service.updateObject(dept);
		return new JsonResult("修改成功");
	}
	
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysDept dept) {
		service.saveObject(dept);
		return new JsonResult("保存成功");
	}
	
	@RequestMapping("doFindZTreeNodes")
	public JsonResult doFindZTreeNodes() {
		List<Node> result = service.findZTreeNodes();
		return new JsonResult(result);
	}
	
}
