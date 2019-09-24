/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.utils;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2018年1月25日 下午5:46:33
 * @version 1.0.0
 */
public class RSAUtilsTest {

	public static void main(String[] args) throws Exception {
		String pubFilePath = RSAKeyUtils.class.getClassLoader().getResource("pub.key").getPath();
		String priFilePath = RSAKeyUtils.class.getClassLoader().getResource("pri.key").getPath();
		pubEncryptpriDecrypt(pubFilePath, priFilePath);
		priEncryptpubDecrypt(pubFilePath, priFilePath);
	}

	private static void priEncryptpubDecrypt(String pubFilePath, String priFilePath) throws Exception {
		String plaintext = "123@#$";
		String ciphertext = RSAUtils.priEncrypt(priFilePath, plaintext);
		String result = RSAUtils.pubDecrypt(pubFilePath, ciphertext);
		System.out.println(result.equals(plaintext));
	}

	private static void pubEncryptpriDecrypt(String pubFilePath, String priFilePath) throws Exception {
		String plaintext = "123@#$";
		String ciphertext = RSAUtils.pubEncrypt(pubFilePath, plaintext);
		String result = RSAUtils.priDecrypt(priFilePath, ciphertext);
		System.out.println(result.equals(plaintext));
	}

}
