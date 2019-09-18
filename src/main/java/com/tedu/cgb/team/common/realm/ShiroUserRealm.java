package com.tedu.cgb.team.common.realm;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tedu.cgb.team.sys.dao.SysMenuDAO;
import com.tedu.cgb.team.sys.dao.SysRoleMenuDAO;
import com.tedu.cgb.team.sys.dao.SysUserDAO;
import com.tedu.cgb.team.sys.dao.SysUserRoleDAO;
import com.tedu.cgb.team.sys.entity.SysUser;

@Service
public class ShiroUserRealm extends AuthorizingRealm {
	
	@Autowired
	private SysUserDAO sysUserDAO;
	@Autowired
	private SysUserRoleDAO sysUserRoleDAO;
	@Autowired
	private SysRoleMenuDAO sysRoleMenuDAO;
	@Autowired
	private SysMenuDAO sysMenuDAO;
	
	/**
	 * 设置凭证匹配器。
	 * 通过此对象指定加密算法。
	 */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		// 构建凭证匹配器对象
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		// 设置加密算法
		matcher.setHashAlgorithmName("MD5");
		// 设置加密次数
		matcher.setHashIterations(1);
		// 传递凭证匹配器对象
		super.setCredentialsMatcher(matcher);
	}
	
	/**
	 * 负责认证信息的获取及封装
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		
		SysUser user = sysUserDAO.findRecordByUsername(username);
		if (user == null)
			throw new UnknownAccountException();
		if (user.getValid() == 0)
			throw new LockedAccountException();
		
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo info = 
				new SimpleAuthenticationInfo(
						user, 
						user.getPassword(), 
						credentialsSalt, 
						getName());
		return info;
	}
	
	/**
	 * 负责授权信息的获取及封装
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		Integer userId = user.getId();
		List<Integer> roleIds = sysUserRoleDAO.findRoleIdsByUserId(userId);
		if (roleIds == null || roleIds.size() == 0)
			throw new AuthorizationException();
		List<Integer> menuIds = sysRoleMenuDAO.findMenuIdsByRoleIds(roleIds);
		if (menuIds == null || menuIds.size() == 0)
			throw new AuthorizationException();
		List<String> permissions = sysMenuDAO.findPermissionsByIds(menuIds);
		if (permissions == null || permissions.size() == 0)
			throw new AuthorizationException();
		
		Set<String> set = permissions.stream()
			.filter(p -> !(StringUtils.isEmpty(p)))
			.collect(Collectors.toSet());
		
//		for (String p : permissions) {
//			if (!StringUtils.isEmpty(p)) {
//				set.add(p);
//			}
//		}
		
		SimpleAuthorizationInfo info = 
				new SimpleAuthorizationInfo();
		info.setStringPermissions(set);
		return info;
	}
}
