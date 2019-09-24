package com.meditrusthealth.fast.common.core.convert;

class IntegerEditor extends ExtendedPropertyEditor {

	@Override
	protected Object convert(String text) {
		return Integer.valueOf(text);
	}
}
