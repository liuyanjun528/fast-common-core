package com.meditrusthealth.fast.common.core.memorycache.value;

import java.util.concurrent.atomic.AtomicInteger;

import com.meditrusthealth.fast.common.core.ide.EclipseTools.ToString;

/**
 * <p>
 * 拥有缓存命中计数的缓存值类实现。当缓存的命中次数超过设定值时，被缓存的数据过期。
 * </p>
 *
 * @since 1.0.0
 */
public class HitCountCacheValue<V> extends AbstractCacheValue<V> {

	private static final long serialVersionUID = 1L;

	/**
	 * 缓存命中计数器
	 */
	private final AtomicInteger hitCount;

	/**
	 * 命中最大次数
	 */
	private final int maxHit;

	public HitCountCacheValue(V value, int maxHit) {
		super(value);
		if (maxHit < 1) {
			throw new IllegalArgumentException("maxHit [" + maxHit + "]" + " the value must be greater than 0");
		}
		this.maxHit = maxHit;
		this.hitCount = new AtomicInteger(0);
	}

	@Override
	protected boolean isReturnValue(V cachedValue) {
		return (maxHit > hitCount.getAndIncrement());
	}

	@Override
	public String toString() {
		ToString builder = new ToString(this);
		builder.append("hitCount", hitCount.get());
		builder.append("maxHit", maxHit);
		builder.append("cachedValue", getCachedValue());
		return builder.toString();
	}
}
