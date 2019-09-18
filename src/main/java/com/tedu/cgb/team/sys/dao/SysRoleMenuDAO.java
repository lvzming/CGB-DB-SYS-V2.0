package com.tedu.cgb.team.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDAO {
	
	int deleteRecordsByMenuId(@Param("menuId") Integer menuId);
	
	int deleteRecordsByRoleId(@Param("roleId") Integer roleId);
	
	Integer insertRecord(@Param("roleId") Integer roleId, Integer... menuIds);
	
	List<Integer> getMenuIdsByRoleId(Integer roleId);
	
	/**
	 * 根据多个角色id查找所有对应的菜单id
	 * @param roleIds
	 * @return
	 */
	List<Integer> findMenuIdsByRoleIds(@Param("roleIds") List<Integer> roleIds);

}
