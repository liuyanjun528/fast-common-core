package com.meditrusthealth.fast.common.core.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import flexjson.JSONSerializer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonUtils {
	public static JsonConfig jsonConfig = new JsonConfig();

	public JsonUtils() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List json2List(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		List<Map> list = new ArrayList();
		Iterator var3 = jsonObject.keySet().iterator();

		while (var3.hasNext()) {
			Object obj = var3.next();
			Map map = json2Map(jsonObject.getString(obj.toString()));
			list.add(map);
		}

		return list;
	}

	public static Map<Object, String> json2Map(String jsonString) {
		Map<Object, String> map = new HashMap<Object, String>();
		JSONObject jsonObj = JSONObject.fromObject(jsonString);
		Iterator<?> var3 = jsonObj.keySet().iterator();

		while (var3.hasNext()) {
			Object object = var3.next();
			String value = jsonObj.get(object.toString()).toString();
			map.put(object, value);
		}

		return map;
	}

	@SuppressWarnings("unchecked")
	public static <T> T json2Object(String jsonString, Class<T> objectClass) {
		JSONObject jsonObj = JSONObject.fromObject(jsonString);
		return (T) JSONObject.toBean(jsonObj, objectClass);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T json2Object(String jsonString, Class<T> objectClass, Map<String, Class> specialCollections) {
		JSONObject jsonObj = JSONObject.fromObject(jsonString);
		return (T) JSONObject.toBean(jsonObj, objectClass, specialCollections);
	}

	public static String object2Json(Object o) {
		return o == null ? "" : JSONObject.fromObject(o, jsonConfig).toString();
	}

	public static String object2Json(Object o, JsonConfig jsonConfig) {
		return o == null ? "" : JSONObject.fromObject(o, jsonConfig).toString();
	}

	@SuppressWarnings("rawtypes")
	public static String list2Json(List list, boolean prettyPrint) {
		return (new JSONSerializer()).exclude(new String[] { "*.class" }).prettyPrint(prettyPrint).serialize(list);
	}

	@SuppressWarnings("rawtypes")
	public static String list2Json(List list) {
		return list2Json(list, false);
	}

	@SuppressWarnings("rawtypes")
	public static String list2Json(List list, boolean prettyPrint, String... excludes) {
		JSONSerializer serializer = new JSONSerializer();
		String[] exclude = new String[excludes.length + 1];

		for (int i = 0; i < excludes.length; ++i) {
			exclude[i] = excludes[i];
		}

		exclude[excludes.length] = "*.class";
		serializer.exclude(exclude);
		return serializer.prettyPrint(prettyPrint).serialize(list);
	}

	@SuppressWarnings("rawtypes")
	public static String list2Json(List list, String... excludes) {
		return list2Json(list, false, excludes);
	}

	public static JSONArray json2Array(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray;
	}

	public static JSONArray object2JsonArray(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object, jsonConfig);
		return jsonArray;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap<String, Object> jsonArray2Map(JSONObject json) {
		HashMap<String, Object> map = new HashMap();
		Set<?> keys = json.keySet();
		Iterator var3 = keys.iterator();

		while (var3.hasNext()) {
			Object key = var3.next();
			Object o = json.get(key);
			if (o instanceof JSONArray) {
				map.put((String) key, jsonArray2List((JSONArray) o));
			} else if (o instanceof JSONObject) {
				map.put((String) key, jsonArray2Map((JSONObject) o));
			} else {
				map.put((String) key, o);
			}
		}

		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List jsonArray2List(JSONArray json) {
		List<Object> list = new ArrayList();
		Iterator var2 = json.iterator();

		while (var2.hasNext()) {
			Object o = var2.next();
			if (o instanceof JSONArray) {
				list.add(jsonArray2List((JSONArray) o));
			} else if (o instanceof JSONObject) {
				list.add(jsonArray2Map((JSONObject) o));
			} else {
				list.add(o);
			}
		}

		return list;
	}

	@SuppressWarnings("rawtypes")
	public static List json2ArrayList(String jsonString) {
		JSONArray json = json2Array(jsonString);
		List list = jsonArray2List(json);
		return list;
	}

	static {
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
		jsonConfig.registerJsonValueProcessor(Time.class, new JsonDateValueProcessor("HH:mm:ss"));
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
	}
}
