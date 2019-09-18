package com.tedu.cgb.team.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.cgb.team.common.vo.JsonResult;
import com.tedu.cgb.team.common.vo.Page;
import com.tedu.cgb.team.sys.entity.SysLog;
import com.tedu.cgb.team.sys.service.SysLogService;

@RequestMapping("/log/")
@RestController
public class SysLogController {
	
	@Autowired
	private SysLogService service;
	
	@RequestMapping("doFindPage")
	public JsonResult doFindPage(String username, Integer pageCurrent) {
		Page<SysLog> page = service.findPage(username, pageCurrent);
		return new JsonResult(page);
	}
	
	@RequestMapping("doDeleteByIds")
	public JsonResult doDeleteByIds(Integer... ids) {
		service.deleteByIds(ids);
		return new JsonResult("删除成功");
	}
}
