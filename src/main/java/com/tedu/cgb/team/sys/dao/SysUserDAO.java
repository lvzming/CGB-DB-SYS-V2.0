package com.tedu.cgb.team.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tedu.cgb.team.common.vo.SysUserDeptVo;
import com.tedu.cgb.team.sys.entity.SysUser;

@Mapper
public interface SysUserDAO {
	/**
	 * 根据id进行对用户的启用/禁用进行修改
	 * @param id
	 * @param valid
	 * @param modifiedUser
	 * @return
	 */
	int validById(Integer id, Integer valid, String modifiedUser);
	
	/**
	 * 根据username查询指定范围内的记录并排序
	 * @param username
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysUserDeptVo> findRecordsByUsernameLimited(
			@Param("username") String username, 
			@Param("startIndex") Integer startIndex, 
			@Param("pageSize") Integer pageSize);
	
	/**
	 * 根据username获取对应的记录数
	 * @param username
	 * @return
	 */
	Integer getRowCountByUsername(@Param("username") String username);
	
	/**
	 * 插入一条新纪录，并返回新增后的主键id值
	 * @param user
	 * @return
	 */
	int insertRecord(SysUser user);
	
	SysUserDeptVo findRecordByIdWithDeptToVo(Integer id);
	
	int updateRecord(SysUser user);
	
	SysUser findRecordByUsername(String username);

	SysUser findRecordById(Integer id);

	Integer updatePasswordSaltById(
			@Param("id") Integer id, 
			@Param("password") String newPasswordNewSaltHexed, 
			@Param("salt") String newSalt);
}
