package com.meditrusthealth.fast.common.core.lang;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import com.meditrusthealth.fast.common.core.utils.Assert;

public class BeanProperty {

    private final PropertyDescriptor property;
    private final Method reader;
    private final Method writer;

    BeanProperty(PropertyDescriptor property) {
        this.property = property;
        this.reader = property.getReadMethod();
        this.writer = property.getWriteMethod();
    }
    
    public String getPropertyName() {
        return property.getName();
    }

    public boolean hasReader() {
        return (reader != null);
    }

    public boolean hasWriter() {
        return (writer != null);
    }

    public Object invokeGet(Object target) {
        checkTarget( target , reader , "read" );
        return ReflectionUtils.invokeMethod( target , reader );
    }

    public void invokeSet(Object target, Object value) {
        checkTarget( target , writer , "write" );
        ReflectionUtils.invokeMethod( target , writer , value );
    }

    private void checkTarget(Object target, Method method, String type) {
        Assert.notNull( target , "target" );
        if ( method == null ) {
            throw new UnsupportedOperationException( "target class '" + target.getClass().getName() +
                    "' property '" + property.getName() + "' " + type + " method not exists" );
        }
    }
}
