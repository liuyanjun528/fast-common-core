/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core;

import com.meditrusthealth.fast.common.core.utils.FastKeyGenerator;
import com.meditrusthealth.fast.common.core.utils.RedisKeyGenerator;
import com.meditrusthealth.fast.common.core.web.enums.FastDomainEnum;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2017年11月17日 下午8:13:59
 * @version 1.0.0
 */
public class RedisKeyGeneratorTest {

	public static void main(String[] args) {

		for (int i = 0; i < 2000; i++) {
			System.out.println(RedisKeyGenerator.generateKey(FastDomainEnum.account_provider, "account"));
			System.out.println(RedisKeyGenerator.generateKey(FastDomainEnum.account_provider, "account",
					FastKeyGenerator.generateKey(FastDomainEnum.account_provider)));
		}
	}

}
