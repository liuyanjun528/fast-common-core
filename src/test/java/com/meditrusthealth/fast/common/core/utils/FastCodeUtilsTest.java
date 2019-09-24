/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.utils;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2018年1月14日 下午2:12:13
 * @version 1.0.0
 */
public class FastCodeUtilsTest {

	public static void main(String[] args) {
		//  银行卡
		testCardNum("6217001210014212879");
		// 身份证
		testIdNum("350526197506029752");

		System.out.printf("姓名 %s \n", FastCodeUtils.maskRealName("秦始皇"));
		System.out.printf("手机号 %s \n", FastCodeUtils.maskMobile("15910672348"));

	}

	private static void testIdNum(String idNum) {
		String encryptIdNum = FastCodeUtils.encrypt(idNum);
		String decryptIdNum = FastCodeUtils.decrypt(encryptIdNum);
		String maskIdNum = FastCodeUtils.decryptAndMaskIdCard(encryptIdNum);
		System.out.printf("身份证 %s %s %s \n", encryptIdNum, decryptIdNum, maskIdNum);
	}

	private static void testCardNum(String cardNum) {
		String encryptCardNum = FastCodeUtils.encrypt(cardNum);
		String decryptCardNum = FastCodeUtils.decrypt(encryptCardNum);
		String maskCardNum = FastCodeUtils.decryptAndMaskBankCard(encryptCardNum);
		System.out.printf("银行卡 %s %s %s \n", encryptCardNum, decryptCardNum, maskCardNum);
	}

}
