package com.meditrusthealth.fast.common.core.resource.props.loader;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meditrusthealth.fast.common.core.convert.PropertyConverter;
import com.meditrusthealth.fast.common.core.lang.AnnotatedProperty;
import com.meditrusthealth.fast.common.core.lang.AnnotationScan;
import com.meditrusthealth.fast.common.core.lang.ReflectionUtils;
import com.meditrusthealth.fast.common.core.resource.PlaceholderResolver;
import com.meditrusthealth.fast.common.core.utils.Assert;
import com.meditrusthealth.fast.common.core.utils.Tools;

public final class PropsLoader<T> {

	private static final Logger LOG = LoggerFactory.getLogger(PropsLoader.class);

	private final Class<T> propsBeanClass;
	private final AnnotationScan<PropertyName> propertyAnnotations;

	private PlaceholderResolver placeholderResolver;
	private Properties properties;
	private Properties defaultProperties;

	private PropsLoader(Class<T> propsBeanClass) {
		Assert.notNull(propsBeanClass, "propsBeanClass");
		this.propsBeanClass = propsBeanClass;
		this.propertyAnnotations = AnnotationScan.scan(propsBeanClass, PropertyName.class);
	}

	public static <T> PropsLoader<T> createPropsLoader(Class<T> propsBeanClass) {
		return new PropsLoader<T>(propsBeanClass);
	}

	public Properties getDefaultProperties() {
		return defaultProperties;
	}

	public void setDefaultProperties(Properties defaultProperties) {
		this.defaultProperties = defaultProperties;
	}

	public Class<T> getPropsBeanClass() {
		return propsBeanClass;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Properties getProperties() {
		return properties;
	}

	public AnnotationScan<PropertyName> getPropertyAnnotations() {
		return propertyAnnotations;
	}

	public PlaceholderResolver getPlaceholderResolver() {
		if (placeholderResolver == null) {
			placeholderResolver = PropertiesPlaceholderResolver.defaultPropertiesPlaceholderResolver();
		}
		return placeholderResolver;
	}

	public void setPlaceholderResolver(PlaceholderResolver placeholderResolver) {
		this.placeholderResolver = placeholderResolver;
	}

	public T createPropsBean() {

		if (LOG.isDebugEnabled()) {
			LOG.debug("create properties bean, class: {}", getPropsBeanClass());
		}

		T bean = ReflectionUtils.newInstance(getPropsBeanClass());

		for (AnnotatedProperty<PropertyName> propertyAnnotation : getPropertyAnnotations()) {
			String value = getString(propertyAnnotation.getAnnotation());
			if (Tools.isBlank(value)) {
				continue;
			}
			LOG.debug("inject property '{}' configuration value '{}', configuration name '{}'",
					propertyAnnotation.getPropertyName(), value, propertyAnnotation.getAnnotation().name());
			invokeSetPropertyValue(bean, propertyAnnotation, value);
		}
		return bean;
	}

	private void invokeSetPropertyValue(T bean, AnnotatedProperty<PropertyName> propertyAnnotation, String text) {
		Class<?> propertyType = propertyAnnotation.getPropertyType();
		Object value = PropertyConverter.convert(text, propertyType);
		LOG.trace("invokeSet, propertyType = {} , value = {} ", propertyType, value);
		if (value instanceof String) {
			value = getPlaceholderResolver().resolve((String) value);
			LOG.debug("invokeSet, after properties holder value is '{}'", value);
		}
		propertyAnnotation.invokeSet(bean, value);
	}

	private String getString(PropertyName propertyName) {

		if (propertyName == null || Tools.isBlank(propertyName.name())) {
			return null;
		}

		String name = propertyName.name().trim();

		String value = getString(name, getProperties());

		if (Tools.isBlank(value)) {
			value = getString(name, getDefaultProperties());
		}

		if (Tools.isBlank(value)) {
			value = propertyName.defaultValue().trim();
		}

		return value;
	}

	private String getString(String name, Properties properties) {
		if (properties == null) {
			return null;
		}
		String value = properties.getProperty(name);
		if (value == null) {
			return null;
		}
		return value.trim();
	}
}
