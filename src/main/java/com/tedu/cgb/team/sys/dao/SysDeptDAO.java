package com.tedu.cgb.team.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tedu.cgb.team.common.vo.Node;
import com.tedu.cgb.team.sys.entity.SysDept;

@Mapper
public interface SysDeptDAO {

	List<Map<String, Object>> findAllRecords();

	int getChildCountByParentId(@Param("id") Integer id);

	int deleteRecordById(@Param("id") Integer id);

	int updateRecordById(SysDept dept);

	int insertRecord(SysDept dept);

	List<Node> getZtreeNode();
	
	
}
