package com.tedu.cgb.team.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tedu.cgb.team.sys.entity.SysLog;

@Mapper
public interface SysLogDAO {
	/** 查询当前页的数据 */
	List<SysLog> findRecordsPageByUsername(
			@Param("username") String username, 
			@Param("startIndex") Integer startIndex, 
			@Param("pageSize") Integer pageSize);
	
	/** 获得查询的总记录数 */
	int getRowCountByUsername(@Param("username") String username);
	
	/** 根据id删除记录 */
	int deleteRecordsByIds(@Param("ids") Integer... ids);
	
	/** 增加一条记录 */
	int insertRecord(SysLog log);
}
