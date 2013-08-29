package com.app.domain.model.utils;

import java.lang.reflect.Field;

/** Helper class to retrieve non-public fields with no accessors in an entity class. */
public final class TestHelper
{
    public static Object getReflectedFieldValue(Object entity, String fieldName)
    {
        // create class instance of entity
        Class<? extends Object>  entityClass = entity.getClass();
        try
        {
            // get reflected field, make it accessible and return

        	Field reflectedSerialUID =  entityClass.getDeclaredField(fieldName);
            reflectedSerialUID.setAccessible(true);

            return reflectedSerialUID.get(entity);
        }
        catch (Exception ex)
        {
            return null;
        }
    }
}