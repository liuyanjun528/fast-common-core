package com.meditrusthealth.fast.common.core.memorycache.value;

/**
 * <p>
 * 永不过期的缓存值实现。
 * </p>
 *
 * @since 1.0.0
 */
public class LongLiveCacheValue<V> extends ExpiredCacheValue<V> {

	private static final long serialVersionUID = 1L;

	public LongLiveCacheValue(V value) {
		super(value, LONG_LIVE);
	}
}
