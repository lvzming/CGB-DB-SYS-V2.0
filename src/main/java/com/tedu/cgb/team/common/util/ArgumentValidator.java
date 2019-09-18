package com.tedu.cgb.team.common.util;

import java.util.Collection;

import org.springframework.util.StringUtils;

import com.tedu.cgb.team.common.exception.EmptyArrayArgumentException;
import com.tedu.cgb.team.common.exception.EmptyCollectionArgumentException;
import com.tedu.cgb.team.common.exception.BlankStringArgumentException;
import com.tedu.cgb.team.common.exception.IdArgumentException;
import com.tedu.cgb.team.common.exception.NullPointerArgumentExcpection;
import com.tedu.cgb.team.common.exception.NumberArgumentException;

/**
 * 验证Service层实参的合法性，验证失败时抛出相应的异常，
 * 通过{@link ThreadLocal}来达到线程安全。<br>
 * 用instance()方法创建实例。<br>
 * exceptionMessage: 验证失败抛出异常的信息
 * 所有验证方法返回此对象本身，可以使用连锁式调用来美化代码。
 * <li>notNull: 验证对象是否为空</li>
 * <li>notEmpty: 验证数组或集合是否没有元素</li>
 * <li>notBlank: 验证String是否为空串</li>
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
//	== Validators =======================================================
//	----------------------------------------------------------------------
	
	/**
	 * 验证对象是否不为null值，验证失败时抛出异常：<br>
	 * {@link NullPointerArgumentExcpection}
	 * @param object 验证对象
	 * @return
	 */
	public ArgumentValidator notNull(Object object) {
		if (object == null) 
			throw new NullPointerArgumentExcpection();
		return this;
	}
	
	/**
	 * 验证对象是否不为null值，验证失败时抛出异常：<br>
	 * {@link NullPointerArgumentExcpection}
	 * @param object 验证对象
	 * @param exceptionMessage 验证失败时抛出的异常信息
	 * @return
	 */
	public ArgumentValidator notNull(Object object, String exceptionMessage) {
		if (object == null) 
			throw new NullPointerArgumentExcpection(exceptionMessage);
		return this;
	}
	
	/**
	 * 验证对象是否不为null值和是否为空串，验证失败时抛出异常：<br>
	 * {@link BlankStringArgumentException}
	 * @param str 需要验证的字符串
	 * @param exceptionMessage 验证失败时抛出的异常信息
	 * @return
	 */
	public ArgumentValidator notBlank(String str, String exceptionMessage) {
		notNull(str, exceptionMessage);
		if (StringUtils.isEmpty(str)) 
			throw new BlankStringArgumentException(exceptionMessage);
		return this;
	}
	
	/**
	 * 验证数组是否不为null值和是否有元素，验证失败时抛出异常：<br>
	 * {@link EmptyArrayArgumentException}
	 * @param array 需要验证的数组
	 * @param exceptionMessage 验证失败时抛出的异常信息
	 * @return
	 */
	public ArgumentValidator notEmpty(Object[] array, String exceptionMessage) {
		notNull(array, exceptionMessage);
		if (array.length == 0) {
			throw new EmptyArrayArgumentException(exceptionMessage);
		}
		return this;
	}
	
	/**
	 * 验证数组是否不为null值和是否有元素，验证失败时抛出异常：<br>
	 * {@link EmptyCollectionArgumentException}
	 * @param collection 需要验证的集合
	 * @param exceptionMessage 验证失败时抛出的异常信息
	 * @return
	 */
	public <T> ArgumentValidator notEmpty(Collection<T> collection, String exceptionMessage) {
		notNull(collection);
		if (collection.isEmpty()) {
			throw new EmptyCollectionArgumentException(exceptionMessage);
		}
		return this;
	}
	
	/**
	 * 验证数字是否不为null值和是否小于等于0，验证失败时抛出异常：<br>
	 * {@link NumberArgumentException}
	 * @param number 需要验证的数字
	 * @param exceptionMessage 验证失败时抛出的异常信息
	 * @return
	 */
	public ArgumentValidator notZero(Number number, String exceptionMessage) {
		notNull(number);
		if (number.doubleValue() == 0) 
			throw new NumberArgumentException(exceptionMessage);
		return this;
	}
	
	/**
	 * 验证id是否不为null值或是否小于等于0，验证失败时抛出异常：<br>
	 * {@link IdArgumentException}
	 * @param id 需要验证的id
	 * @return
	 */
	public ArgumentValidator validateId(Integer id) {
		notNull(id);
		if (id <= 0)
			throw new IdArgumentException();
		return this;
	}
	
	/**
	 * 验证id是否为null值或是否小于等于0，验证失败时抛出异常：<br>
	 * {@link IdArgumentException}
	 * @param id 需要验证的id
	 * @param exceptionMessage 验证失败时抛出的异常信息
	 * @return
	 */
	public ArgumentValidator validateId(Integer id, String exceptionMessage) {
		notNull(id, exceptionMessage);
		if (id <= 0)
			throw new IdArgumentException(exceptionMessage);
		return this;
	}

}
