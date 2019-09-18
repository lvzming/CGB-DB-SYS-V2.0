package com.tedu.cgb.team.common.util;

import java.util.Collection;

import com.tedu.cgb.team.common.exception.AssociationException;
import com.tedu.cgb.team.common.exception.NoResultException;

/**
 * 专门验证查询结果，含有的方法全部为静态方法。
 * @version 1.0.20190918
 * @author David Zhao
 *
 */
public class ResultValidator {
	private ResultValidator() {}
	
	/**
	 * 验证结果对象是否不为null值，验证失败时抛出异常：<br>
	 * {@link NoResultException}
	 * @param object 需要验证的对象
	 * @return
	 */
	public static boolean notNull(Object object, String exceptionMessage) {
		if (object == null)
			throw new NoResultException(exceptionMessage);
		return true;
	}
	
	/**
	 * 验证结果集合是否不为null值或是否有元素，验证失败时抛出异常：<br>
	 * {@link NoResultException}
	 * @param collection 需要验证的结果集合
	 * @return
	 */
	public static boolean notEmpty(Collection<?> collection, String exceptionMessage) {
		notNull(collection, exceptionMessage);
		if (collection.isEmpty())
			throw new NoResultException(exceptionMessage);
		return true;
	}
	
	/**
	 * 验证结果集合是否不为null值或是否有元素，验证失败时抛出异常：<br>
	 * {@link NoResultException}
	 * @param collection 需要验证的结果集合 
	 * @return
	 */
	public static boolean validateResult(Collection<?> collection, String exceptionMessage) {
		notEmpty(collection, exceptionMessage);
		return true;
	}
	
	/**
	 * 验证结果是否不为null值或是否有有成功操作的行数，验证失败时抛出异常：<br>
	 * {@link NoResultException}
	 * @param rows 成功操作的行数
	 * @return
	 */
	public static boolean validateResult(Integer rows, String exceptionMessage) {
		notNull(rows, exceptionMessage);
		if (rows <= 0)
			throw new NoResultException(exceptionMessage);
		return true;
	}
	
	/**
	 * 验证结果是否不为null值或是否有查找到子元素，验证失败时抛出异常：<br>
	 * {@link AssociationException}
	 * {@link NoResultException}
	 * @param childRowCount 查找到的子元素数量 
	 * @return
	 */
	public static boolean notHasChild(Integer childRowCount, String exceptionMessage) {
		if (childRowCount > 0)
			throw new AssociationException(exceptionMessage);
		return true;
	}
	
	
}
