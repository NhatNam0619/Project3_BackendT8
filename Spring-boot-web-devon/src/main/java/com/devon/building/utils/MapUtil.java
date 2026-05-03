package com.devon.building.utils;

import java.util.Map;

public class MapUtil {
    public static <T> T getObject(Map<String, Object> params, String key, Class<T> tclass) {
        Object object = params.getOrDefault(key, null);
        if(object!=null)
        {
            if(tclass.getTypeName().equals("java.lang.Long"))
            {
                object = object != "" ? Long.valueOf(object.toString()) : null;
            }
            else if(tclass.getTypeName().equals("java.lang.Integer"))
            {
                object = object != "" ? Integer.valueOf(object.toString()) : null;
            }
            else if(tclass.getTypeName().equals("java.lang.String"))
            {
                object = object != "" ? object.toString() : null;
            }
            return tclass.cast(object);
        }
        return null;
    }
}

