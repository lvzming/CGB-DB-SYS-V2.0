package com.tedu.cgb.team.sys.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cgb.team.common.util.ArgumentValidator;
import com.tedu.cgb.team.common.util.ResultValidator;
import com.tedu.cgb.team.common.vo.Page;
import com.tedu.cgb.team.sys.dao.SysLogDAO;
import com.tedu.cgb.team.sys.entity.SysLog;
import com.tedu.cgb.team.sys.service.SysLogService;

@Transactional(isolation = Isolation.READ_COMMITTED)
@Service
public class SysLogServiceImpl implements SysLogService {
	
	@Autowired
	private SysLogDAO sysLogDAO;
	private static final int DEFAULT_PAGE_SIZE = 5;
	
	@Override
	public Page<SysLog> findPage(String username, Integer pageCurrent) {
		ArgumentValidator.instance()
		.notZero(pageCurrent, "页面参数不合法");
		
		int rowCount = sysLogDAO.getRowCountByUsername(username);
		ResultValidator.validateResult(rowCount, "没有找到相应的记录");
		
		int pageSize = DEFAULT_PAGE_SIZE;
		int startIndex = (pageCurrent-1) * pageSize;
		List<SysLog> records = sysLogDAO.findRecordsPageByUsername(username, startIndex, pageSize);
		
		ResultValidator.validateResult(records, "没有找到相应的记录");
		return new Page<>(pageCurrent, pageSize, rowCount, records);
	}
	
	@RequiresPermissions("sys:log:delete")
	@Override
	public int deleteByIds(Integer... ids) {
		ArgumentValidator.instance()
		.notEmpty(ids, "id参数不合法");
		
		int rows = sysLogDAO.deleteRecordsByIds(ids);
		
		ResultValidator.validateResult(rows, "删除失败，记录不存在");
		return rows;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Async
	public void insertRecord(SysLog log) {
		ArgumentValidator.instance().notNull(log);
		int rows = sysLogDAO.insertRecord(log);
		ResultValidator.validateResult(rows, "日志新增失败，请联系系统管理员排查错误");
	}

}
