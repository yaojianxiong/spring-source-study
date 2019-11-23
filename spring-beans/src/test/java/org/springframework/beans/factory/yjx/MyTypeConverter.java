package org.springframework.beans.factory.yjx;

import org.springframework.beans.TypeConverter;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Field;

/**
 * 2019/11/21 17:44
 *
 * @author: yjx
 * @since 1.0
 */
public class MyTypeConverter implements TypeConverter {
    @Override
    public <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException {
        return null;
    }

    @Override
    public <T> T convertIfNecessary(Object value, Class<T> requiredType, MethodParameter methodParam) throws TypeMismatchException {

        return null;
    }

    @Override
    public <T> T convertIfNecessary(Object value, Class<T> requiredType, Field field) throws TypeMismatchException {
        return null;
    }
}
