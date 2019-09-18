package com.tedu.cgb.team.common.util;

import java.util.Collection;

import com.tedu.cgb.team.common.exception.AssociationException;
import com.tedu.cgb.team.common.exception.EmptyCollectionResultException;
import com.tedu.cgb.team.common.exception.NoResultException;

public class ResultValidator {
	private ResultValidator() {}
	
	public static boolean notNull(Object object) {
		if (object == null)
			throw new NoResultException();
		return true;
	}
	
	public static boolean notNull(Object object, String exceptionMessage) {
		if (object == null)
			throw new NoResultException(exceptionMessage);
		return true;
	}
	
	public static boolean notEmpty(Collection<?> result, String exceptionMessage) {
		if (result.isEmpty())
			throw new EmptyCollectionResultException(exceptionMessage);
		return true;
	}
	
	public static boolean notNullNotEmpty(Collection<?> result, String exceptionMessage) {
		notNull(result, exceptionMessage);
		notEmpty(result, exceptionMessage);
		return true;
	}
	
	public static boolean validateResult(Collection<?> result, String exceptionMessage) {
		notNullNotEmpty(result, exceptionMessage);
		return true;
	}
	
	public static boolean validateResult(Integer rows, String exceptionMessage) {
		notNull(rows, exceptionMessage);
		if (rows <= 0)
			throw new NoResultException(exceptionMessage);
		return true;
	}
	
	public static boolean notHasChild(Integer childRowCount, String exceptionMessage) {
		if (childRowCount > 0)
			throw new AssociationException(exceptionMessage);
		return true;
	}
	
	
}
