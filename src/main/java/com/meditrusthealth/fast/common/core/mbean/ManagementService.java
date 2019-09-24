package com.meditrusthealth.fast.common.core.mbean;

import javax.management.JMException;
import javax.management.ObjectName;

import com.meditrusthealth.fast.common.core.memorycache.MemoryCacheMBean;

public interface ManagementService {

	ObjectName registerMBean(String type, String module, String name, Object mbean) throws JMException;

	ObjectName registerUtilsMBean(String module, String name, Object mbean) throws JMException;

	ObjectName registerMemoryCacheMBean(String name, MemoryCacheMBean memoryCache) throws JMException;

	void registerInJMX(ObjectName objectName, Object mbean) throws JMException;

	void unregisterFromJMX(ObjectName objectName) throws JMException;

	void start();

	void stop();
}
