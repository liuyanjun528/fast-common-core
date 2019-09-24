package com.meditrusthealth.fast.common.core.convert;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meditrusthealth.fast.common.core.utils.Tools;

public abstract class ArrayPropertyEditor extends ExtendedPropertyEditor {

	private static final Logger LOG = LoggerFactory.getLogger(ArrayPropertyEditor.class);

	private static final Pattern SEPERATOR = Pattern.compile("\\s*[,;]\\s*");

	@Override
	protected String toValueString(Object value) {
		if (value.getClass().isArray()) {
			Object[] array = (Object[]) value;
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < array.length; i++) {
				if (i > 0) {
					builder.append(", ");
				}
				builder.append(toItemString(array[i]));
			}
		}
		return super.toValueString(value);
	}

	@Override
	protected Object convert(String text) {

		LOG.debug("convert text = {}", text);

		String[] array = Tools.split(text, SEPERATOR);
		if (array == null) {
			return null;
		}

		return arrayConvert(array);
	}

	protected String toItemString(Object item) {
		return super.toValueString(item);
	}

	protected abstract Object arrayConvert(String[] array);
}
