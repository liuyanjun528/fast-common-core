/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.utils;

import com.meditrusthealth.fast.common.core.web.enums.FastDomainEnum;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2017年11月17日 下午4:58:19
 * @version 1.0.0
 */

public final class RedisKeyGenerator {

	public static String generateKey(FastDomainEnum fastDomainEnum, String type) {
		Assert.notEmpty(type, "RedisKeyGenerator type");
		return String.format("%s:%s", fastDomainEnum.getCode(), type);
	}

	public static String generateKey(FastDomainEnum fastDomainEnum, String type, String... values) {
		Assert.notEmpty(type, "RedisKeyGenerator type");
		StringBuilder key = new StringBuilder(fastDomainEnum.getCode());
		key.append(":");
		key.append(type);
		if (!Tools.isBlank(values)) {
			for (int i = 0; i < values.length; i++) {
				key.append(":");
				key.append(values[i]);
			}
		}
		return key.toString();
	}
}
