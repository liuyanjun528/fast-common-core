package com.meditrusthealth.fast.common.core.lang;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 系统工具类
 *
 */
public final class Sys {

	private Sys() {
	}

	private static final AtomicLong CURRENT_TIME_MILLIS = new AtomicLong(System.currentTimeMillis());

	private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(1);

	static {

		EXECUTOR_SERVICE.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				CURRENT_TIME_MILLIS.set(System.currentTimeMillis());
			}
		}, 0, 250, TimeUnit.MILLISECONDS);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				EXECUTOR_SERVICE.shutdown();
			}
		});
	}

	/**
	 * 获取当前 Unix 纪元毫秒值（精度为 250ms 以内）
	 * <p>
	 * 相较于 {@link System#currentTimeMillis()} 方法在高并发时，不需要频繁地调用
	 * </p>
	 *
	 * @return 误差在 250ms 以内的 Unix 纪元毫秒值
	 */
	public static long currentTimeMillis() {
		return CURRENT_TIME_MILLIS.get();
	}
}
