/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.random;

import static com.meditrusthealth.fast.common.core.utils.Tools.BYTE_MASK;
import static com.meditrusthealth.fast.common.core.utils.Tools.EMPTY_STRING;
import static java.lang.Byte.SIZE;

import java.security.SecureRandom;

import com.meditrusthealth.fast.common.core.utils.Tools;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2017年11月24日 上午10:35:06
 * @version 1.0.0
 */
public class SecurityRandom {
	private static final int MAPPING_BYTES = 3;

	private static final SecureRandom random = new SecureRandom();

	private static final int[] BASE = { 0x7744, 0x546b, 0x7546, 0x7a6d, 0x4857, 0x784a, 0x674e, 0x7672, 0x4f39, 0x525a,
			0x4d6f, 0x636a, 0x6e6c, 0x5141, 0x3845, 0x4b31, 0x6156, 0x5373, 0x7135, 0x3362, 0x5070, 0x4742, 0x4250,
			0x3738, 0x3466, 0x7479, 0x3636, 0x4369, 0x5834, 0x6474, 0x6867, 0x4c54, 0x5959, 0x4952, 0x6953, 0x554b,
			0x6671, 0x704c, 0x3278, 0x7965, 0x6576 };

	private SecurityRandom() {
	}

	/**
	 * <p>
	 * 生成指定长度的随机串
	 * </p>
	 *
	 * @param length
	 *            需要生成随机串的长度，数据应为 3 的整数倍
	 * @return 随机串
	 */
	public static String generateRandom(int length) {
		byte[] bys = new byte[(length / MAPPING_BYTES) << Tools.SHIFT_BIT_1];
		random.nextBytes(bys);
		return encode(bys);
	}

	public static String encode(byte[] bys) {
		if (bys == null) {
			return null;
		}
		if (bys.length == 0) {
			return EMPTY_STRING;
		}
		return new String(arrayEncode(bys));
	}

	/**
	 * <p>
	 * 将字节转换为基于数字和大小字母的字符数组
	 * </p>
	 *
	 * @param bys
	 *            原始字节数组
	 * @return
	 */
	private static char[] arrayEncode(byte[] bys) {
		char[] chs = new char[((bys.length + 1) >> 1) * MAPPING_BYTES];
		int offset = 0, index = 0, odd = SIZE;
		while (offset < bys.length) {
			int num = (bys[offset++] & BYTE_MASK) << SIZE;
			if (offset < bys.length) {
				num |= (bys[offset++] & BYTE_MASK);
			}
			for (int i = 0; i < MAPPING_BYTES; i++) {
				chs[index++] = (char) ((BASE[num % BASE.length] >> odd) & BYTE_MASK);
				num /= BASE.length;
			}
			odd ^= SIZE;
		}
		return chs;
	}
}
