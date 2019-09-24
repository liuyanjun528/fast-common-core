/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.annotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2018年6月13日 下午2:02:35
 * @version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EpColumnDir(name = "ApplyDir")
public class ApplyColumn {

	@EpColumn(desc = "姓名", security = true, display = true, required = true)
	private String name;

	@EpColumn(desc = "年龄")
	private int age;

	@EpColumn(desc = "VIP会员")
	private boolean isVip;

	@EpColumn(desc = "渠道", dictCode = "applyChannelDic", dataDicAdapter = ApplyChannelDicAdapter.class)
	private String applyChannel;
}
