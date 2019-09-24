/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtilsTest {

	public static void main(String[] args) {
		String templateFileName = "template/template.xls";
		String resultFileName = "result/fruit.xls";
		Map<String, Object> beanParams = new HashMap<>();
		List<Map<String, String>> freighBillModels = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put("k", "V");
		freighBillModels.add(map);
		beanParams.put("list", freighBillModels);
		(new ExcelUtils()).createExcel(templateFileName, beanParams, resultFileName);
	}

}
