/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.web.exception;

import com.meditrusthealth.fast.common.core.utils.Tools;
import com.meditrusthealth.fast.common.core.web.enums.FastEnum;

/**
 * <p>
 * 业务异常
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2017年11月1日 上午10:11:23
 * @version 1.0.0
 */
public class CommonException extends Exception {

	private static final long serialVersionUID = 946098281133332679L;

	private String code = Tools.EMPTY_STRING;
	private String subMessage = Tools.EMPTY_STRING;

	public CommonException(FastEnum fastEnum) {
		this(fastEnum.getCode(), fastEnum.getMessage());
	}

	public CommonException(String code, String message) {
		super(message);
		this.code = code;
	}

	public CommonException(String code, String message, String subMessage) {
		super(message);
		this.code = code;
		this.subMessage = subMessage;
	}

	public CommonException(String code, String message, Throwable throwable) {
		super(message, throwable);
		this.code = code;
	}

	public CommonException(String code, String message, String subMessage, Throwable throwable) {
		super(message, throwable);
		this.code = code;
		this.subMessage = subMessage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSubMessage() {
		return subMessage;
	}

}
