package com.tedu.cgb.team.sys.service;

import java.util.List;
import java.util.Map;

import com.tedu.cgb.team.common.vo.Node;
import com.tedu.cgb.team.sys.entity.SysMenu;

public interface SysMenuService {
	/**
	 * 查找sys_menus表所有记录，
	 * 单条记录数据以及父菜单id和name一起封装到map中，多条记录封装到List中返回
	 * @return 将多条的记录和父菜单信息封装到Map返回
	 */
	List<Map<String, Object>> findObjects();
	
	/**
	 * 根据id值删除sys_menus表里相对应的记录，
	 * 并删除sys_role_menus关系表数据，<br>
	 * 当被删除的菜单有子菜单时，不允许删除
	 * @param id 要删除的记录的id
	 * @return 成功操作的记录行数，小于1代表操作失败
	 */
	int deleteSysMenusById(Integer id);
	
	/**
	 * 根据id值在sys_menus表更新一条记录，id值不允许为空
	 * @param menu 将要更新的记录的数据封装成SysMenu传入
	 * @return 成功操作的记录行数，小于1代表操作失败
	 */
	int updateObject(SysMenu menu);
	
	/**
	 * 往sys_menus表插入一条新纪录
	 * @param menu 将新纪录的数据封装成SysMenu传入
	 * @return 成功操作的记录行数，小于1代表操作失败
	 */
	int saveObject(SysMenu menu);
	
	/**
	 * 获取每一条菜单记录的父菜单对应关系并将数据封装到Node，
	 * 多条记录存到List里
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
}
