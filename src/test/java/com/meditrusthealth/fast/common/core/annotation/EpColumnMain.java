/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.annotation;

import java.util.List;

import com.meditrusthealth.fast.common.core.annotation.utils.EpColumnData;
import com.meditrusthealth.fast.common.core.annotation.utils.EpColumnUtils;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2018年6月13日 下午2:06:40
 * @version 1.0.0
 */
public class EpColumnMain {

	public static void main(String[] args) {
		ApplyColumn applyColumn = new ApplyColumn("东周列传", 2000, true, "WECHAT");

		List<EpColumnData> epColumnDatas = EpColumnUtils.getEpColumnDataList(applyColumn);

		for (EpColumnData epColumnData : epColumnDatas) {
			System.out.println(epColumnData);
		}
	}

}
