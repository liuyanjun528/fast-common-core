package com.meditrusthealth.fast.common.core.memorycache;

public interface MemoryCacheMBean {

    String getName();

    int getAddedCount();

    int getGetCount();

    int getHitCount();

    int getUnhitCount();

    int getRemovedCount();

    int getCachedCount();

    String getMemeoryCacheObjects();
}
