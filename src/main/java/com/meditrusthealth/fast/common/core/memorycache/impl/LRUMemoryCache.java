package com.meditrusthealth.fast.common.core.memorycache.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meditrusthealth.fast.common.core.memorycache.CacheKey;
import com.meditrusthealth.fast.common.core.memorycache.CacheValue;

/**
 * <p>
 * 采用 LRU 策略实现的内存缓存。
 * </p>
 *
 * @since 1.0.0
 */
public class LRUMemoryCache<V> extends UnsafeMapMemoryCache<V> implements LRUMemoryCacheMBean {

	private static final Logger LOG = LoggerFactory.getLogger(LRUMemoryCache.class);

	/**
	 * 内存缓存容器
	 */
	private final ReadWriteLock lock;

	private final int maxCount;

	public LRUMemoryCache(final int maxCount, final String name) {
		super(new LinkedHashMap<CacheKey, CacheValue<V>>() {
			private static final long serialVersionUID = 1L;

			protected boolean removeEldestEntry(Map.Entry<CacheKey, CacheValue<V>> eldest) {
				return size() > maxCount;
			}
		}, name);
		this.maxCount = maxCount;
		this.lock = new ReentrantReadWriteLock();
		if (LOG.isInfoEnabled()) {
			LOG.info("initialize LRU memory cache, max entries is {}", maxCount);
		}
	}

	public int getLRUMaxCount() {
		return maxCount;
	}

	@Override
	protected Object getSerializeCacheObject() {
		lock.readLock().lock();
		try {
			return super.getSerializeCacheObject();
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public int size() {
		lock.readLock().lock();
		try {
			return super.size();
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	protected CacheValue<V> putCacheValue(CacheKey key, CacheValue<V> value) {
		lock.writeLock().lock();
		try {
			return super.putCacheValue(key, value);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	protected CacheValue<V> getCacheValue(CacheKey key) {
		lock.readLock().lock();
		try {
			return super.getCacheValue(key);
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	protected CacheValue<V> removeCacheValue(CacheKey key) {
		lock.writeLock().lock();
		try {
			return super.removeCacheValue(key);
		} finally {
			lock.writeLock().unlock();
		}
	}
}
