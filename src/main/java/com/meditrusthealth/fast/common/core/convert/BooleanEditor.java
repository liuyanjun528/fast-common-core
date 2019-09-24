package com.meditrusthealth.fast.common.core.convert;

class BooleanEditor extends ExtendedPropertyEditor {

	@Override
	protected Object convert(String text) throws IllegalArgumentException {
		return Boolean.valueOf(text);
	}
}
