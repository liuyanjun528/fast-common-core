package com.meditrusthealth.fast.common.core.mbean;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.meditrusthealth.fast.common.core.memorycache.MemoryCacheMBean;

public class ManagementServiceImpl implements ManagementService {

    private final ObjectNameBuilder objectNameBuilder;
    private final MBeanServer mbeanServer;
    private final Set<ObjectName> registeredNames = new HashSet<ObjectName>();

    public ManagementServiceImpl(MBeanServer mbeanServer) {
        this.mbeanServer = mbeanServer;
        this.objectNameBuilder = ObjectNameBuilder.createDefault();
    }

    @Override
    public ObjectName registerMemoryCacheMBean(final String name, final MemoryCacheMBean memoryCache) throws JMException {
        ObjectName objectName = objectNameBuilder.createMemoryCacheObjectName( name );
        registerInJMX( objectName , memoryCache );
        return objectName;
    }

    @Override
    public ObjectName registerUtilsMBean(String module, String name, Object mbean) throws JMException {
        ObjectName objectName = objectNameBuilder.createUtilsObjectName( module , name );
        registerInJMX( objectName , mbean );
        return objectName;
    }

    @Override
    public ObjectName registerMBean(String type, String module, String name, Object mbean) throws JMException {
        ObjectName objectName = objectNameBuilder.createObjectName( type, module , name );
        registerInJMX( objectName , mbean );
        return objectName;
    }

    @Override
    public synchronized void registerInJMX(final ObjectName objectName, final Object mbean) throws JMException {
        unregisterFromJMX( objectName );
        mbeanServer.registerMBean( mbean , objectName );
        registeredNames.add( objectName );
    }

    @Override
    public synchronized void unregisterFromJMX(final ObjectName objectName) throws JMException {
        if ( mbeanServer.isRegistered( objectName ) ) {
            mbeanServer.unregisterMBean( objectName );
            registeredNames.remove( objectName );
        }
    }

    @Override
    public final synchronized void start() {
        doSyncStart();
    }

    @Override
    public synchronized void stop() {

        if ( registeredNames.isEmpty() ) {
            return;
        }

        for ( Iterator<ObjectName> i = registeredNames.iterator(); i.hasNext();  ) {
            try {
                ObjectName objectName = i.next();
                if ( mbeanServer.isRegistered( objectName ) ) {
                    mbeanServer.unregisterMBean( objectName );
                    i.remove();
                }
            }
            catch ( Exception e ) {
            }
        }
    }

    protected void doSyncStart() {
    }
}
