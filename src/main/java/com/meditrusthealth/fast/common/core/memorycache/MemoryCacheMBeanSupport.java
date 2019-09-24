package com.meditrusthealth.fast.common.core.memorycache;

import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public abstract class MemoryCacheMBeanSupport implements MemoryCacheMBean {

	private static final SerializerFeature[] JSON_SERIALIZER_FEATURES = new SerializerFeature[0];

	private final AtomicInteger addedCount = new AtomicInteger(0);
	private final AtomicInteger getCount = new AtomicInteger(0);
	private final AtomicInteger hitCount = new AtomicInteger(0);
	private final AtomicInteger unhitCount = new AtomicInteger(0);
	private final AtomicInteger removedCount = new AtomicInteger(0);

	public MemoryCacheMBeanSupport() {
	}

	@Override
	public final int getAddedCount() {
		return addedCount.get();
	}

	@Override
	public final int getGetCount() {
		return getCount.get();
	}

	@Override
	public final int getHitCount() {
		return hitCount.get();
	}

	@Override
	public final int getUnhitCount() {
		return unhitCount.get();
	}

	@Override
	public final int getRemovedCount() {
		return removedCount.get();
	}

	@Override
	public final int getCachedCount() {
		return size();
	}

	@Override
	public final String getMemeoryCacheObjects() {
		Object object = getSerializeCacheObject();
		if (object == null) {
			return null;
		}
		return JSON.toJSONString(object, JSON_SERIALIZER_FEATURES);
	}

	protected void incrementAddedCount() {
		addedCount.incrementAndGet();
	}

	protected void incrementGetCount() {
		getCount.incrementAndGet();
	}

	protected void incrementHitCount() {
		hitCount.incrementAndGet();
	}

	protected void incrementUnhitCount() {
		unhitCount.incrementAndGet();
	}

	protected void incrementRemovedCount() {
		removedCount.incrementAndGet();
	}

	protected abstract int size();

	protected abstract Object getSerializeCacheObject();
}
