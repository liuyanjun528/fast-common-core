package com.meditrusthealth.fast.common.core.memorycache.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meditrusthealth.fast.common.core.mbean.MBeanManager;
import com.meditrusthealth.fast.common.core.memorycache.CacheKey;
import com.meditrusthealth.fast.common.core.memorycache.CacheValue;
import com.meditrusthealth.fast.common.core.memorycache.MemoryCache;
import com.meditrusthealth.fast.common.core.memorycache.MemoryCacheMBeanSupport;

/**
 * <p>
 * 内存缓存的抽象实现，定义了内存缓存操作的规范。
 * </p>
 *
 * @since 1.0.0
 */
public abstract class AbstractMemoryCache<V> extends MemoryCacheMBeanSupport implements MemoryCache<V> {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractMemoryCache.class);

	private final String name;

	public AbstractMemoryCache(String name) {
		this.name = name;
		LOG.info("initialize MemoryCache, name is '{}', type is '{}'", name, getClass().getName());
		MBeanManager.registerMemoryCacheMBean(name, this);
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final void set(CacheKey key, CacheValue<V> value) {
		putCacheValue(key, value);
		incrementAddedCount();
	}

	@Override
	public final V get(CacheKey key) {

		incrementGetCount();
		CacheValue<V> cacheValue = getCacheValue(key);

		if (cacheValue == null) {
			incrementUnhitCount();
			return null;
		}

		V value = cacheValue.getValue();

		if (value == null) {
			removeCacheValue(key);
			incrementRemovedCount();
		}

		incrementHitCount();
		return value;
	}

	/**
	 * <p>
	 * 将缓存数据放入内存缓存中。
	 * </p>
	 *
	 * @param key
	 *            缓存的键
	 * @param value
	 *            缓存的数据对象
	 * @return 缓存中指定缓存键所对应旧的缓存值对象。
	 */
	protected abstract CacheValue<V> putCacheValue(CacheKey key, CacheValue<V> value);

	/**
	 * <p>
	 * 从缓存中获取缓存值对象。
	 * </p>
	 *
	 * @param key
	 *            缓存键对象
	 * @return 缓存值对象。若返回 <code>null</code> 值时，表示缓存中不存在指定缓存键的值。
	 */
	protected abstract CacheValue<V> getCacheValue(CacheKey key);

	/**
	 * <p>
	 * 移除缓存中的缓存数据。用于缓存值中被缓存的数据过期或者不可用时清理缓存使用。
	 * </p>
	 *
	 * @param key
	 *            缓存键对象
	 * @return 缓存键所对应移除前的缓存值对象。
	 */
	protected abstract CacheValue<V> removeCacheValue(CacheKey key);
}
