package com.meditrusthealth.fast.common.core.mbean;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meditrusthealth.fast.common.core.memorycache.MemoryCacheMBean;

public final class MBeanManager {

	private static final Logger LOG = LoggerFactory.getLogger(MBeanManager.class);

	private static final MBeanServer mbeanServer = initializeMBeanServer();
	private static final ManagementService management = new ManagementServiceImpl(mbeanServer);

	static {
		management.start();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				MBeanManager.stop();
				LOG.info("Shutdown hook started, stop the all register MBeans");
			}
		});
	}

	private MBeanManager() {
	}

	public static void registerMBean(String type, String module, String name, Object mbean) {
		ObjectName objectName = null;
		try {
			objectName = management.registerMBean(type, module, name, mbean);
			LOG.info("register MBean, objectName = {}, mbean = {}", objectName, mbean);
		} catch (Exception e) {
			LOG.warn("register MBean failed, name = {}, objectName = {}, mbean = {}", name, objectName, mbean, e);
		}
	}

	public static void registerUtilsMBean(String module, String name, Object mbean) {
		ObjectName objectName = null;
		try {
			objectName = management.registerUtilsMBean(module, name, mbean);
			LOG.info("register Utils MBean, objectName = {}, mbean = {}", objectName, mbean);
		} catch (Exception e) {
			LOG.warn("register Utils MBean failed, name = {}, objectName = {}, mbean = {}", name, objectName, mbean, e);
		}
	}

	public static void registerMemoryCacheMBean(final String name, final MemoryCacheMBean memoryCache) {
		ObjectName objectName = null;
		try {
			objectName = management.registerMemoryCacheMBean(name, memoryCache);
			LOG.info("register MemoryCache MBean, objectName = {}, mbean = {}", objectName, memoryCache);
		} catch (Exception e) {
			LOG.warn("register MemoryCache MBean failed, name = {}, objectName = {}, memoryCache = {}", name,
					objectName, memoryCache, e);
		}
	}

	public static void registerInJMX(final ObjectName objectName, final Object mbean) {
		try {
			management.registerInJMX(objectName, mbean);
			LOG.info("register MBean {}, mbean = {}", objectName, mbean);
		} catch (Exception e) {
			LOG.warn("register MBean failed, objectName = {}, mbean = {}", objectName, mbean, e);
		}
	}

	public static void unregisterFromJMX(final ObjectName objectName) {
		try {
			management.unregisterFromJMX(objectName);
			LOG.info("unregister MBean {}", objectName);
		} catch (Exception e) {
			LOG.warn("unregister MBean failed, objectName = {}", objectName, e);
		}
	}

	public static void stop() {
		management.stop();
	}

	private static MBeanServer initializeMBeanServer() {
		return ManagementFactory.getPlatformMBeanServer();
	}
}
