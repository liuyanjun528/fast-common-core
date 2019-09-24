package com.meditrusthealth.fast.common.core.resource.props.loader;

import java.util.Properties;

import com.meditrusthealth.fast.common.core.resource.PlaceholderResolver;
import com.meditrusthealth.fast.common.core.utils.Assert;
import com.meditrusthealth.fast.common.core.utils.Tools;

public class PropertiesPlaceholderResolver implements PlaceholderResolver {

	private static final PropertiesPlaceholderResolver DEFAULT_INSTANCE = new PropertiesPlaceholderResolver();

	private static final String PLACEHOLDER_START = "${";
	private static final String PLACEHOLDER_END = "}";

	private final String placeholderStart;
	private final String placeholderEnd;
	private final Properties properties;

	private PropertiesPlaceholderResolver() {
		this(PLACEHOLDER_START, PLACEHOLDER_END, System.getProperties());
	}

	public PropertiesPlaceholderResolver(String placeholderStart, String placeholderEnd) {
		this(placeholderStart, placeholderEnd, System.getProperties());
	}

	public PropertiesPlaceholderResolver(String placeholderStart, String placeholderEnd, Properties properties) {
		Assert.notEmpty(placeholderStart, "placeholderStart");
		Assert.notEmpty(placeholderEnd, "placeholderEnd");
		Assert.notNull(properties, "properties");
		this.placeholderStart = placeholderStart.trim();
		this.placeholderEnd = placeholderEnd.trim();
		this.properties = properties;
	}

	public static PropertiesPlaceholderResolver defaultPropertiesPlaceholderResolver() {
		return DEFAULT_INSTANCE;
	}

	@Override
	public String resolve(String text) {
		if (Tools.isBlank(text)) {
			return text;
		}
		int start = text.indexOf(getPlaceholderStart());
		int last = text.lastIndexOf(getPlaceholderEnd());
		if (start < 0 || last < 0 || last < start) {
			return text;
		}
		StringBuilder builder = new StringBuilder();
		int offset = 0;
		while (start > -1) {
			int next = getStartNextIndex(start);
			int end = text.indexOf(getPlaceholderEnd(), next);
			if (end < 0) {
				break;
			}
			String name = text.substring(next, end);
			int inner = name.lastIndexOf(getPlaceholderStart());
			if (inner > -1) {
				start = inner + next;
				next = getStartNextIndex(start);
				name = text.substring(next, end);
			}
			builder.append(text, offset, start);
			builder.append(getPropertyValue(text, name, start, end));
			offset = end + 1;
			start = text.indexOf(getPlaceholderStart(), getEndNextIndex(end));
		}
		if (offset < text.length()) {
			builder.append(text, offset, text.length());
		}
		return builder.toString();
	}

	public String getPlaceholderStart() {
		return placeholderStart;
	}

	public String getPlaceholderEnd() {
		return placeholderEnd;
	}

	public int getStartNextIndex(int start) {
		if (start < 0) {
			return 0;
		}
		return start + getPlaceholderStart().length();
	}

	public int getEndNextIndex(int end) {
		if (end < 0) {
			return 0;
		}
		return end + getPlaceholderEnd().length();
	}

	public Properties getProperties() {
		return properties;
	}

	private String getPropertyValue(String text, String name, int start, int end) {
		String value = getProperties().getProperty(name);
		if (Tools.isBlank(value)) {
			StringBuilder builder = new StringBuilder();
			builder.append("property configuration string includes invalid placeholder '").append(name)
					.append("' at index ").append(start).append("-").append(end).append('\n').append('\n');
			builder.append('\t').append(text).append('\n');
			char[] chs = new char[end + 1];
			fill(chs, '.', 0, start);
			fill(chs, '^', start, end + 1);
			builder.append('\t').append(new String(chs)).append('\n');
			throw new IllegalArgumentException(builder.toString());
		}
		return value;
	}

	private static void fill(char[] chs, char fill, int start, int end) {
		if (chs == null || chs.length == 0) {
			return;
		}
		Assert.notLesser(start, 0, "start");
		Assert.notLesser(end, 0, "end");
		Assert.notGreater(end, chs.length, "end");
		for (int i = start; i < end; i++) {
			chs[i] = fill;
		}
	}
}
