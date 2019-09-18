package com.tedu.cgb.team.sys.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.cgb.team.common.util.ArgumentValidator;
import com.tedu.cgb.team.common.util.ResultValidator;
import com.tedu.cgb.team.common.vo.Node;
import com.tedu.cgb.team.sys.dao.SysDeptDAO;
import com.tedu.cgb.team.sys.entity.SysDept;
import com.tedu.cgb.team.sys.service.SysDeptService;

@Service
public class SysDeptServiceImpl implements SysDeptService {
	
	@Autowired
	private SysDeptDAO sysDeptDAO;
	
	@Override
	public List<Map<String, Object>> findObjects() {
		return sysDeptDAO.findAllRecords();
	}
	
	@Override
	public int deleteObject(Integer id) {
		ArgumentValidator.instance().validateId(id, "请至少选择一个部门");
		
		int childRowCount = sysDeptDAO.getChildCountByParentId(id);
		ResultValidator.notHasChild(childRowCount, "此部门有子部门，请先删除子部门");
		int rows = sysDeptDAO.deleteRecordById(id);
		ResultValidator.validateResult(rows, "删除失败，该部门或已不存在");
		return rows;
	}

	@Override
	public int updateObject(SysDept dept) {
		ArgumentValidator.instance()
		.notNull(dept)
		.notBlank(dept.getName(), "名字不能为空");
		
		int rows = sysDeptDAO.updateRecordById(dept);
		ResultValidator.validateResult(rows, "更新失败，没有相对应的记录，请刷新页面重试");
		return rows;
	}

	@Override
	public int saveObject(SysDept dept) {
		ArgumentValidator.instance()
		.notNull(dept)
		.notBlank(dept.getName(), "名字不能为空");		
		
		int rows = sysDeptDAO.insertRecord(dept);
		ResultValidator.validateResult(rows, "保存失败，请刷新页面重试");
		return rows;
	}

	@Override
	public List<Node> findZTreeNodes() {
		return sysDeptDAO.getZtreeNode();
	}
}
