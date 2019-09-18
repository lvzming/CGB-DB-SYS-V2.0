package com.tedu.cgb.team.sys.service;

import org.apache.ibatis.annotations.Param;

import com.tedu.cgb.team.common.vo.Page;
import com.tedu.cgb.team.sys.entity.SysLog;

public interface SysLogService {
	
	Page<SysLog> findPage(String username, Integer pageCurrent);
	int deleteByIds(Integer... ids);
	void insertRecord(@Param("log") SysLog log);
}
