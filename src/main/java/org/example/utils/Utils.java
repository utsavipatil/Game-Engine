package org.example.utils;

import java.util.function.Supplier;

public class Utils {
    public static Object getIfNull(Object object, Supplier<Object> supplier){
        if(object == null){
            return supplier.get();
        }
        return object;
    }
}
