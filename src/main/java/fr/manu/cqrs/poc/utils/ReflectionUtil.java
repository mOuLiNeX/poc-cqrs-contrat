package fr.manu.cqrs.poc.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.manu.cqrs.poc.domain.Event;

public class ReflectionUtil {
	public static final String HANDLE_METHOD = "handle";

	@SuppressWarnings("unchecked")
	public static <R> R invokeApplyMethod(Object target, Event evt) {
		try {
			Method method = target.getClass().getMethod("apply", evt.getClass());
			return (R) method.invoke(target, evt);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getTargetException());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
