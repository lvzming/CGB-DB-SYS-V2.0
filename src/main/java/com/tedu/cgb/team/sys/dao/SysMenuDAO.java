package com.tedu.cgb.team.sys.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tedu.cgb.team.common.vo.Node;
import com.tedu.cgb.team.sys.entity.SysMenu;

@Mapper
public interface SysMenuDAO {
	/**
	 * 查询所有菜单记录，并连同父菜单的id和name信息封装到Map，存入List返回
	 * @return 一条表记录是一个Map，存入List返回
	 */
	List<Map<String, Object>> findAllRecords();
	
	/**
	 * 获得sys_menus总记录数
	 * @return sys_menus总记录数
	 */
	int getRowCount();
	
	/**
	 * 根据id删除sys_menus表的记录
	 * @param id
	 * @return 成功删除的记录数，小于1说明操作失败
	 */
	int deleteRecordById(@Param("id") Integer id);
	
	/**
	 * 根据id更新sys_menus表的记录
	 * @param menu 将更新的数据封装到SysMenu对象传入
	 * @return 成功更新的记录数，小于1说明操作失败
	 */
	int updateRecordById(SysMenu menu);
	
	/**
	 * 往sys_menus表中插入一条新纪录
	 * @param menu 将新纪录的数据封装到SysMenu对象传入
	 * @return 成功插入的记录数，小于1说明操作失败
	 */
	int insertRecord(SysMenu menu);
	
	/**
	 * 在sys_menus表里，根据id获取对应的子菜单数量
	 * @param id 需要获取子菜单数的菜单id
	 * @return 对应id的子菜单数量
	 */
	int getChildCountByParentId(@Param("id") Integer id);
	
	/**
	 * 获取sys_menus表的父菜单对应数据，封装成Node返回
	 * @return 每一条记录的父菜单对应数据封装成Node，存入List返回
	 */
	List<Node> getZtreeNodes();
	
	/**
	 * 根据菜单id查找授权信息
	 * @param ids 菜单id
	 * @return
	 */
	List<String> findPermissionsByIds(@Param("ids") List<Integer> ids);
}
