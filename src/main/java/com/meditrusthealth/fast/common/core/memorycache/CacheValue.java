package com.meditrusthealth.fast.common.core.memorycache;

import java.io.Serializable;

/**
 * <p>作为内存缓存使用时，该接对象表示缓存的值对象。</p>
 *
 * @since 1.0.0
 */
public interface CacheValue<V> extends Serializable {

    /**
     * <p>获取缓存值中原始被缓存的对象数据。</p>
     *
     * @return 缓存中被缓存的对象数据。如果返回 <code>null</code>
     *      值时表示该缓存值所缓存的原始数据已经过期或者被清理。
     */
    V getValue();
}
