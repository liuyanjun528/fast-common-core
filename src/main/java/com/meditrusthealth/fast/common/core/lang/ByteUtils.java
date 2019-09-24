package com.meditrusthealth.fast.common.core.lang;

import static com.meditrusthealth.fast.common.core.utils.Tools.DEFAULT_CHARSET;
import static com.meditrusthealth.fast.common.core.utils.Tools.EMPTY_STRING;
import static com.meditrusthealth.fast.common.core.utils.Tools.HALF_BYTE_BITS;
import static com.meditrusthealth.fast.common.core.utils.Tools.HALF_BYTE_MASK;

import java.nio.charset.Charset;

public final class ByteUtils {

	public static final byte[] EMPTY_BYTES = new byte[0];

	private static final char[] HEX = "0123456789abcdef".toCharArray();

	private static final char DEFAULT_SEPERATOR = ' ';

	private ByteUtils() {
	}

	public static byte[] toBytes(String str) {
		return toBytes(str, DEFAULT_CHARSET);
	}

	public static String toString(byte[] bys) {
		return toString(bys, DEFAULT_CHARSET);
	}

	public static byte[] toBytes(String str, Charset charset) {
		if (str == null) {
			return null;
		}
		if (str.length() == 0) {
			return EMPTY_BYTES;
		}
		return str.getBytes(charset);
	}

	public static String toString(byte[] bys, Charset charset) {
		if (bys == null) {
			return null;
		}
		if (bys.length == 0) {
			return EMPTY_STRING;
		}
		return new String(bys, charset);
	}

	public static String toHex(byte[] bys) {
		if (bys == null) {
			return null;
		}
		if (bys.length == 0) {
			return EMPTY_STRING;
		}
		char[] chs = new char[bys.length << 1];
		for (int i = 0, k = 0; i < bys.length; i++) {
			chs[k++] = toHexChar(bys[i], HALF_BYTE_BITS);
			chs[k++] = toHexChar(bys[i]);
		}
		return new String(chs);
	}

	public static String toBinarySpace(byte[] bys) {
		if (bys == null) {
			return null;
		}
		if (bys.length == 0) {
			return EMPTY_STRING;
		}
		char[] chs = new char[(bys.length * Byte.SIZE) + bys.length - 1];
		for (int i = 0, k = 0; i < bys.length; i++) {
			if (i > 0) {
				chs[k++] = ' ';
			}
			chs[k++] = (char) ('0' + ((bys[i] >>> 7) & 1));
			chs[k++] = (char) ('0' + ((bys[i] >>> 6) & 1));
			chs[k++] = (char) ('0' + ((bys[i] >>> 5) & 1));
			chs[k++] = (char) ('0' + ((bys[i] >>> 4) & 1));
			chs[k++] = (char) ('0' + ((bys[i] >>> 3) & 1));
			chs[k++] = (char) ('0' + ((bys[i] >>> 2) & 1));
			chs[k++] = (char) ('0' + ((bys[i] >>> 1) & 1));
			chs[k++] = (char) ('0' + ((bys[i] >>> 0) & 1));
		}
		return new String(chs);
	}

	public static char toHexChar(int num) {
		return toHexChar(num, 0);
	}

	public static char toHexChar(int num, int rightShiftBits) {
		return HEX[(num >>> rightShiftBits) & HALF_BYTE_MASK];
	}

	public static String toHex(String str) {
		return toHex(toBytes(str));
	}

	public static String toHexSpace(byte[] bys) {
		return toHexSeperator(bys, DEFAULT_SEPERATOR);
	}

	public static String toHexSeperator(byte[] bys, char seperator) {
		if (bys == null) {
			return null;
		}
		char[] chs = new char[(bys.length << 1) + bys.length - 1];
		for (int i = 0, k = 0; i < bys.length; i++) {
			if (i > 0) {
				chs[k++] = seperator;
			}
			chs[k++] = HEX[(bys[i] >> HALF_BYTE_BITS) & HALF_BYTE_MASK];
			chs[k++] = HEX[bys[i] & HALF_BYTE_MASK];
		}
		return new String(chs);
	}
}
