package com.zhangyun.rpc.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class GsonUtil {
    private static final Gson gson = new GsonBuilder().create();

    public static <T> String toJsonString(T data) {
        return gson.toJson(data);
    }

    public static <T> T fromJson(String str, Type type) {
        return gson.fromJson(str, type);
    }
}
