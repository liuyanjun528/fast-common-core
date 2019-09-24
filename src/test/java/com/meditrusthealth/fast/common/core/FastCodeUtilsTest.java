/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core;

import com.meditrusthealth.fast.common.core.utils.FastCodeUtils;

/**
 * <p> 
 * </p>
 *
 * @author  xiaoyu.wang
 * @date  2017年11月17日 下午5:13:48
 * @version  1.0.0
 */
public class FastCodeUtilsTest {
	
	public static void main(String[] args) {
		System.out.println(FastCodeUtils.maskRealName("镁信健康"));
		System.out.println(FastCodeUtils.maskIdCard("341198199012302389"));
		System.out.println(FastCodeUtils.maskBankCard("6217002347569876"));
	}
	
}
