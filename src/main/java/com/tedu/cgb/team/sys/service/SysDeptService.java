package com.tedu.cgb.team.sys.service;

import java.util.List;
import java.util.Map;

import com.tedu.cgb.team.common.vo.Node;
import com.tedu.cgb.team.sys.entity.SysDept;

public interface SysDeptService {
	
	/**
	 * 查询所有部门信息以及上级部门相关信息
	 * @return 将一个的部门与上级部门信息封装Map，再把多个部门信息封装到List返回
	 */
	List<Map<String, Object>> findObjects();
	
	/**
	 * 根据id删除sys_depts表里的一条记录，
	 * 当当前部门有子部门时不允许删除
	 * @param id 需要删除的记录的id值
	 * @return 成功操作的行数，少于1时删除失败
	 */
	int deleteObject(Integer id);
	
	/**
	 * 根据id值更新sys_depts表对应记录的数据
	 * @param dept 封装需要修改的记录的id和数据
	 * @return 成功操作的行数，少于1时删除失败
	 */
	int updateObject(SysDept dept);
	
	/**
	 * 往sys_depts表里插入一条新记录
	 * @param dept 封装新部门的数据
	 * @return 成功操作的行数，少于1时删除失败
	 */
	int saveObject(SysDept dept);
	
	/**
	 * 获取每一条部门记录的父部门对应关系并将数据封装到Node，
	 * 多条记录再封装到List
	 * @return 多条数据封装成List返回
	 */
	List<Node> findZTreeNodes();

}
