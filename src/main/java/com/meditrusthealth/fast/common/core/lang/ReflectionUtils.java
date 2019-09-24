package com.meditrusthealth.fast.common.core.lang;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ReflectionUtils {

	private static final Logger LOG = LoggerFactory.getLogger(ReflectionUtils.class);

	private ReflectionUtils() {
	}

	public static Class<?> loadClass(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new ReflectionOperationException("load class '" + className + "' error", e);
		}
	}

	public static <T> T newInstance(Class<T> clazz, Object... arguments) {
		if (clazz == null) {
			return null;
		}
		Class<?>[] parameterTypes = new Class<?>[arguments.length];
		for (int i = 0; i < arguments.length; i++) {
			parameterTypes[i] = arguments[i].getClass();
		}
		try {
			Constructor<T> constructor = clazz.getDeclaredConstructor(parameterTypes);
			if (!constructor.isAccessible()) {
				constructor.setAccessible(true);
			}
			return (T) constructor.newInstance(arguments);
		} catch (Exception e) {
			throw new ReflectionOperationException("constructor invoke error", clazz, parameterTypes, e);
		}
	}

	public static Object invokeMethod(Object target, Method method, Object... arguments) {
		if (target == null || method == null) {
			return null;
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("invokeMethod, target class = {}, method = {}, arguments = {}", target.getClass(), method,
					Arrays.toString(arguments));
		}

		try {
			if (!method.isAccessible()) {
				method.setAccessible(true);
			}
			return method.invoke(target, arguments);
		} catch (Exception e) {
			throw ReflectionOperationException.convertException(e, target, method);
		}
	}

	public static void setValue(Object target, Field field, Object value) {

		if (target == null || field == null) {
			return;
		}

		LOG.debug("setValue, target class = {}, field = {}, value = {}", target.getClass(), field, value);

		try {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			field.set(target, value);
		} catch (Exception e) {
			throw ReflectionOperationException.convertException(e, target, field);
		}
	}

	public static Object getValue(Object target, Field field) {

		if (target == null || field == null) {
			return null;
		}

		LOG.debug("getValue, target class = {}, field = {}", target.getClass(), field);

		try {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			return field.get(target);
		} catch (Exception e) {
			throw ReflectionOperationException.convertException(e, target, field);
		}
	}

	public static final class ReflectionOperationException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public ReflectionOperationException(String message, Member member, Object target, Throwable cause) {
			super(message + ", member: " + member + ", target: " + target, cause);
		}

		public ReflectionOperationException(String message, Class<?> clazz, Class<?>[] types, Throwable cause) {
			super(message + ", class: " + clazz + ", types: " + Arrays.toString(types), cause);
		}

		public ReflectionOperationException(String message) {
			super(message);
		}

		public ReflectionOperationException(String message, Throwable cause) {
			super(message, cause);
		}

		public static ReflectionOperationException convertException(Exception e, Object target, Member member) {
			if (e instanceof IllegalArgumentException) {
				return new ReflectionOperationException("invoke arguments error", member, target, e);
			}
			if (e instanceof IllegalAccessException) {
				return new ReflectionOperationException("invoke access error", member, target, e);
			}
			if (e instanceof InvocationTargetException) {
				return new ReflectionOperationException("invoke target error", member, target, e.getCause());
			}
			return new ReflectionOperationException("invoke error", member, target, e);
		}
	}
}
