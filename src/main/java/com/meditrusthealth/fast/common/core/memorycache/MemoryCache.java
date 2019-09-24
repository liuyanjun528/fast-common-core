package com.meditrusthealth.fast.common.core.memorycache;

/**
 * <p>对象内存缓存接口。该接口的实现类应是线程安全的。</p>
 *
 * @since 1.0.0
 */
public interface MemoryCache<V> {

    /**
     * <p>获取内存缓存的名称，该名称用于标识一个内存缓存。</p>
     *
     * @return 内存缓存的名称
     */
    String getName();

    /**
     * <p>将数据放入内存缓存中。</p>
     *
     * @param key 缓存键对象
     * @param value 缓存值对象
     */
    void set(CacheKey key, CacheValue<V> value);

    /**
     * <p>通过缓存键获取缓存中存放的缓存对象。</p>
     *
     * @param key 缓存键对象
     * @return 被缓存的数据对象。若返回 <code>null</code> 值时表示该缓存键在缓存中不存在，
     *      或者缓存中的数据已经过期或者被清理。
     */
    V get(CacheKey key);
}
