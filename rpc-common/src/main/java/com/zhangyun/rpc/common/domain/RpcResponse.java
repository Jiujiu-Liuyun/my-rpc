package com.zhangyun.rpc.common.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RpcResponse implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    public static RpcResponse success(Object data) {
        return RpcResponse.builder().code(200).data(data).build();
    }

    public static RpcResponse bizFail(String msg) {
        return RpcResponse.builder().code(300).msg(msg).build();
    }

    public static RpcResponse sysFail() {
        return RpcResponse.builder().code(500).msg("服务器发生错误").build();
    }

}
