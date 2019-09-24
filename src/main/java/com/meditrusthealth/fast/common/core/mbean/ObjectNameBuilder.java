package com.meditrusthealth.fast.common.core.mbean;

import javax.management.JMException;
import javax.management.ObjectName;

public final class ObjectNameBuilder {

	public static final String DEFAULT_JMX_DOMAIN = "com.meditrusthealth.fast";
	public static final String JMX_MODULE_UTILS = "util";

	private final String domain;

	private ObjectNameBuilder(String domain) {
		this.domain = domain;
	}

	public static ObjectNameBuilder create(final String domain) {
		if (domain == null) {
			return new ObjectNameBuilder(DEFAULT_JMX_DOMAIN);
		} else {
			return new ObjectNameBuilder(domain);
		}
	}

	public static ObjectNameBuilder createDefault() {
		return create(DEFAULT_JMX_DOMAIN);
	}

	public ObjectName createMemoryCacheObjectName(final String name) throws JMException {
		return createUtilsObjectName("MemoryCache", name);
	}

	public ObjectName createUtilsObjectName(final Class<?> clazz, final String name) throws JMException {
		return createObjectName(JMX_MODULE_UTILS, clazz, name);
	}

	public ObjectName createUtilsObjectName(final String module, final String name) throws JMException {
		return createObjectName(JMX_MODULE_UTILS, module, name);
	}

	public ObjectName createObjectName(final String type, final Class<?> clazz, final String name) throws JMException {
		return createObjectName(type, clazz.getSimpleName(), name);
	}

	public ObjectName createObjectName(final String type, final String module, final String name) throws JMException {
		return ObjectName.getInstance(
				String.format("%s:module=%s,type=%s,name=%s", domain, module, type, ObjectName.quote(name)));
	}
}
