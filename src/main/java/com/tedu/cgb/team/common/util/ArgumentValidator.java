package com.tedu.cgb.team.common.util;

import java.util.Collection;

import org.springframework.util.StringUtils;

import com.tedu.cgb.team.common.exception.EmptyArrayArgumentException;
import com.tedu.cgb.team.common.exception.EmptyCollectionResultException;
import com.tedu.cgb.team.common.exception.BlankStringArgumentException;
import com.tedu.cgb.team.common.exception.IdArgumentException;
import com.tedu.cgb.team.common.exception.NullPointerArgumentExcpection;
import com.tedu.cgb.team.common.exception.NumberArgumentException;

/**
 * 验证Service层实参的合法性，通过{@link ThreadLocal}来达到线程安全。<br>
 * 所有验证方法返回此对象本身，可以使用连锁式调用来美化代码。
 * <li>notNull: 验证对象是否为空</li>
 * <li>notEmpty: 验证String、数组或集合是否没有元素</li>
 * <li>notZero: 验证数据类型是否小于等于0</li>
 * @version 1.0.20190918
 * @author David Zhao
 *
 */
public class ArgumentValidator {
	private static final ThreadLocal<ArgumentValidator> THREAD_LOCAL = new ThreadLocal<>();
	private ArgumentValidator() {}
	
	/**
	 * 获取当前线程的实例，假如还没有的情况创建一个返回
	 * @return
	 */
	public static ArgumentValidator instance() {
		ArgumentValidator validator = THREAD_LOCAL.get();
		if (validator == null) {
			validator = new ArgumentValidator();
			THREAD_LOCAL.set(validator);
		}
		return validator;
	}
	
	
//	----------------------------------------------------------------------
//	== S I N G L E =======================================================
//	----------------------------------------------------------------------
	
	/**
	 * 验证对象是否为null值，否则抛出异常：<br>
	 * {@link NullPointerArgumentExcpection}
	 * @param object
	 * @return
	 */
	public ArgumentValidator notNull(Object object) {
		if (object == null) 
			throw new NullPointerArgumentExcpection();
		return this;
	}
	
	public ArgumentValidator notNull(Object object, String exceptionMessage) {
		if (object == null) 
			throw new NullPointerArgumentExcpection(exceptionMessage);
		return this;
	}
	
	public ArgumentValidator notBlank(String str, String exceptionMessage) {
		if (StringUtils.isEmpty(str)) 
			throw new BlankStringArgumentException(exceptionMessage);
		return this;
	}
	
	public ArgumentValidator notEmpty(Object[] array, String exceptionMessage) {
		if (array.length == 0) {
			throw new EmptyArrayArgumentException(exceptionMessage);
		}
		return this;
	}
	
	public <T> ArgumentValidator notEmpty(Collection<T> result, String exceptionMessage) {
		if (result.isEmpty()) {
			throw new EmptyCollectionResultException(exceptionMessage);
		}
		return this;
	}
	
	public ArgumentValidator notZero(Number number, String exceptionMessage) {
		if (number.doubleValue() == 0) 
			throw new NumberArgumentException(exceptionMessage);
		return this;
	}
	
	
//	----------------------------------------------------------------------
//	== C O M P O S E =====================================================
//	----------------------------------------------------------------------
	
	
	public ArgumentValidator notNullNotEmpty(Object[] array, String exceptionMessage) {
		notNull(array, exceptionMessage);
		notEmpty(array, exceptionMessage);
		return this;
	}
	
	public ArgumentValidator notNullNotBlank(String str, String exceptionMessage) {
		notNull(str, exceptionMessage);
		notBlank(str, exceptionMessage);
		return this;
	}
	
	public ArgumentValidator notNullNotZero(Number number, String exceptionMessage) {
		notNull(number, exceptionMessage);
		notZero(number, exceptionMessage);
		return this;
	}
	
	public ArgumentValidator validateId(Integer id) {
		notNull(id);
		if (id <= 0)
			throw new IdArgumentException();
		return this;
	}
	
	public ArgumentValidator validateId(Integer id, String exceptionMessage) {
		notNull(id, exceptionMessage);
		if (id <= 0)
			throw new IdArgumentException(exceptionMessage);
		return this;
	}

}
