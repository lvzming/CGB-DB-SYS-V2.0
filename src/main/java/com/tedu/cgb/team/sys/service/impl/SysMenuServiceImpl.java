package com.tedu.cgb.team.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cgb.team.common.util.ArgumentValidator;
import com.tedu.cgb.team.common.util.ResultValidator;
import com.tedu.cgb.team.common.vo.Node;
import com.tedu.cgb.team.sys.dao.SysMenuDAO;
import com.tedu.cgb.team.sys.dao.SysRoleMenuDAO;
import com.tedu.cgb.team.sys.entity.SysMenu;
import com.tedu.cgb.team.sys.service.SysMenuService;

@Transactional(timeout = 30, 
			   rollbackFor = Throwable.class, 
			   noRollbackFor = IllegalAccessException.class,
			   isolation = Isolation.READ_COMMITTED,
			   propagation = Propagation.REQUIRED)
@Service
@Cacheable("menuCache")
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDAO sysMenuDAO;
	@Autowired
	private SysRoleMenuDAO sysRoleMenuDAO;
	
	@Transactional(readOnly = true)
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> results = sysMenuDAO.findAllRecords();
		
		ResultValidator.validateResult(results, "没有对应的菜单信息");
		return results;
	}

	@Override
	@CacheEvict(value = "menuCache", allEntries = true)
	public int deleteSysMenusById(Integer id) {
		ArgumentValidator.instance()
		.validateId(id, "至少要选择一个");
		
		int childRowCount = sysMenuDAO.getChildCountByParentId(id);
		ResultValidator.notHasChild(childRowCount, "此菜单有子菜单，请先删除子菜单");
		int rows = sysMenuDAO.deleteRecordById(id);
		ResultValidator.validateResult(rows, "删除失败，该菜单或已不存在");
		sysRoleMenuDAO.deleteRecordsByMenuId(id);
		return rows;
	}

	@CacheEvict(value = "menuCache", allEntries = true)
//	@CachePut(value = "menuCache", key = "#menu.id")
	@Override
	public int updateObject(SysMenu menu) {
		ArgumentValidator.instance()
		.notNull(menu)
		.notBlank(menu.getName(), "菜单名字不能为空");
		
		int rows = sysMenuDAO.updateRecordById(menu);
		
		ResultValidator.validateResult(rows, "更新失败，没有相对应的记录，请检查参数是否正确");
		return rows;
	}
	
	@CacheEvict(value = "menuCache", allEntries = true)
	@Override
	public int saveObject(SysMenu menu) {
		ArgumentValidator.instance()
		.notNull(menu)
		.notBlank(menu.getName(), "菜单名字不能为空");
		
		int rows = sysMenuDAO.insertRecord(menu);
		
		ResultValidator.validateResult(rows, "保存失败，可能是主键id冲突造成");
		return rows;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDAO.getZtreeNodes();
	}

}
