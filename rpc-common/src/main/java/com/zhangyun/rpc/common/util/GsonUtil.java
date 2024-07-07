package com.zhangyun.rpc.common.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonUtil {
    private static final Gson gson = new Gson();

    public static <T> String toJsonString(T data) {
        return gson.toJson(data);
    }

    public static <T> T fromJson(String str, Type type) {
        return gson.fromJson(str, type);
    }
}
