/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core;

import com.meditrusthealth.fast.common.core.utils.IdWorker;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2017年11月17日 下午4:19:15
 * @version 1.0.0
 */
public class IdWorkerTest {

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			System.out.println(IdWorker.nextId());
		}

		for (int i = 0; i < 10; i++) {
			System.out.println(IdWorker.unique());
		}

	}
}
