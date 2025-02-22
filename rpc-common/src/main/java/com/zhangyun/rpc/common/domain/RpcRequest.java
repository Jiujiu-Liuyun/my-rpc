package com.zhangyun.rpc.common.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Method;

@Builder
@Data
public class RpcRequest implements Serializable {
    private Class<?> serviceClz;
    private String method;
    private Object[] params;
    private Class<?>[] paramsTypes;

}
