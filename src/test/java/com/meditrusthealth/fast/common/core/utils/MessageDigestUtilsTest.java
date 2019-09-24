/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.utils;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2017年11月26日 下午9:30:01
 * @version 1.0.0
 */
public class MessageDigestUtilsTest {

	public static void main(String[] args) {
		String data = "123456";
		String salt = "FK9#@%SBD2017LPO0";
		String digest = MessageDigestUtils.md5(data);
		System.out.println(String.format("digest : %s", digest));

		String digestSalt = MessageDigestUtils.md5(data, salt);
		System.out.println(String.format("digestSalt : %s", digestSalt));
	}
}
