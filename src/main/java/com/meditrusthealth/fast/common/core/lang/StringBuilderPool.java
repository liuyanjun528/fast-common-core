package com.meditrusthealth.fast.common.core.lang;

public final class StringBuilderPool {

	private static final int DEFAULT_SIZE = NumberUtils.parseInt(System.getProperty("vpal.stringbuilder.capacity"),
			2048);

	private static final ThreadLocal<StringBuilder> POOL = new ThreadLocal<StringBuilder>() {
		@Override
		protected StringBuilder initialValue() {
			return new StringBuilder(DEFAULT_SIZE);
		}
	};

	private StringBuilderPool() {
	}

	public static StringBuilder get() {
		StringBuilder builder = POOL.get();
		builder.delete(0, builder.length());
		return builder;
	}
}
