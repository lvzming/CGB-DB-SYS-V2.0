package com.tedu.cgb.team.sys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.cgb.team.common.util.ArgumentValidator;
import com.tedu.cgb.team.common.util.ResultValidator;
import com.tedu.cgb.team.common.vo.CheckBox;
import com.tedu.cgb.team.common.vo.Page;
import com.tedu.cgb.team.common.vo.SysRoleMenuVo;
import com.tedu.cgb.team.sys.dao.SysRoleDAO;
import com.tedu.cgb.team.sys.dao.SysRoleMenuDAO;
import com.tedu.cgb.team.sys.dao.SysUserRoleDAO;
import com.tedu.cgb.team.sys.entity.SysRole;
import com.tedu.cgb.team.sys.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDAO sysRoleDAO;
	@Autowired
	private SysRoleMenuDAO sysRoleMenuDAO;
	@Autowired
	private SysUserRoleDAO sysUserRoleDAO;
	private static final int DEFAULT_PAGE_SIZE = 5;

	@Override
	public int deleteObject(Integer id) {
		ArgumentValidator.instance().validateId(id);
		
		int rows = sysRoleDAO.deleteRecordById(id);
		ResultValidator.validateResult(rows, "数据可能已经不存在");
		
		sysRoleMenuDAO.deleteRecordsByRoleId(id);
		sysUserRoleDAO.deleteRecordsByRoleId(id);
		return rows;
	}

	@Override
	public int saveObject(SysRole role, Integer... menuIds) {
		ArgumentValidator.instance()
		.notNull(role)
		.notBlank(role.getName(), "名字不能为空")
		.notEmpty(menuIds, "必须给角色赋予权限");
		
		int rows = sysRoleDAO.insertRecord(role);
		ResultValidator.validateResult(rows, "新增失败，可能是id值重复");
		
		sysRoleMenuDAO.insertRecord(role.getId(), menuIds);
		return rows;
	}

	@Override
	public int updateObject(SysRole role, Integer... menuIds) {
		ArgumentValidator.instance()
		.notNull(role)
		.notBlank(role.getName(), "名字不能为空")
		.notEmpty(menuIds, "必须给角色赋予权限");

		
		int rows = sysRoleDAO.updateRecord(role);
		ResultValidator.validateResult(rows, "更新失败，可能是id值出错");
		
		sysRoleMenuDAO.deleteRecordsByRoleId(role.getId());
		sysRoleMenuDAO.insertRecord(role.getId(), menuIds);
		return rows;
	}

	@Override
	public Page<SysRole> findPageObjects(String name, Integer pageCurrent) {
		ArgumentValidator.instance()
		.notZero(pageCurrent, "当前页码不正确");
		
		List<SysRole> records;
		int pageSize = DEFAULT_PAGE_SIZE;
		int startIndex = (pageCurrent - 1) * pageSize;
		
		int rowCount = sysRoleDAO.getRowCountByName(name);
		ResultValidator.validateResult(rowCount, "没有相对应的记录");
		records = sysRoleDAO.findRecordsLimitedByName(name, startIndex, pageSize);
		ResultValidator.validateResult(records, "没有相对应的记录");
		return new Page<>(pageCurrent, pageSize, rowCount, records);
	}

	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		ArgumentValidator.instance().validateId(id);
//		SysRole role = sysRoleMapper.findRecordById(id);
//		List<Integer> menuIds = sysRoleMenuMapper.getMenuIdsByRoleId(role.getId());
//		return new SysRoleMenu(role.getId(), role.getName(), role.getNote(), menuIds);
		SysRoleMenuVo roleMenu = sysRoleDAO.findRecordByIdToSysRoleMenu(id);
		return roleMenu;
	}
	
	@Override
	public List<CheckBox> findRoles() {
		List<CheckBox> result = sysRoleDAO.findRecordCheckBox();
		return result;
	}

}
