package com.tedu.cgb.team.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.cgb.team.common.annotation.OperationLog;
import com.tedu.cgb.team.common.exception.ServiceException;
import com.tedu.cgb.team.common.util.ArgumentValidator;
import com.tedu.cgb.team.common.util.ResultValidator;
import com.tedu.cgb.team.common.util.ShiroUtils;
import com.tedu.cgb.team.common.vo.Page;
import com.tedu.cgb.team.common.vo.SysUserDeptVo;
import com.tedu.cgb.team.sys.dao.SysUserDAO;
import com.tedu.cgb.team.sys.dao.SysUserRoleDAO;
import com.tedu.cgb.team.sys.entity.SysUser;
import com.tedu.cgb.team.sys.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDAO sysUserDAO;
	@Autowired
	private SysUserRoleDAO sysUserRoleDAO;
	private static final Integer DEFAULT_PAGE_SIZE = 5;
	
	@Override
	public Page<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
		ArgumentValidator.instance()
		.notNullNotZero(pageCurrent, "当前页面不正确");
		
		int pageSize = DEFAULT_PAGE_SIZE;
		int startIndex = (pageCurrent-1) * pageSize;
		int rowCount = sysUserDAO.getRowCountByUsername(username);
		ResultValidator.validateResult(rowCount, "没有相对应的记录");
		List<SysUserDeptVo> records = sysUserDAO.findRecordsByUsernameLimited(username, startIndex, pageSize);
		ResultValidator.validateResult(records, "没有相对应的记录");
		return new Page<>(pageCurrent, pageSize, rowCount, records);
	}
	
	@OperationLog("enable/disable")
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		ArgumentValidator.instance().validateId(id);
		
		if (valid != 0 && valid != 1)
			throw new IllegalArgumentException("无效状态值"); 
		
		int rows = sysUserDAO.validById(id, valid, modifiedUser);
		ResultValidator.validateResult(rows, "没有相对应的记录");
		return rows;
	}
	
	@Override
	public int saveObject(SysUser user, Integer[] roleIds) {
		ArgumentValidator.instance()
		.notNull(user)
		.notNullNotBlank(user.getUsername(), "用户名不能为空")
		.notNullNotBlank(user.getPassword(), "密码不能为空")
		.notNullNotEmpty(roleIds, "必须指定至少一个角色");
		
		String source = user.getPassword();
		String salt = UUID.randomUUID().toString();
		SimpleHash sh = new SimpleHash("MD5", source, salt, 1); 
		user.setSalt(salt).setPassword(sh.toHex());
		
		int rows = sysUserDAO.insertRecord(user);
		ResultValidator.validateResult(rows, "保存失败，请刷新页面重试");
		sysUserRoleDAO.insertRecords(user.getId(), roleIds);
		return rows;
	}
	
	@Override
	public Map<String, Object> findObjectById(Integer id) {
		ArgumentValidator.instance().validateId(id);
		
		SysUserDeptVo user = sysUserDAO.findRecordByIdWithDeptToVo(id);
		ResultValidator.notNull(user, "该用户已不存在");
		
		List<Integer> roleIds = sysUserRoleDAO.findRoleIdsByUserId(id);
		HashMap<String, Object> map = new HashMap<>();
		map.put("roleIds", roleIds);
		map.put("user", user);
		return map;
	}
	
	@Override
	public int updateRecordWithRoleById(SysUser user, Integer[] roleIds) {
		ArgumentValidator.instance()
		.notNull(user)
		.notNullNotBlank(user.getUsername(), "用户名不能为空")
		.notNullNotEmpty(roleIds, "必须指定至少一个角色");
		
		int rows = sysUserDAO.updateRecord(user);
		ResultValidator.validateResult(rows, "该用户已不存在");
		sysUserRoleDAO.deleteRecordsByUserId(user.getId());
		sysUserRoleDAO.insertRecords(user.getId(), roleIds);
		return rows;
	}
	
	@Override
	public int updatePasswordById(String oldPassword, String newPassword, String confirmPassword) {
		// 验证参数
		ArgumentValidator.instance()
		.notNullNotBlank(oldPassword, "请输入原密码")
		.notNullNotBlank(newPassword, "请输入新密码");
		if (!newPassword.equals(confirmPassword)) 
			throw new IllegalArgumentException("两次密码输入不相同");
		
		Integer id = ShiroUtils.getCurrentUser().getId();
		ArgumentValidator.instance().validateId(id);
		
		// 根据id查找数据库并对新旧密码和数据库密码进行加密核对验证
		validatePasswords(id, oldPassword, newPassword);
		// 用新盐值和新密码进行加密，更新数据库与Subject用户对象
		int updatedRows = updateUserAndSubject(id, newPassword);
		return updatedRows;
	}

	
//	-------------------------------------------------------------------------------------
//	== 私 有 方 法 =======================================================================
//	-------------------------------------------------------------------------------------
	
	
	/**
	 * 根据id查找用户，加密后核对用户输入的旧密码、新密码，和数据库的密码：<br>
	 * 1、核对用户输入的旧密码与数据库密码是否相同，不同则抛异常<br>
	 * 2、核对用户输入的新密码与数据库密码是否相同，相同则抛异常
	 * @param id 修改密码的用户id
	 * @param oldPassword
	 * @param newPassword
	 */
	private void validatePasswords(Integer id, String oldPassword, String newPassword) {
		// 根据id查询数据库
		SysUser user = sysUserDAO.findRecordById(id);
		if (user == null) 
			throw new ServiceException("该用户已不存在");
		// 获取盐值
		String salt = user.getSalt();
		// 加密参数密码
		String oldPasswordHex = 
				new SimpleHash("MD5", oldPassword, salt, 1)
				.toHex();
		// 核对数据库与参数
		if (!oldPasswordHex.equals(user.getPassword()))
			throw new IncorrectCredentialsException();
		// 加密新密码并验证确保不重复
		String newPasswordHex = 
				new SimpleHash("MD5", newPassword, salt, 1)
				.toHex();
		if (newPasswordHex.equals(user.getPassword())) {
			throw new IllegalArgumentException("新密码不能与旧密码相同");
		}
	}
	
	/**
	 * 用新的盐值和新密码进行加密，并更新数据库，
	 * 获取Subject对象里的用户对象，更新此用户对象的盐值和密码属性值
	 * @param id 修改密码的用户id
	 * @param newPassword 新密码
	 * @return
	 */
	private int updateUserAndSubject(Integer id, String newPassword) {
		// 对新密码采用新盐值重新加密
		String newSalt = UUID.randomUUID().toString();
		String newPasswordNewSaltHex = 
				new SimpleHash("MD5", newPassword, newSalt, 1)
				.toHex();
		// 修改数据库
		int rows = sysUserDAO.updatePasswordSaltById(id, newPasswordNewSaltHex, newSalt);
		ResultValidator.validateResult(rows, "该用户已不存在");
		// 修改当前Subject里的用户信息
		ShiroUtils.getCurrentUser()
				  .setPassword(newPasswordNewSaltHex)
				  .setSalt(newSalt);
		return rows;
	}
	
}
