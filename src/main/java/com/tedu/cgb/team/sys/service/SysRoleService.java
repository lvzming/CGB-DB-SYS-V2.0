package com.tedu.cgb.team.sys.service;

import java.util.List;

import com.tedu.cgb.team.common.vo.CheckBox;
import com.tedu.cgb.team.common.vo.Page;
import com.tedu.cgb.team.common.vo.SysRoleMenuVo;
import com.tedu.cgb.team.sys.entity.SysRole;

public interface SysRoleService {
	List<CheckBox> findRoles();

	int deleteObject(Integer id);

	int saveObject(SysRole role, Integer... menuIds);

	Page<SysRole> findPageObjects(String name, Integer pageCurrent);

	SysRoleMenuVo findObjectById(Integer id);

	int updateObject(SysRole role, Integer... menuIds);

}
