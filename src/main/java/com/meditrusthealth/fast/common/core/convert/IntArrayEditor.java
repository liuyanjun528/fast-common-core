package com.meditrusthealth.fast.common.core.convert;

import com.meditrusthealth.fast.common.core.utils.EmptyConstants;

class IntArrayEditor extends ArrayPropertyEditor {

	@Override
	protected Object arrayConvert(String[] array) {

		if (array.length == 0) {
			return EmptyConstants.EMPTY_INT_ARRAY;
		}

		int[] ints = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			ints[i] = Integer.parseInt(array[i]);
		}

		return ints;
	}
}
