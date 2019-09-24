/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.utils;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2018年1月10日 下午5:29:50
 * @version 1.0.0
 */
public class IdNumUtilsTest {

	public static void main(String[] args) {
		String idCard = "341127199110112819";
		
		System.out.println(IdNumUtils.getPinYin("王孝雨"));
		System.out.println(IdNumUtils.getPinYinHeadChar("王孝雨"));
		System.out.println(IdNumUtils.conver15To18(idCard));
		System.out.println(IdNumUtils.conver15To18(idCard));
		System.out.println(IdNumUtils.isIdNum(IdNumUtils.conver15To18(idCard)));
		System.out.println(IdNumUtils.isIdNum(idCard));
		idCard = IdNumUtils.conver15To18(idCard);
		System.out.println(IdNumUtils.getAgeByIdNum(idCard));
		System.out.println(IdNumUtils.getSexByIdNum(idCard));
		System.out.println(IdNumUtils.getBirthByIdNum(idCard));

	}

}
