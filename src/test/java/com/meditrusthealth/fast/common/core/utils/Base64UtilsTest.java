/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.utils;

import org.junit.Assert;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2017年11月26日 下午9:24:32
 * @version 1.0.0
 */
public class Base64UtilsTest {

	public static void main(String[] args) {
		String data = "Fast123@#$!_.,.。；／base641234567890sjdladoaepqjvnifeiq";
		String encode = Base64Utils.encode(data);
		System.out.println(String.format("encode : %s ", encode));

		String decode = Base64Utils.decode(encode);
		Assert.assertEquals(data, decode);
	}
}
