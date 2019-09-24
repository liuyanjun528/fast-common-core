package com.meditrusthealth.fast.common.core.memorycache.value;

import com.alibaba.fastjson.annotation.JSONField;
import com.meditrusthealth.fast.common.core.memorycache.CacheValue;

/**
 * <p>
 * 缓存对象值的抽象实现，定义了缓存数据写入与读取的规范。
 * </p>
 *
 * @since 1.0.0
 */
public abstract class AbstractCacheValue<V> implements CacheValue<V> {

	private static final long serialVersionUID = 1L;

	/**
	 * 被缓存的数据对象
	 */
	private final V value;

	public AbstractCacheValue(V value) {
		this.value = value;
	}

	@Override
	@JSONField(serialize = false)
	public final V getValue() {
		if (isReturnValue(value)) {
			return value;
		}
		return null;
	}

	public String getCachedValue() {
		return String.valueOf(value);
	}

	/**
	 * <p>
	 * 确定缓存值中被缓存的数据对象是否是可以读取的。
	 * </p>
	 *
	 * @param cachedValue
	 *            被缓存的数据对象
	 * @return 缓存值中被缓存的数据对象是否是可以读取的。如果返回 true，表示可以将被缓存的数据对象返回， 否则将 <code>null</code>
	 *         值作为被缓存的数据对象返回。
	 */
	protected abstract boolean isReturnValue(V cachedValue);
}
