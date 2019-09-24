package com.meditrusthealth.fast.common.core.lang;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class AnnotationScan<T extends Annotation> implements Iterable<AnnotatedProperty<T>> {

	private List<AnnotatedProperty<T>> list;

	private AnnotationScan() {
		this.list = new LinkedList<AnnotatedProperty<T>>();
	}

	public static <T extends Annotation> AnnotationScan<T> scan(Class<?> clazz, Class<T> annotationClass) {
		AnnotationScan<T> scan = new AnnotationScan<T>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
			for (PropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
				T annotation = getAnnotation(property.getReadMethod(), annotationClass);
				if (annotation == null) {
					annotation = getAnnotation(property.getWriteMethod(), annotationClass);
				}
				if (annotation == null) {
					continue;
				}
				scan.addAnnotatedElement(annotation, property);
			}
			return scan;
		} catch (IntrospectionException e) {
			throw new IllegalStateException(
					"analysis annotation error, class: " + clazz + ", annotation class: " + annotationClass, e);
		}
	}

	private void addAnnotatedElement(T annotation, PropertyDescriptor property) {
		list.add(new AnnotatedProperty<T>(annotation, property));
	}

	private static <T extends Annotation> T getAnnotation(AnnotatedElement element, Class<T> annotationClass) {
		if (element == null || annotationClass == null) {
			return null;
		}
		return element.getAnnotation(annotationClass);
	}

	@Override
	public Iterator<AnnotatedProperty<T>> iterator() {
		return list.iterator();
	}
}
