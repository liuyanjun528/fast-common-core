package com.meditrusthealth.fast.common.core.memorycache.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meditrusthealth.fast.common.core.memorycache.CacheKey;
import com.meditrusthealth.fast.common.core.memorycache.CacheValue;

/**
 * <p>
 * 无限制容量内存缓存的实现。
 * </p>
 *
 * @since 1.0.0
 */
public class UnlimitedMemoryCache<V> extends UnsafeMapMemoryCache<V> implements UnlimitedMemoryCacheMBean {

	private static final Logger LOG = LoggerFactory.getLogger(UnlimitedMemoryCache.class);

	public UnlimitedMemoryCache(final String name) {
		super(new ConcurrentHashMap<CacheKey, CacheValue<V>>(), name);
		LOG.info("initialize unlimited memory cache");
	}
}
