package com.meditrusthealth.fast.common.core.ide;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Float.floatToIntBits;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.meditrusthealth.fast.common.core.utils.Tools;

public abstract class EclipseTools {

	public static class EqualsBuilder {

		private boolean equals;

		public EqualsBuilder() {
			this.equals = true;
		}

		public EqualsBuilder appendSuper(boolean superEquals) {
			if (this.equals) {
				this.equals = superEquals;
			}
			return this;
		}

		public boolean isEquals() {
			return this.equals;
		}

		public EqualsBuilder append(Object left, Object right) {
			if (!this.equals) {
				return this;
			}
			if (left == null && right == null) {
				return this;
			}
			if (left == null || right == null) {
				this.equals = false;
				return this;
			}
			if (left.equals(right)) {
				return this;
			}
			if (!left.getClass().isArray()) {
				this.equals = left.equals(right);
				return this;
			}
			if (left.getClass() != right.getClass()) {
				this.equals = false;
				return this;
			}
			return appendPrimitive(left, right);
		}

		private EqualsBuilder appendPrimitive(Object left, Object right) {
			if (!this.equals) {
				return this;
			}
			if (left instanceof byte[]) {
				append((byte[]) left, (byte[]) right);
			} else if (left instanceof short[]) {
				append((short[]) left, (short[]) right);
			} else if (left instanceof char[]) {
				append((char[]) left, (char[]) right);
			} else if (left instanceof int[]) {
				append((int[]) left, (int[]) right);
			} else if (left instanceof float[]) {
				append((float[]) left, (float[]) right);
			} else if (left instanceof double[]) {
				append((double[]) left, (double[]) right);
			} else if (left instanceof boolean[]) {
				append((boolean[]) left, (boolean[]) right);
			}
			return this;
		}

		public EqualsBuilder append(byte left, byte right) {
			if (this.equals) {
				this.equals = (left == right);
			}
			return this;
		}

		public EqualsBuilder append(short left, short right) {
			if (this.equals) {
				this.equals = (left == right);
			}
			return this;
		}

		public EqualsBuilder append(char left, char right) {
			if (this.equals) {
				this.equals = (left == right);
			}
			return this;
		}

		public EqualsBuilder append(int left, int right) {
			if (this.equals) {
				this.equals = (left == right);
			}
			return this;
		}

		public EqualsBuilder append(long left, long right) {
			if (this.equals) {
				this.equals = (left == right);
			}
			return this;
		}

		public EqualsBuilder append(float left, float right) {
			if (this.equals) {
				this.equals = (floatToIntBits(left) == floatToIntBits(right));
			}
			return this;
		}

		public EqualsBuilder append(double left, double right) {
			if (this.equals) {
				this.equals = (doubleToLongBits(left) == doubleToLongBits(right));
			}
			return this;
		}

		public EqualsBuilder append(boolean left, boolean right) {
			if (this.equals) {
				this.equals = (left == right);
			}
			return this;
		}

		// Array
		public EqualsBuilder append(Object[] left, Object[] right) {
			if (!equals || left == right) {
				return this;
			}
			if (left == null || right == null) {
				equals = false;
				return this;
			} else if (left.length != right.length) {
				return this;
			}
			for (int i = 0; i < left.length && equals; ++i) {
				append(left[i], right[i]);
			}
			return this;
		}
	}

	public static final class HashCodeBuilder {

		private static final int PRIME = 31;

		private int hashCode = 1;

		public void append(Object value) {
			if (value != null) {
				hashCode = hashCode * PRIME + value.hashCode();
			}
		}

		public void append(byte value) {
			hashCode = hashCode * PRIME + value;
		}

		public void append(short value) {
			hashCode = hashCode * PRIME + value;
		}

		public void append(char value) {
			hashCode = hashCode * PRIME + value;
		}

		public void append(int value) {
			hashCode = hashCode * PRIME + value;
		}

		public void append(long value) {
			hashCode = hashCode * PRIME + (int) (value ^ (value >>> (Long.SIZE >> 1)));
		}

		public void append(float value) {
			append(Float.floatToIntBits(value));
		}

		public void append(double value) {
			append(Double.doubleToLongBits(value));
		}

		public int toHashCode() {
			return hashCode;
		}
	}

	public static final class ToString {

		private static final int MAX = 10;

		private final StringBuilder builder;
		private int count;

		public ToString() {
			this.builder = new StringBuilder();
		}

		public ToString(String prefix) {
			this(prefix, null, true);
		}

		public ToString(Object target) {
			this(null, target);
		}

		public ToString(String prefix, Object target) {
			this(prefix, target, false);
		}

		public ToString(String prefix, Object target, boolean ignoreNull) {

			this();

			if (prefix != null) {
				builder.append(prefix);
			}

			if (target == null) {

				if (!ignoreNull) {
					builder.append("null");
				}
			} else {
				builder.append(target.getClass().getSimpleName());
				builder.append('@');
				builder.append(Integer.toHexString(System.identityHashCode(target)));
				builder.append(": ");
			}
		}

