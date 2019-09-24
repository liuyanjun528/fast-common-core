/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core;

import com.meditrusthealth.fast.common.core.utils.FastKeyGenerator;
import com.meditrusthealth.fast.common.core.web.enums.FastDomainEnum;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2017年11月17日 下午4:42:13
 * @version 1.0.0
 */
public class FastKeyGeneratorTest {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(FastKeyGenerator.generateKey(FastDomainEnum.oss));
		}
	}

}
