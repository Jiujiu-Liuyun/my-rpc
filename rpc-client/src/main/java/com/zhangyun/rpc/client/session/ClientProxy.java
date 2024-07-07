package com.zhangyun.rpc.client.session;

import com.zhangyun.rpc.common.domain.RpcRequest;
import com.zhangyun.rpc.common.domain.RpcResponse;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@AllArgsConstructor
public class ClientProxy implements InvocationHandler {
    private String host;
    private int port;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = RpcRequest.builder()
                .serviceClz(method.getDeclaringClass())
                .method(method.getName())
                .params(args)
                .paramsTypes(method.getParameterTypes())
                .build();
        RpcResponse response = IOClient.sendRequest(host, port, request);
        return response == null ? null : response.getData();
    }

    public <T> T getProxy(Class<T> clz) {
        Object proxy = Proxy.newProxyInstance(clz.getClassLoader(), new Class[]{clz}, this);
        return (T) proxy;
    }
}
