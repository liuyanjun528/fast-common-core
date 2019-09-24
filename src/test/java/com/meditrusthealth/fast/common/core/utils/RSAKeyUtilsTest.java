/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.utils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.meditrusthealth.fast.common.core.utils.RSAKeyUtils;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2017年11月21日 上午10:55:42
 * @version 1.0.0
 */
public class RSAKeyUtilsTest {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		RSAKeyUtils.generateKey("./src/main/resources/pub.key", "./src/main/resources/pri.key", "fast2017");
	}

}
