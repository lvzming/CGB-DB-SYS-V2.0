package com.tedu.cgb.team.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserRoleDAO {

	int deleteRecordsByRoleId(Integer roleId);
	
	int deleteRecordsByUserId(Integer userId);
	
	/**
	 * 插入一条新纪录
	 * @param userId
	 * @param roleId
	 * @return
	 */
	int insertRecords(Integer userId, Integer[] roleIds);
	
	/**
	 * 根据用户id查找对应角色id
	 * @param userId
	 * @return
	 */
	List<Integer> findRoleIdsByUserId(Integer userId);

}
