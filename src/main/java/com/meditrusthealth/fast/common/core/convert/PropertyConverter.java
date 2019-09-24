package com.meditrusthealth.fast.common.core.convert;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meditrusthealth.fast.common.core.lang.ReflectionUtils;
import com.meditrusthealth.fast.common.core.utils.Assert;

public final class PropertyConverter {

	private static final Logger LOG = LoggerFactory.getLogger(PropertyConverter.class);

	private static final String packagePrefix = PropertyConverter.class.getPackage().getName() + ".";

	private static final Map<Class<?>, Class<?>> registry = new HashMap<Class<?>, Class<?>>();

	static {
		load(Boolean.class, "Boolean");
		load(Integer.class, "Integer");
		load(String[].class, "StringArray");
		load(int[].class, "IntArray");
	}

	private PropertyConverter() {
	}

	public static void register(Class<?> targetType, Class<? extends PropertyEditor> editorClass) {
		if (!includePropertyEditor(targetType)) {
			getRegistry().put(targetType, editorClass);
		}
	}

	public static Object convert(String text, Class<?> targetType) {
		PropertyEditor editor = getEditor(targetType);
		if (editor == null) {
			throw new UnsupportedOperationException("target type '" + targetType + "' is unsupported");
		}
		editor.setAsText(text);
		return editor.getValue();
	}

	private static PropertyEditor getEditor(Class<?> targetType) {
		Assert.notNull(targetType, "targetType");
		PropertyEditor editor = (PropertyEditor) ReflectionUtils.newInstance(registry.get(targetType));
		if (editor == null) {
			return PropertyEditorManager.findEditor(targetType);
		}
		LOG.debug("PropertyEditor = '{}', targetType = '{}'", editor, targetType);
		return editor;
	}

	private static Map<Class<?>, Class<?>> getRegistry() {
		return registry;
	}

	private static boolean includePropertyEditor(Class<?> targetClass) {
		return getRegistry().containsKey(targetClass);
	}

	private static void load(Class<?> type, String editor) {
		LOG.debug("load convert, target type: {}, editor name: {}", type, editor);
		String className = packagePrefix + editor + "Editor";
		getRegistry().put(type, ReflectionUtils.loadClass(className));
	}
}
