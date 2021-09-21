package com.redisaop.service;

import java.util.Arrays;

public class CacheKeyGenerator {


    public static String generateKey(String methodName , Object... params) {
        if (params.length == 0) {
            return new Integer(methodName.hashCode()).toString();
        }
        Object[] paramList = new Object[params.length+1];
        paramList[0] = methodName;
        System.arraycopy(params, 0, paramList, 1, params.length);
        int hashCode = Arrays.deepHashCode(paramList);
        return new Integer(hashCode).toString();
    }
}