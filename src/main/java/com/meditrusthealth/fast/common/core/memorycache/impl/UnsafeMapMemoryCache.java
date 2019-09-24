package com.meditrusthealth.fast.common.core.memorycache.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meditrusthealth.fast.common.core.memorycache.CacheKey;
import com.meditrusthealth.fast.common.core.memorycache.CacheValue;

/**
 * <p>
 * 基于 {@link Map} 实现的内存缓存抽象实现。该实现中的操作没有任何线程同步措施，线程安全需要由子类进行处理。
 * </p>
 *
 * @since 1.0.0
 */
public abstract class UnsafeMapMemoryCache<V> extends AbstractMemoryCache<V> {

	private static final Logger LOG = LoggerFactory.getLogger(UnsafeMapMemoryCache.class);

	/**
	 * 内存缓存容器
	 */
	private final Map<CacheKey, CacheValue<V>> container;

	public UnsafeMapMemoryCache(Map<CacheKey, CacheValue<V>> container, String name) {
		super(name);
		this.container = container;
	}

	@Override
	protected Object getSerializeCacheObject() {
		return container;
	}

	@Override
	public int size() {
		return container.size();
	}

	@Override
	protected CacheValue<V> putCacheValue(CacheKey key, CacheValue<V> value) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("[putCacheValue] before container entries: {}, key = {}, value = {}", container.size(), key,
					value);
		}

		return container.put(key, value);
	}

	@Override
	protected CacheValue<V> getCacheValue(CacheKey key) {

		CacheValue<V> value = container.get(key);

		if (LOG.isDebugEnabled()) {
			LOG.debug("[getCacheValue] key = {}, value = {}", key, value);
		}

		return value;
	}

	@Override
	protected CacheValue<V> removeCacheValue(CacheKey key) {

		CacheValue<V> value = container.remove(key);

		if (LOG.isDebugEnabled()) {
			LOG.debug("[removeCacheValue] after container entries: {}, key = {}, value = {}", container.size(), key,
					value);
		}

		return value;
	}
}
