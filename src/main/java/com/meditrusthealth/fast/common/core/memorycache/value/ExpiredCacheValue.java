package com.meditrusthealth.fast.common.core.memorycache.value;

import com.meditrusthealth.fast.common.core.ide.EclipseTools.ToString;

/**
 * <p>
 * 拥有缓存值生存时间的缓存值类实现。
 * </p>
 *
 * @since 1.0.0
 */
public class ExpiredCacheValue<V> extends AbstractCacheValue<V> {

	private static final long serialVersionUID = 1L;

	/**
	 * 被缓存数据不过期
	 */
	public static final int LONG_LIVE = -1;

	/**
	 * 被缓存数据的过期时间
	 */
	private final long expiredTime;

	/**
	 * <p>
	 * 构建一个带有生存时效的缓存值对象。
	 * </p>
	 *
	 * @param value
	 *            被缓存的数据
	 * @param livedSeconds
	 *            缓存数据的生存时间（秒）。若值小于 1 时，表示该缓存值永不过期。
	 */
	public ExpiredCacheValue(V value, int livedSeconds) {
		super(value);
		if (livedSeconds < 1) {
			this.expiredTime = LONG_LIVE;
		} else {
			this.expiredTime = System.currentTimeMillis() + 1000L * livedSeconds;
		}
	}

	public long getExpiredTime() {
		return expiredTime;
	}

	@Override
	protected boolean isReturnValue(V cachedValue) {
		if (getExpiredTime() == LONG_LIVE) {
			return true;
		}
		return (getExpiredTime() > System.currentTimeMillis());
	}

	@Override
	public String toString() {
		ToString builder = new ToString(this);
		builder.append("expiredTime", getExpiredTime());
		builder.append("cachedValue", getCachedValue());
		return builder.toString();
	}
}
