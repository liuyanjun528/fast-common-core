package com.meditrusthealth.fast.common.core.lang;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import com.meditrusthealth.fast.common.core.memorycache.CacheKey;
import com.meditrusthealth.fast.common.core.memorycache.MemoryCache;
import com.meditrusthealth.fast.common.core.memorycache.impl.LRUMemoryCache;
import com.meditrusthealth.fast.common.core.memorycache.value.LongLiveCacheValue;

public final class BeanProperties {

	private static final MemoryCache<BeanProperties> CACHE = new LRUMemoryCache<BeanProperties>(100, "BeanProperties");

	private final Class<?> clazz;
	private final Map<String, BeanProperty> properties;

	private BeanProperties(Class<?> clazz) {
		this.clazz = clazz;
		this.properties = new HashMap<String, BeanProperty>();
		initBeanProperties();
	}

	public static BeanProperty getBeanProperty(Class<?> clazz, String propertyName) {
		CacheKey key = new BeanPropertiesCacheKey(clazz);
		BeanProperties beanProperties = CACHE.get(key);
		if (beanProperties == null) {
			beanProperties = new BeanProperties(clazz);
			CACHE.set(key, new LongLiveCacheValue<BeanProperties>(beanProperties));
		}
		return beanProperties.getBeanProperty(propertyName);
	}

	public BeanProperty getBeanProperty(String propertyName) {
		return properties.get(propertyName);
	}

	private void initBeanProperties() {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
			for (PropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
				addProperty(property);
			}
		} catch (IntrospectionException e) {
			throw new IllegalStateException("create bean properties error, class: " + clazz, e);
		}
	}

	private void addProperty(PropertyDescriptor property) {
		BeanProperty beanProperty = new BeanProperty(property);
		properties.put(beanProperty.getPropertyName(), beanProperty);
	}

	private static final class BeanPropertiesCacheKey implements CacheKey {
		private static final long serialVersionUID = 1L;
		private final Class<?> clazz;

		public BeanPropertiesCacheKey(Class<?> clazz) {
			this.clazz = clazz;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			BeanPropertiesCacheKey other = (BeanPropertiesCacheKey) obj;
			if (clazz == null) {
				if (other.clazz != null) {
					return false;
				}
			} else if (!clazz.equals(other.clazz)) {
				return false;
			}
			return true;
		}
	}
}
