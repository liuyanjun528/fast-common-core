/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.web.enums;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2018年1月5日 下午2:46:17
 * @version 1.0.0
 */
public enum ChannelEnum {

	WECHAT("WECHAT", "药康付微信公众号"),

	WECHAT_MINIAPP("WXMA", "药康付微信小程序"),

	DRUGSTORE_IPAD("IPAD", "药店iPad"),

	AMDIN("ADMIN", "管理后台"),

	WXCEBBANK("WXCEBBANK", "药康付微信公众号-联名信用卡");

	private String code;
	private String message;

	private ChannelEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static ChannelEnum getEnumByCode(String code) {
		for (ChannelEnum channelEnum : ChannelEnum.values()) {
			if (channelEnum.getCode().equals(code)) {
				return channelEnum;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return String.format("ChannelEnum name = %s code = %s message = %s", this.name(), code, message);
	}
}
