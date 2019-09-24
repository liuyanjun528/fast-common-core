package com.meditrusthealth.fast.common.core.convert;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ExtendedPropertyEditor extends PropertyEditorSupport {

	private static final Logger LOG = LoggerFactory.getLogger(ExtendedPropertyEditor.class);

	public final String getAsText() {
		if (getValue() == null) {
			return null;
		}
		return toValueString(getValue());
	}

	@Override
	public final void setAsText(String text) {
		LOG.trace("convert text = {}", text);
		if (text == null) {
			return;
		}
		setValue(convert(text));
	}

	protected String toValueString(Object value) {
		return String.valueOf(value);
	}

	protected abstract Object convert(String text);
}