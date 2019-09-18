package com.tedu.cgb.team.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tedu.cgb.team.common.vo.CheckBox;
import com.tedu.cgb.team.common.vo.SysRoleMenuVo;
import com.tedu.cgb.team.sys.entity.SysRole;

@Mapper
public interface SysRoleDAO {
	List<CheckBox> findRecordCheckBox();
	
	int deleteRecordById(@Param("id") Integer id);

	int insertRecord(SysRole role);

	int updateRecord(SysRole role);

	int getRowCountByName(@Param("name") String name);

	List<SysRole> findRecordsLimitedByName(
			@Param("name") String name,
			@Param("startIndex") Integer startIndex, 
			@Param("pageSize") Integer pageSize);

	SysRole findRecordById(@Param("id") Integer id);

	SysRoleMenuVo findRecordByIdToSysRoleMenu(Integer id);
}
