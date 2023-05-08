package com.ssts.ssts.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

@Component
public class UpdateUtils<T> {


    public T copyNonNullProperties(T source, T destination){      // source = update 할 객체 , destination = 업데이트 전 DB의 기존 객체
        if (source == null || destination == null || source.getClass() != destination.getClass()) {
            return null;
        }

        final BeanWrapper src = new BeanWrapperImpl(source);
        final BeanWrapper dest = new BeanWrapperImpl(destination);

        for (final Field property : source.getClass().getDeclaredFields()) {
            Object sourceProperty = src.getPropertyValue(property.getName());
            if (sourceProperty != null && !(sourceProperty instanceof Collection<?>)) {
                dest.setPropertyValue(property.getName(), sourceProperty);
            }
        }

        return destination;

    }
}
