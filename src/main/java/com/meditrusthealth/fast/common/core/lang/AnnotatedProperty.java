package com.meditrusthealth.fast.common.core.lang;

import java.beans.PropertyDescriptor;

public final class AnnotatedProperty<A> {

	private final A annotation;
	private final PropertyDescriptor propertyDescriptor;

	public AnnotatedProperty(A annotation, PropertyDescriptor propertyDescriptor) {
		this.annotation = annotation;
		this.propertyDescriptor = propertyDescriptor;
	}

	public String getPropertyName() {
		return propertyDescriptor.getName();
	}

	public A getAnnotation() {
		return annotation;
	}

	public Class<?> getPropertyType() {
		return propertyDescriptor.getPropertyType();
	}

	public Object invokeGet(Object target) {
		return ReflectionUtils.invokeMethod(target, propertyDescriptor.getReadMethod());
	}

	public void invokeSet(Object target, Object value) {
		ReflectionUtils.invokeMethod(target, propertyDescriptor.getWriteMethod(), value);
	}
}
