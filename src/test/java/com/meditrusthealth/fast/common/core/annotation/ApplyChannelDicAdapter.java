/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.annotation;

import com.meditrusthealth.fast.common.core.annotation.utils.AbstractEpColumnDicAdapter;
import com.meditrusthealth.fast.common.core.annotation.utils.EpColumnData;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2018年6月13日 下午2:11:23
 * @version 1.0.0
 */
public class ApplyChannelDicAdapter extends AbstractEpColumnDicAdapter {

	@Override
	public Object getDicValue(EpColumnData epColumnData) {
		String value = String.valueOf(epColumnData.getValue());
		if ("applyChannelDic".equals(epColumnData.getDictCode())) {
			switch (value) {
			case "H5":
				value = "HTML5网页";
				break;
			case "WECHAT":
				value = "微信";
				break;
			default:
				value = "其他渠道";
				break;
			}
			return value;
		}
		return null;
	}

}