		public ToString append(String field, Object value) {
			if (value != null) {
				appendField(field).append('[').append(value).append(']');
			}
			return this;
		}

		public ToString append(String field, byte value) {
			return appendField(field).append('[').append(value).append(']');
		}

		public ToString append(String field, short value) {
			return appendField(field).append('[').append(value).append(']');
		}

		public ToString append(String field, char value) {
			return appendField(field).append('[').append(value).append(']');
		}

		public ToString append(String field, int value) {
			return appendField(field).append('[').append(value).append(']');
		}

		public ToString append(String field, long value) {
			return appendField(field).append('[').append(value).append(']');
		}

		public ToString append(String field, boolean value) {
			return appendField(field).append('[').append(value).append(']');
		}

		public ToString append(String field, float value) {
			return appendField(field).append('[').append(value).append(']');
		}

		public ToString append(String field, double value) {
			return appendField(field).append('[').append(value).append(']');
		}

		public ToString append(String field, int[] value) {
			if (value != null) {
				appendField(field).append(value);
			}
			return this;
		}

		public ToString append(String field, byte[] value) {
			if (value != null) {
				appendField(field).append(value);
			}
			return this;
		}

		public ToString append(String field, short[] value) {
			if (value != null) {
				appendField(field).append(value);
			}
			return this;
		}

		public ToString append(String field, char[] value) {
			if (value != null) {
				appendField(field).append(value);
			}
			return this;
		}

		public ToString append(String field, long[] value) {
			if (value != null) {
				appendField(field).append(value);
			}
			return this;
		}

		public ToString append(String field, float[] value) {
			if (value != null) {
				appendField(field).append(value);
			}
			return this;
		}

		public ToString append(String field, double[] value) {
			if (value != null) {
				appendField(field).append(value);
			}
			return this;
		}

		public ToString append(String field, Collection<?> value) {
			if (value != null) {
				appendField(field).append('[').append(value).append(']');
			}
			return this;
		}

		public ToString append(String field, Map<?, ?> value) {
			if (value != null) {
				appendField(field).append('[').append(value).append(']');
			}
			return this;
		}

		public ToString append(String field, Date value) {
			if (value != null) {
				appendField(field).append('[').append(Tools.formatWithMillis(value)).append(']');
			}
			return this;
		}

		public ToString appendUnix(String field, long unixEpoch) {
			appendField(field).append('[').append(Tools.formatWithMillis(new Date(unixEpoch))).append(']');
			return this;
		}

		public ToString appendParent(Object value) {
			builder.append("\n  parent: ").append(value);
			return this;
		}

		public ToString append(Object value) {
			builder.append(value);
			return this;
		}

		public ToString append(byte value) {
			builder.append(value);
			return this;
		}

		public ToString append(short value) {
			builder.append(value);
			return this;
		}

		public ToString append(char value) {
			builder.append(value);
			return this;
		}

		public ToString append(int value) {
			builder.append(value);
			return this;
		}

		public ToString append(long value) {
			builder.append(value);
			return this;
		}

		public ToString append(boolean value) {
			builder.append(value);
			return this;
		}

		public ToString append(float value) {
			builder.append(value);
			return this;
		}

		public ToString append(double value) {
			builder.append(value);
			return this;
		}

		public ToString append(byte[] value) {
			builder.append(Arrays.toString(value));
			return this;
		}

		public ToString append(short[] value) {
			builder.append(Arrays.toString(value));
			return this;
		}

		public ToString append(char[] value) {
			builder.append(Arrays.toString(value));
			return this;
		}

		public ToString append(int[] value) {
			builder.append(Arrays.toString(value));
			return this;
		}

		public ToString append(long[] value) {
			builder.append(Arrays.toString(value));
			return this;
		}

		public ToString append(float[] value) {
			builder.append(Arrays.toString(value));
			return this;
		}

		public ToString append(double[] value) {
			builder.append(Arrays.toString(value));
			return this;
		}

		public ToString append(Collection<?> value) {
			if (value == null) {
				builder.append("null");
				return this;
			}

			int offset = 0;
			for (Object item : value) {
				if (offset++ > MAX) {
					break;
				}
				if (offset > 1) {
					builder.append(", ");
				}
				builder.append(item);
			}

			return this;
		}

		public ToString append(Map<?, ?> value) {
			if (value == null) {
				builder.append("null");
				return this;
			}
			int offset = 0;
			for (Map.Entry<?, ?> entry : ((Map<?, ?>) value).entrySet()) {
				if (offset++ > MAX) {
					break;
				}
				if (offset > 1) {
					builder.append(", ");
				}
				builder.append(entry.getKey());
				builder.append(" : ");
				builder.append(entry.getValue());
			}
			return this;
		}

		private ToString appendField(String field) {
			if (count++ > 0) {
				builder.append(", ");
			}
			builder.append(field).append(" = ");
			return this;
		}

		@Override
		public String toString() {
			return builder.toString();
		}
	}
}
