package com.meditrusthealth.fast.common.core.lang;

import com.meditrusthealth.fast.common.core.utils.Tools;

public final class NumberUtils {

	private NumberUtils() {
	}

	public static int parseInt(String num, int defaultValue) {
		if (Tools.isBlank(num)) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(num);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static long parseLong(String num, long defaultValue) {
		if (Tools.isBlank(num)) {
			return defaultValue;
		}
		try {
			return Long.parseLong(num);
		} catch (Exception e) {
			return defaultValue;
		}
	}
}
